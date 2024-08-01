package pdm.compose.prova2_pdm.ui.screens.clientes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.toAttributeMap
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel
import pdm.compose.prova2_pdm.ui.components.GenericAttributeCard
import pdm.compose.prova2_pdm.ui.components.TextLabel
import pdm.compose.prova2_pdm.ui.components.TextTitle

@Composable
fun DetailedClienteScreen(
    navController: NavController,
    viewModel: ClienteViewModel
){
    val cliente by viewModel.selectedCliente.collectAsState()
    Log.d("ClienteDetailsScreen", "cliente Id: $cliente")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.setSelectedCliente(cliente)
                    navController.navigate("clientes/clienteEdition")
                    Log.d("ClienteDetailsScreen", "Cliente a editar: ${cliente.nome}")
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edição do Cliente")
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
                    onClick = { navController.navigate("clientes") },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar para a Lista de Clientes"
                    )
                }
            }

            val clienteAttributes = cliente.toAttributeMap()

            TextTitle(
                text = "Cliente Detalhado",
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Column(
                modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                clienteAttributes.forEach { (name, value) ->
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        TextLabel(text = name)
                        GenericAttributeCard(value = value)
                    }
                }
            }
        }
    }
}