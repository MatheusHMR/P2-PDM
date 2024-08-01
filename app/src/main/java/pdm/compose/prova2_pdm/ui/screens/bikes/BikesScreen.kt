package pdm.compose.prova2_pdm.ui.screens.bikes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.Bike
import pdm.compose.prova2_pdm.ui.components.ConfirmationDialog
import pdm.compose.prova2_pdm.ui.components.CustomOutlinedTextField
import pdm.compose.prova2_pdm.ui.components.GenericListItem
import pdm.compose.prova2_pdm.ui.components.TextTitle
import pdm.compose.prova2_pdm.viewmodel.BikeViewModel
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel

@Composable
fun BikesScreen (
    navController: NavController,
    viewModel: BikeViewModel,
    clienteViewModel: ClienteViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()
    var selectedBike by remember { mutableStateOf(Bike.EMPTY) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    var searchText by remember { mutableStateOf("") }
    var showInfoDropDown by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Surface(onClick = {focusManager.clearFocus()}) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("bikes/addBike") }) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Add Bike")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                TextTitle(text = "Bikes")

                //BARRA DE PESQUISA
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp, horizontal = 8.dp)
                        .clickable { focusManager.clearFocus() }
                ) {
                    CustomOutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            viewModel.filtrarBikes(it, clienteViewModel)
                        },
                        label = "Pesquisar",
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        trailingIcon = {
                            Box {
                                IconButton(
                                    onClick = { showInfoDropDown = !showInfoDropDown }
                                ) { Icon(Icons.Filled.Info, contentDescription = "Dicas de Pesquisa") }
                                DropdownMenu(
                                    expanded = showInfoDropDown,
                                    onDismissRequest = { showInfoDropDown = false },
                                    modifier = Modifier
                                        .width(IntrinsicSize.Min)
                                        .padding(8.dp)
                                ) {
                                    Text("Pesquise por Bikes usando:\nNome ou CPF do Cliente\nCódigo, Modelo ou Aro da Bike",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(700),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (!it.isFocused) {
                                    focusManager.clearFocus()
                                }
                            }
                    )
                }

                //DEFINIR SE É BUSCA FILTRADA OU TRADICIONAL
                val bikes = if (searchText.trim().isBlank()) {
                    viewModel.bikes.collectAsState()
                } else {
                    viewModel.filteredBikes.collectAsState()
                }

                if(isLoading) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center)
                    { CircularProgressIndicator() }
                } else {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(bikes.value) { bike ->
                            var expanded by remember { mutableStateOf(false) }

                            // Obter o nome do cliente pelo Id que está na bike
                            val clienteNome = clienteViewModel.getClienteById(bike.clienteId)?.nome ?: ""

                            GenericListItem(
                                item = bike,
                                displayContent = {
                                    Column {
                                        Text(
                                            text = "Bike: ${bike.modelo}",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                        Text(
                                            text = "Preço: R$ ${bike.preco}",
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "Marchas: ${bike.quantidade_de_marchas}",
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "Material: ${bike.material_do_chassi}",
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "Cliente: ${clienteNome}",
                                            fontSize = 14.sp
                                        )
                                    }
                                },
                                onCardClick = {
                                    viewModel.setSelectedBike(bike)
                                    navController.navigate("bikes/bikeDetails")
                                },
                                onMoreInfoClick = {
                                    viewModel.setSelectedBike(bike)
                                    navController.navigate("bikes/bikeDetails")
                                },
                                onEditClick = {
                                    viewModel.setSelectedBike(bike)
                                    navController.navigate("bikes/bikeEdition")
                                    expanded = false
                                },
                                onDeleteClick = {
                                    selectedBike = bike
                                    showDeleteConfirmation = true
                                    expanded = false
                                },
                                expanded = expanded,
                                onExpandChange = { expanded = it }
                            )
                        }
                    }

                    // Confirmation Dialog
                    ConfirmationDialog(
                        showDialog = showDeleteConfirmation,
                        title = "Confirme a Deleção",
                        message = "Tem certeza que quer deletar a bike do Cliente: \n<<}>>?",/* TODO BIKE SELECTION BY CLIENTE ID*/
                        onConfirm = {
                            viewModel.excluirBike(selectedBike.codigo)
                            navController.navigate("bikes")
                            selectedBike = Bike.EMPTY
                            showDeleteConfirmation = false
                        },
                        onDismiss = {
                            selectedBike = Bike.EMPTY
                            showDeleteConfirmation = false
                        }
                    )
                }
            }
        }
    }
}