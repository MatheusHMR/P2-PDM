package pdm.compose.prova2_pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pdm.compose.prova2_pdm.data.DataProvider
import pdm.compose.prova2_pdm.model.Cliente
import pdm.compose.prova2_pdm.ui.theme.Prova2PDMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prova2PDMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    val cliente1 = Cliente(cpf = "003", nome = "mario da costa", instagram = "@mariodacosta", email = "mariodacosta@gmail.com")
                    val cliente2 = Cliente(cpf = "004", nome = "marreco", instagram = "@marreco123", email = "marreco@gmail.com")
                    val clientsRepository = DataProvider.clienteRepository
                    LaunchedEffect(key1 = cliente1) {
                        clientsRepository.addCliente(cliente1)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Prova2PDMTheme {
        Greeting("Android")
    }
}

