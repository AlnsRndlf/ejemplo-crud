package cl.duouc.kt_nicolas_munoz.navigation

sealed class Ruta(val ruta:String){

    object LibroLista: Ruta("libro_lista")

    object LibroAgregar: Ruta("libro_agregar?id={id}"){
        const val ARG_ID = "id"
        fun crearRuta(id: Int?) = "libro_agregar"
        fun actualizarRuta(id: Int?) = "libro_agregar?id=$id"
    }

    object LibroDetalle: Ruta("libro_detalle?id={id}"){
        const val ARG_ID = "id"
        fun detalleId(id: Int?) = "libro_detalle?id=$id"
    }
}