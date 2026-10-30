package cl.duouc.kt_nicolas_munoz.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import cl.duouc.kt_nicolas_munoz.data.local.LibroEntity
import cl.duouc.kt_nicolas_munoz.model.Libro
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroDetalle(
    viewModel: LibroViewModel,
    libroId: Int?,
    alEditar: (Int) -> Unit,
    alVolver: () -> Unit
) {
    var libro by remember { mutableStateOf<Libro?>(null) }

    LaunchedEffect(libroId) {
        if (libroId != null) {
            libro = viewModel.obtenerLibroPorId(libroId)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle del Libro") },
            navigationIcon = {
                IconButton(onClick = alVolver) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        ) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            val actual = libro
            if (actual == null) {
                Text("Cargando...")
            } else {
                Text("Titulo: ${actual.titulo}")
                Text(
                    text = "Autor : ${actual.autor}",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Año : ${actual.anio}",
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(
                    modifier = Modifier.padding(top = 24.dp),
                    onClick = { alEditar(actual.id) }
                ) {
                    Text("Editar")
                }
            }
        }
    }
}