package cl.duouc.kt_nicolas_munoz

import cl.duouc.kt_nicolas_munoz.data.repository.LibroRepository
import cl.duouc.kt_nicolas_munoz.model.Libro
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LibroViewModelTest {

    private val repository: LibroRepository = mockk(relaxed = true)
    private lateinit var viewModel: LibroViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LibroViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insertarLibro debe llamar a insertarLibro del repositorio cuando el id es cero`() {
        // ARRANGE -> Instanciamos un "Libro" puro con ID 0
        val nuevoLibro =
            Libro(id = 0, titulo = "El principito", autor = "Saint-Exupéry", anio = 1943)

        // ACT
        viewModel.insertarLibro(nuevoLibro)

        // ASSERT -> Verificamos que el repositorio reciba el modelo Libro
        coVerify(exactly = 1) { repository.insertarLibro(nuevoLibro) }
        coVerify(exactly = 0) { repository.actualizarLibro(any()) }
    }

    @Test
    fun `insertarLibro debe llamar a actualizarLibro del repositorio cuando el id NO es cero`() {
        // ARRANGE -> Instanciamos un "Libro" con un ID existente (ej. 12)
        val libroExistente = Libro(id = 12, titulo = "El principito", autor = "Saint-Exupéry", anio = 1943)

        // ACT
        viewModel.insertarLibro(libroExistente)

        // ASSERT -> Verificamos que el repositorio ejecute la actualización
        coVerify(exactly = 1) { repository.actualizarLibro(libroExistente) }
        coVerify(exactly = 0) { repository.insertarLibro(any()) }
    }
}