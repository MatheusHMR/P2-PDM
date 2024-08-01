package pdm.compose.prova2_pdm.ui.screens.bikes

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.Bike
import pdm.compose.prova2_pdm.model.toAttributeMap
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.ui.components.GenericAttributeCard
import pdm.compose.prova2_pdm.ui.components.TextLabel
import pdm.compose.prova2_pdm.ui.components.TextTitle
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@Composable
fun DetailedBikeScreen(
    navController: NavController,
    bikeViewModel: BikeViewModel,
    clienteViewModel: ClienteViewModel
) {
    val bike by bikeViewModel.selectedBike.collectAsState()
    Log.d("BikeDetailsScreen", "Bike Id: $bike")

    val clienteNome = clienteViewModel.getClienteById(bike.clienteId)?.nome ?: ""
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    bikeViewModel.setSelectedBike(bike)
                    navController.navigate("bikes/bikeEdition")
                    Log.d("BikeDetailsScreen", "Cliente a editar: ${clienteViewModel.getClienteById(bike.clienteId)}")
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edição da Bike")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.navigate("bikes") },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar para a Lista de Bikes"
                    )
                }
            }

            val bikeAttributes = bike.copy().toAttributeMap().toMutableMap()

            bikeAttributes["Cliente"] = clienteNome

            TextTitle(
                text = "Detalhes da Bike",
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Column(
                modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                bikeAttributes.forEach { (name, value) ->
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        TextLabel(text = name)
                        GenericAttributeCard(value = value)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaçamento antes do botão

            Button(
                onClick = {
                    bikeViewModel.setSelectedBike(bike)
                    navController.navigate("bikes/bikeEdition")
                    Log.d("BikeDetailsScreen", "Bike a editar: ${bike.modelo}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar Bike")
            }
        }
    }
}