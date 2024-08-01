package pdm.compose.prova2_pdm.ui.screens.bikes

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.Bike
import pdm.compose.prova2_pdm.ui.components.CustomOutlinedTextField
import pdm.compose.prova2_pdm.ui.components.NumberField
import pdm.compose.prova2_pdm.ui.components.TextTitle
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeEditionScreen(
    navController: NavController,
    bikeViewModel: BikeViewModel,
    clienteViewModel: ClienteViewModel
) {
    val bike by bikeViewModel.selectedBike.collectAsState()
    val cliente = clienteViewModel.getClienteById(bike.clienteId) // Buscar o cliente

    var showInfoDropDown by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() } //InteractionSource para o ícone do nome do usuário

    var bikeModelo by remember(bike) { mutableStateOf(bike.modelo) }
    var bikeMaterialChassi by remember(bike) { mutableStateOf(bike.material_do_chassi)}
    var bikeAro by remember(bike) { mutableStateOf(bike.aro.toString()) }
    var bikePreco by remember(bike) { mutableStateOf(bike.preco.toString()) }
    var bikeQtdMarchas by remember(bike) { mutableStateOf(bike.quantidade_de_marchas.toString()) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.navigate("bikes") },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar para a Lista de Bikes"
                    )
                }
            }

            TextTitle(text = "Editar Bike")

            // Mostrar o nome do cliente (não editável)
            CustomOutlinedTextField(
                value = cliente?.nome ?: "Cliente não encontrado",
                onValueChange = { /* Campo desabilitado */ },
                label = "Cliente",
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                trailingIcon = {
                    Box {
                        IconButton(
                            onClick = { showInfoDropDown = !showInfoDropDown },
                            interactionSource = interactionSource,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) { Icon(Icons.Filled.QuestionMark, contentDescription = "Informação sobre o campo") }
                        DropdownMenu(
                            expanded = showInfoDropDown,
                            onDismissRequest = { showInfoDropDown = false },
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .padding(8.dp)
                        ) {
                            Text("Fazemos Bikes sob medida dos Clientes:\nDonos não são editáveis!",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campos editáveis da bike
            CustomOutlinedTextField(
                value = bikeModelo,
                onValueChange = { bikeModelo = it },
                label = "Modelo",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = bikeMaterialChassi,
                onValueChange = { bikeMaterialChassi = it },
                label = "Material do Chassi",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            NumberField(
                value = bikeAro.toInt(),
                onValueChange = { bikeAro = it.toString() },
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
                value = bikeQtdMarchas.toInt(),
                onValueChange = { bikeQtdMarchas = it.toString() },
                label = "Quantidade de Marchas",
                modifier = Modifier.fillMaxWidth(),
                incrementValue = 1
            )

            Button(
                onClick = {
                    val updatedBike = bike.copy(
                        modelo = bikeModelo,
                        material_do_chassi = bikeMaterialChassi,
                        aro = bikeAro.toIntOrNull() ?: 0, // Lidar com conversãopara Int
                        preco = bikePreco.toDoubleOrNull() ?: 0.0, // Lidar com conversão para Double
                        quantidade_de_marchas = bikeQtdMarchas.toIntOrNull() ?: 0 // Lidar com conversão para Int
                    )
                    bikeViewModel.atualizarBike(updatedBike)
                    navController.navigate("bikes")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Alterações")
            }
        }
    }
}