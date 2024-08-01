package pdm.compose.prova2_pdm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import pdm.compose.prova2_pdm.R

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imagem = painterResource(id = R.drawable.uma_bike_preta)
        Image(
            painter = imagem,
            contentDescription = "Duas Bikes",
            modifier = Modifier
                .size(450.dp)
                .padding(16.dp)
        )

        Text(
            text = "Seja bem-vindo(a) à Loja\n de Bikes do Jeff!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}