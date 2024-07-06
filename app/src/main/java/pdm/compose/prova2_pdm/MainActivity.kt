package pdm.compose.prova2_pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pdm.compose.prova2_pdm.ui.NavBar
import pdm.compose.prova2_pdm.ui.TopBar
import pdm.compose.prova2_pdm.ui.screens.HomeScreen
import pdm.compose.prova2_pdm.ui.screens.bikes.BikesNavigation
import pdm.compose.prova2_pdm.ui.screens.clientes.ClientesNavigation
import pdm.compose.prova2_pdm.ui.theme.Prova2PDMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prova2PDMTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    /* TODO VIEW MODELS */
//                    val clienteViewModel = ClienteViewModel(/*dataprovider*/)
//                    val bikeViewModel = BikeViewModel(/*dataprovider*/)
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController
    /* TODO VIEW MODELS */
){
    Scaffold(
        topBar = { TopBar(navController = navController) },
        bottomBar = { NavBar(navController = navController) }
    ) {  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
            ) {
            composable("home") { HomeScreen() }
            composable("clientes") { ClientesNavigation(navController = navController) }
            composable("bikes") { BikesNavigation(navController = navController, /*viewModelStoreOwner = TODO VIEW MODEL */) }
        }
    }
}

