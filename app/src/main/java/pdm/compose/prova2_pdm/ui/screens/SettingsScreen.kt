package pdm.compose.prova2_pdm.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pdm.compose.prova2_pdm.data.DataProvider
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    var backupContent by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = {
            coroutineScope.launch {
                performBackup(context)
                backupContent = getBackupContent(context)
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Fazer Backup")
        }

        backupContent?.let {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Backup realizado com sucesso. Conteúdo do backup:")
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    value = TextFieldValue(it),
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    enabled = false
                )
            }
        }
    }
}

suspend fun performBackup(context: Context): String {
    val backupData = DataProvider.globalBackup()
    val backupFile = File(context.filesDir, "backup.txt")
    return try {
        withContext(Dispatchers.IO) {
            FileWriter(backupFile).use { it.write(backupData) }
        }
        backupData
    } catch (e: IOException) {
        "Erro ao realizar o backup: ${e.message}"
    }
}

suspend fun getBackupContent(context: Context): String {
    val backupFile = File(context.filesDir, "backup.txt")
    return try {
        withContext(Dispatchers.IO) {
            FileReader(backupFile).use { it.readText() }
        }
    } catch (e: IOException) {
        "Erro ao ler o backup: ${e.message}"
    }
}