package pdm.compose.prova2_pdm.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    modifier: Modifier ?= null,
    text: String = "Empty",
    color: Color ?= null,
    fontSize: TextUnit ?= null,
    alignment: TextAlign ?= null
){
    Text(
        text = text,
        modifier = modifier?: Modifier
            .fillMaxWidth()
            .padding(32.dp),
        textAlign = alignment?: TextAlign.Center,
        color = color?: MaterialTheme.colorScheme.primary, // Customize color as needed
        fontWeight = FontWeight.Bold,
        fontSize = fontSize ?: 32.sp
    )
}

@Composable
fun TextPropaganda(
    text: String = "Empty",
    color: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier ?= null
) {
    TextTitle(
        modifier = modifier ?: Modifier
            .padding(vertical = 16.dp, horizontal = 0.dp),
        fontSize = 32.sp,
        text = text,
        color = color,
        alignment = TextAlign.Left,

        )
}

@Composable
fun TextLabel(
    modifier: Modifier = Modifier,
    text: String = "Empty"
) {
    val defaultModifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)

    val appliedModifier = if (modifier == Modifier) {
        defaultModifier // Use default if no custom modifier is provided
    } else {
        modifier // Use the provided modifier otherwise
    }

    Text(
        text = text,
        modifier = appliedModifier,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}