package cl.duouc.kt_nicolas_munoz.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [LibroEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun libroDao(): LibroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                lateinit var instance: AppDataBase
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "libros_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se ejecuta SOLO la primera vez que se crea la BD.
                            // OJO: en este punto INSTANCE todavía es null (recién
                            // se está construyendo), por eso usamos la referencia
                            // local "instancia" capturada por este callback.
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = instance.libroDao()
                                dao.insertarLibro(LibroEntity(titulo = "El principito", autor = "Antoine de Saint-Exupéry", anio = 1943))
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}