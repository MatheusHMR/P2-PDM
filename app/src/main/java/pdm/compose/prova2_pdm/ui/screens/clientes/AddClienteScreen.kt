package pdm.compose.prova2_pdm.ui.screens.clientes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pdm.compose.prova2_pdm.model.Cliente
import pdm.compose.prova2_pdm.viewmodel.ClienteViewModel
import pdm.compose.prova2_pdm.ui.components.CustomOutlinedTextField
import pdm.compose.prova2_pdm.ui.components.TextTitle
import pdm.compose.prova2_pdm.ui.components.ThreadLaunchingButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClienteScreen(
    navController: NavController,
    viewModel: ClienteViewModel
) {

    var clienteCpf by remember { mutableStateOf("") }
    var clienteNome by remember { mutableStateOf("") }
    var clienteEmail by remember { mutableStateOf("") }
    var clienteInstagram by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
//                    navController.popBackStack()
                    navController.navigate("clientes")
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar para a Lista de Clientes",
                    )
                }
            }

            TextTitle(text = "Novo Cliente")

            CustomOutlinedTextField(
                value = clienteCpf,
                onValueChange = { clienteCpf = it },
                label = "CPF",
                modifier = Modifier.fillMaxWidth(),
//                validation = viewModel::validateclienteName
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomOutlinedTextField(
                value = clienteNome,
                onValueChange = { clienteNome = it },
                label = "Nome",
                modifier = Modifier.fillMaxWidth(),
//                validation = viewModel::validateclienteName
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomOutlinedTextField(
                value = clienteEmail,
                onValueChange = { clienteEmail = it },
                label = "E-mail",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Phone,
//                validation = viewModel::validateclientePhone
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomOutlinedTextField(
                value = clienteInstagram,
                onValueChange = { clienteInstagram = it },
                label = "Instagram",
                modifier = Modifier.fillMaxWidth(),
//                validation = viewModel::validateclienteAddress
            )

            Spacer(modifier = Modifier.height(8.dp))

            ThreadLaunchingButton(
                onClick = {
                    val novoCliente = Cliente(
                        cpf = clienteCpf,
                        nome = clienteNome,
                        email = clienteEmail,
                        instagram = clienteInstagram
                    )
                    viewModel.adicionarCliente(novoCliente)
                    Log.i("AddClienteScreen", "Added Cliente: ${novoCliente.nome}")
//                    navController.popBackStack()
                    navController.navigate("clientes") // Navigate back after adding
                },
                text = "Add cliente",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}