package cl.duouc.kt_nicolas_munoz

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.duouc.kt_nicolas_munoz.data.local.AppDataBase
import cl.duouc.kt_nicolas_munoz.data.repository.LibroRepository
import cl.duouc.kt_nicolas_munoz.navigation.NavGraph
import cl.duouc.kt_nicolas_munoz.ui.theme.KT_Nicolas_MunozTheme
import cl.duouc.kt_nicolas_munoz.viewmodel.LibroViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: LibroViewModel by viewModels{
        val database = AppDataBase.getInstance(applicationContext)
        val repository = LibroRepository(database.libroDao())
        LibroViewModel.LibroViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(viewModel)
                }
            }
        }

    }
}