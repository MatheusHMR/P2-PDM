package pdm.compose.prova2_pdm.ui.screens.bikes

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.Bike
import pdm.compose.prova2_pdm.model.Cliente
import pdm.compose.prova2_pdm.ui.components.CustomOutlinedTextField
import pdm.compose.prova2_pdm.ui.components.DropdownMenuComponent
import pdm.compose.prova2_pdm.ui.components.NumberField
import pdm.compose.prova2_pdm.ui.components.TextTitle
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBikeScreen(
    navController: NavController,
    bikeViewModel: BikeViewModel,
    clienteViewModel: ClienteViewModel
) {
    var bikeModelo by remember { mutableStateOf("") }
    var bikeMaterialChassi by remember { mutableStateOf("") }
    var bikeAro by remember { mutableIntStateOf(0) }
    var bikePreco by remember { mutableStateOf("0.0") }
    var bikeQtdMarchas by remember { mutableIntStateOf(0) }
    var selectedCliente by remember { mutableStateOf<Cliente?>(null) }
    val context = LocalContext.current

    val clientes = clienteViewModel.clientes.collectAsState().value

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.navigate("bikes") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar para a Lista de Bikes"
                    )
                }
            }

            TextTitle(text = "Nova Bike")

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para seleção do cliente
            DropdownMenuComponent(
                label = "Selecione o Cliente",
                selectedOption = selectedCliente?.nome ?: "Selecione o Cliente",
                options = clientes.map { it.nome },
                onSelectionChange = { selectedOption ->
                    selectedCliente = clientes.find { it.nome == selectedOption }
                    selectedCliente?.let {
                        bikeViewModel.setChosenCliente(it.clienteId)
                    }
                }
            )

            // Campos de entrada para os detalhes da bicicleta
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedTextField(
                value = bikeModelo,
                onValueChange = { bikeModelo = it },
                label = "Modelo",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomOutlinedTextField(
                value = bikeMaterialChassi,
                onValueChange = { bikeMaterialChassi = it },
                label = "Material do Chassi",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(4.dp))

            NumberField(
                value = bikeAro,
                onValueChange = { bikeAro = it },
                label = "Aro",
                modifier = Modifier.fillMaxWidth(),
                incrementValue = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomOutlinedTextField(
                value = bikePreco,
                onValueChange = { bikePreco = it },
                label = "Preço",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(4.dp))

            NumberField(
                value = bikeQtdMarchas,
                onValueChange = { bikeQtdMarchas = it },
                label = "Quantidade de Marchas",
                modifier = Modifier.fillMaxWidth(),
                incrementValue = 1
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para adicionar a bicicleta
            Button(
                onClick = {
                    if (selectedCliente != null) {
                        val novaBike = Bike(
                            modelo = bikeModelo,
                            material_do_chassi = bikeMaterialChassi,
                            aro = bikeAro,
                            preco = bikePreco.toDoubleOrNull() ?: 0.0,
                            quantidade_de_marchas = bikeQtdMarchas,
                            clienteId = selectedCliente!!.clienteId
                        )
                        bikeViewModel.adicionarBike(novaBike)
                        Log.i("AddBikeScreen", "Adicionada Bike: ${novaBike.modelo}")
                        navController.navigate("bikes")
                        Toast.makeText(context, "Bike adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Selecione um cliente primeiro!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Adicionar Bike")
            }
        }
    }
}
