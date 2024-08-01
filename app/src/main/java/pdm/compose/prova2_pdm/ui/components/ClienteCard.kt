package pdm.compose.prova2_pdm.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pdm.compose.prova2_pdm.model.Cliente

@Composable
fun ClienteCard(
    cliente: Cliente,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Log.d("ClienteCard", "Renderizando $cliente")
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Cliente: ${cliente.nome}", fontWeight = FontWeight.Bold)
            Text(text = "CPF: ${cliente.cpf}")
            Text(text = "Email: ${cliente.email}")
        }
    }
}