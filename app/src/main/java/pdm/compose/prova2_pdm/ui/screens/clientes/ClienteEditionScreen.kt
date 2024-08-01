package pdm.compose.prova2_pdm.ui.screens.clientes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel
import pdm.compose.prova2_pdm.ui.components.CustomOutlinedTextField
import pdm.compose.prova2_pdm.ui.components.TextTitle

@Composable
fun ClienteEditionScreen(
    navController: NavController,
    viewModel: ClienteViewModel
){
    val cliente by viewModel.selectedCliente.collectAsState()
    Log.d("ClienteEditionScreen", "Cliente: $cliente")

    var clienteCpf by remember(cliente) { mutableStateOf(cliente.cpf ?: "") }
    var clienteNome by remember(cliente) { mutableStateOf(cliente.nome ?: "") }
    var clienteEmail by remember(cliente) { mutableStateOf(cliente.email ?: "") }
    var clienteInstagram by remember(cliente) { mutableStateOf(cliente.instagram ?: "") }

    Scaffold {
            innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.navigate("clientes") },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Cliente List"
                    )
                }
            }

            TextTitle(text = "Edite o Cliente")

            CustomOutlinedTextField(
                value = clienteCpf,
                onValueChange = { /*Disabled field*/},
                label = "CPF",
                modifier = Modifier.fillMaxWidth(),
                enabled = false
//                    validation = viewModel::validateClienteName // Assuming you have this in your ViewModel
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = clienteNome,
                onValueChange = { clienteNome = it },
                label = "Nome",
                modifier = Modifier.fillMaxWidth(),
//                    validation = viewModel::validateClienteName // Assuming you have this in your ViewModel
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = clienteEmail,
                onValueChange = { clienteEmail = it },
                label = "E-mail",
                modifier = Modifier.fillMaxWidth(),
//                    validation = viewModel::validateClienteAddress // Assuming you have this
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomOutlinedTextField(
                value = clienteInstagram,
                onValueChange = { clienteInstagram = it },
                label = "Instagram",
                modifier = Modifier.fillMaxWidth(),
//                    validation = viewModel::validateClienteAddress // Assuming you have this
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val updatedCliente = cliente.copy(
                            cpf = clienteCpf,
                            nome = clienteNome,
                            email = clienteEmail,
                            instagram = clienteInstagram
                        )
                    viewModel.atualizarCliente(updatedCliente)
                    navController.navigate("clientes")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }
        }
    }

}