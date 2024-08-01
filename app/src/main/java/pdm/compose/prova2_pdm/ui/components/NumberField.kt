package pdm.compose.prova2_pdm.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberField(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    incrementValue: Int = 1
) {
    var textFieldValue by remember { mutableStateOf(value.toString()) }

    CustomOutlinedTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue
            val intValue = newValue.toIntOrNull() ?: value
            onValueChange(intValue.coerceAtLeast(0))
        },
        label = label,
        modifier = modifier,
        keyboardType = KeyboardType.Number,
        trailingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    // Converte textFieldValue para Int, se falhar, usa 0
                    val currentValue = textFieldValue.toIntOrNull() ?: 0
                    val newValue = (currentValue + incrementValue).coerceAtLeast(0)
                    textFieldValue = newValue.toString()
                    onValueChange(newValue)
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Incrementar")
                }
                IconButton(onClick = {
                    // Converte textFieldValue para Int, se falhar, usa 0
                    val currentValue = textFieldValue.toIntOrNull() ?: 0
                    val newValue = (currentValue - incrementValue).coerceAtLeast(0)
                    textFieldValue = newValue.toString()
                    onValueChange(newValue)
                }) {
                    Icon(Icons.Filled.Remove, contentDescription = "Decrementar")
                }
            }
        }
    )
}
