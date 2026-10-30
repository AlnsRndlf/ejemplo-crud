package cl.duouc.kt_nicolas_munoz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.duouc.kt_nicolas_munoz.ui.screens.LibroAgregar
import cl.duouc.kt_nicolas_munoz.ui.screens.LibroDetalle
import cl.duouc.kt_nicolas_munoz.ui.screens.LibroLista
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel
@Composable
fun NavGraph(viewModel: LibroViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Ruta.LibroLista.ruta){

        composable(Ruta.LibroLista.ruta) {
            LibroLista(
                viewModel = viewModel,
                alAgregar = { navController.navigate(Ruta.LibroAgregar.ruta) },
                alDetalle = { id -> navController.navigate(Ruta.LibroDetalle.detalleId(id)) }
            )
        }

        composable(
            route = Ruta.LibroAgregar.ruta,
            arguments = listOf(
                navArgument(Ruta.LibroAgregar.ARG_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(Ruta.LibroAgregar.ARG_ID)
            LibroAgregar(
                viewModel = viewModel,
                libroId = id?.toIntOrNull(),
                alGuardar = { navController.popBackStack() },
                alVolver = { navController.popBackStack() }
            )
        }

        composable(
            route = Ruta.LibroDetalle.ruta,
            arguments = listOf(
                navArgument(Ruta.LibroDetalle.ARG_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            backStackEntry ->
            val id = backStackEntry.arguments?.getInt(Ruta.LibroDetalle.ARG_ID)
            LibroDetalle(
                viewModel = viewModel,
                libroId = id,
                alEditar = { libroId -> navController.navigate(Ruta.LibroAgregar.actualizarRuta(libroId)) },
                alVolver = { navController.popBackStack() }
            )
        }
    }
}