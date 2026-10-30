package cl.duouc.kt_nicolas_munoz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroLista(
    viewModel : LibroViewModel,
    alAgregar: () -> Unit,
    alDetalle: (Int) -> Unit
){
    val listaLibros by viewModel.libros.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Catalogo de Libros")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = alAgregar) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Libro")
            }
        }

    ) {
            padding ->

        if(listaLibros.isEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ){
                Text(text = "No hay libros en la lista", modifier = Modifier.padding(24.dp))
            }
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaLibros, key = {it.id}){
                        libro ->
                    TarjetaLibro(
                        libro = libro,
                        alTocar = { alDetalle(libro.id)},
                        alEliminar = {viewModel.eliminarLibro(libro)}
                    )
                }
            }
        }
    }
}

