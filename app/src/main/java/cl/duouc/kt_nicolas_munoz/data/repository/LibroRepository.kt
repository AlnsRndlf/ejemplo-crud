package cl.duouc.kt_nicolas_munoz.data.repository

import cl.duouc.kt_nicolas_munoz.data.local.LibroDao
import cl.duouc.kt_nicolas_munoz.data.local.LibroEntity
import cl.duouc.kt_nicolas_munoz.model.Libro
import cl.duouc.kt_nicolas_munoz.model.toDomain
import cl.duouc.kt_nicolas_munoz.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibroRepository(
    private val libroDao: LibroDao
) {
    val libros: Flow<List<Libro>> = libroDao.obtenerLibros().map { listaEntidades ->
        listaEntidades.map { entidad -> entidad.toDomain() }
    }

    suspend fun obtenerLibroPorId(id: Int): Libro? = libroDao.obtenerLibroPorId(id)?.toDomain()

    suspend fun insertarLibro(libro: Libro) = libroDao.insertarLibro(libro.toEntity())

    suspend fun actualizarLibro(libro: Libro) = libroDao.actualizarLibro(libro.toEntity())

    suspend fun eliminarLibro(libro: Libro) = libroDao.eliminarLibro(libro.toEntity())
}