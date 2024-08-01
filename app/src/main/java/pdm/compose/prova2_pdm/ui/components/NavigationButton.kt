package pdm.compose.prova2_pdm.ui.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.Navigation


@Composable
fun NavigationButton (
    navController: NavController, // Add NavController parameter route: String,
    // Use route instead of targetActivity
    route: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    popUpTo: String? = null, //Optional: route to pop up to, or rather, to navigate
    popUpToInclusive: Boolean = false // Optional: Whether to include
// the popUpTo route in the removal
) {
    Button(
        onClick = {
            navController.navigate(route) {
                popUpTo?.let {
                    popUpTo(route = route) {
                        inclusive = popUpToInclusive
                    }
                }
            }
        },
        modifier = modifier
    ) {
        Text(text = buttonText)
    }
}