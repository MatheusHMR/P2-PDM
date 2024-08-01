package pdm.compose.prova2_pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pdm.compose.prova2_pdm.ui.NavBar
import pdm.compose.prova2_pdm.ui.TopBar
import pdm.compose.prova2_pdm.ui.screens.HomeScreen
import pdm.compose.prova2_pdm.ui.screens.SettingsScreen
import pdm.compose.prova2_pdm.ui.screens.bikes.BikesNavigation
import pdm.compose.prova2_pdm.ui.screens.clientes.ClientesNavigation
import pdm.compose.prova2_pdm.ui.theme.Prova2PDMTheme
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prova2PDMTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val clienteViewModel = ClienteViewModel()
                    val bikeViewModel = BikeViewModel()

                    // Armazena o viewModelStoreOwner no escopo da Navigation
                    val navigationViewModelStoreOwner = remember { this }
                    Navigation(
                        navController,
                        clienteViewModel,
                        bikeViewModel,
                        navigationViewModelStoreOwner
                        )
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    clienteViewModel: ClienteViewModel,
    bikeViewModel: BikeViewModel,
    viewModelStoreOwner: ViewModelStoreOwner
) {
    CompositionLocalProvider(
        LocalViewModelStoreOwner provides viewModelStoreOwner
    ) {
        Scaffold(
            topBar = { TopBar(navController = navController) },
            bottomBar = { NavBar(navController = navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen() }
                composable("settings") { SettingsScreen(navController)}
                composable("clientes") {
                    ClientesNavigation(
                        navController = navController,
                        viewModel = clienteViewModel
                    )
                }
                composable("bikes") {
                    BikesNavigation(
                        navController = navController,
                        bikeViewModel = bikeViewModel,
                        clienteViewModel = clienteViewModel
                    )
                }
            }
        }
    }
}

