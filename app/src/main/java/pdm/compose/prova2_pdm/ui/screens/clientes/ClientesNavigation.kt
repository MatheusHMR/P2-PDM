package pdm.compose.prova2_pdm.ui.screens.clientes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ClientesNavigation(
    navController: NavHostController, //main NavController
) {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = "clientes",
    ){
        composable("clientes") { ClientesScreen() }
    }
}