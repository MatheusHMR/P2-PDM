package pdm.compose.prova2_pdm.ui.screens.clientes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@Composable
fun ClientesNavigation(
    navController: NavHostController, //main NavController
    viewModel: ClienteViewModel
) {
    val nestedNavController = rememberNavController()
    NavHost(
        navController = nestedNavController,
        startDestination = "clientes",
    ){
        composable("clientes") {
            ClientesScreen(nestedNavController, viewModel)
        }
        composable("clientes/addCliente") {
            AddClienteScreen(nestedNavController, viewModel)
        }
        composable("clientes/clienteDetails"){
            DetailedClienteScreen(nestedNavController, viewModel)
        }
        composable("clientes/clienteEdition"){
            ClienteEditionScreen(nestedNavController, viewModel)
        }
    }
}