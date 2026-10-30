package cl.duouc.kt_nicolas_munoz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cl.duouc.kt_nicolas_munoz.data.repository.LibroRepository
import cl.duouc.kt_nicolas_munoz.model.Libro
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibroViewModel(
    private val libroRepository: LibroRepository
) : ViewModel() {

    val libros : StateFlow<List<Libro>> = libroRepository.libros
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()

        )

    suspend fun obtenerLibroPorId(id: Int): Libro?{
        return libroRepository.obtenerLibroPorId(id)
    }

    fun insertarLibro(libro: Libro){
        viewModelScope.launch {
            if (libro.id == 0) {
                libroRepository.insertarLibro(libro)
            } else {
                libroRepository.actualizarLibro(libro)
            }
        }
    }

    fun eliminarLibro(libro: Libro){
        viewModelScope.launch {
            libroRepository.eliminarLibro(libro)
        }
    }

    class LibroViewModelFactory(private val libroRepository: LibroRepository) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LibroViewModel(libroRepository) as T
        }
    }
}