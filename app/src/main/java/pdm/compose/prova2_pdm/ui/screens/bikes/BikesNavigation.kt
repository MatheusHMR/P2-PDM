package pdm.compose.prova2_pdm.ui.screens.bikes

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BikesNavigation(
    navController: NavHostController, // Main Nav Controller
    /*viewModelStoreOwner: ViewModelStoreOwner*/
) {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = "bikes",
    ) {
        composable("bikes") { BikesScreen()}
    }
}