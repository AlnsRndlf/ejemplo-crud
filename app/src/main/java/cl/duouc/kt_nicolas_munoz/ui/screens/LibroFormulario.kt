package cl.duouc.kt_nicolas_munoz.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duouc.kt_nicolas_munoz.model.Libro
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroAgregar(
    viewModel: LibroViewModel,
    libroId: Int?,
    alGuardar: () -> Unit,
    alVolver: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var idActual by remember { mutableStateOf(0) }

    LaunchedEffect( libroId) {
        if (libroId != null) {
            val libro = viewModel.obtenerLibroPorId(libroId)
            if (libro != null) {
                idActual = libro.id
                titulo = libro.titulo
                autor = libro.autor
                anio = libro.anio.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (libroId == null) "Nuevo Libro" else "Editar Libro") },
                navigationIcon = {
                    IconButton(onClick = alVolver) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Titulo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = autor,
                onValueChange = { autor = it },
                label = { Text("Autor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                label = { Text("Año") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                onClick = {
                    val anioValido = anio.toIntOrNull()

                    if (titulo.isNotBlank() && autor.isNotBlank() && anioValido != null) {
                        viewModel.insertarLibro(
                            Libro(
                                id = idActual,
                                titulo = titulo,
                                autor = autor,
                                anio = anioValido
                            )
                        )
                        alGuardar()
                    }
                }
            ) {
                Text("Guardar")
            }
        }
    }
}