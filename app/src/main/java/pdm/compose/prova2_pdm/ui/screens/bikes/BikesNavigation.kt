package pdm.compose.prova2_pdm.ui.screens.bikes

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@Composable
fun BikesNavigation(
    navController: NavHostController, // Main Nav Controller
    bikeViewModel: BikeViewModel,
    clienteViewModel: ClienteViewModel
) {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = "bikes",
    ) {
        composable("bikes") { BikesScreen(nestedNavController, bikeViewModel, clienteViewModel) }
        composable("bikes/addBike") { AddBikeScreen(nestedNavController, bikeViewModel, clienteViewModel) }
        composable("bikes/bikeDetails") { DetailedBikeScreen(nestedNavController, bikeViewModel, clienteViewModel) }
        composable("bikes/bikeEdition") { BikeEditionScreen(nestedNavController, bikeViewModel, clienteViewModel)}
    }
}