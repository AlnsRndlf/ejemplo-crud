package cl.duouc.kt_nicolas_munoz.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LibroDao {

    @Query("SELECT * FROM libros ORDER BY titulo ASC")
    fun obtenerLibros(): Flow<List<LibroEntity>>

    @Query("SELECT * FROM libros WHERE id = :id")
    suspend fun obtenerLibroPorId(id: Int): LibroEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLibro(libro: LibroEntity)

    @Update
    suspend fun actualizarLibro(libro: LibroEntity)

    @Delete
    suspend fun eliminarLibro(libro: LibroEntity)
}