package cl.duouc.kt_nicolas_munoz.model

import cl.duouc.kt_nicolas_munoz.data.local.LibroEntity

data class Libro(
    val id:Int = 0,
    val titulo: String,
    val autor: String,
    val anio: Int
)

fun LibroEntity.toDomain(): Libro {
    return Libro(
        id = this.id,
        titulo = this.titulo,
        autor = this.autor,
        anio = this.anio
    )
}

fun Libro.toEntity(): LibroEntity {
    return LibroEntity(
        id = this.id,
        titulo = this.titulo,
        autor = this.autor,
        anio = this.anio
    )
}