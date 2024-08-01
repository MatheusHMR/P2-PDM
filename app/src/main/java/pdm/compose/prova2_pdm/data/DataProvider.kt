package pdm.compose.prova2_pdm.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import pdm.compose.prova2_pdm.repository.BikeRepository
import pdm.compose.prova2_pdm.repository.ClienteRepository

object DataProvider {
    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val bikesCollection: CollectionReference by lazy { firestore.collection("bikes") }
    val bikeRepository: BikeRepository by lazy { BikeRepository( bikesCollection ) }

    private val clientesCollection: CollectionReference by lazy { firestore.collection("clientes") }
    val clienteRepository: ClienteRepository by lazy { ClienteRepository( clientesCollection ) }


    suspend fun globalBackup(): String {
        val bikes = bikeRepository.getAll()
        val clientes = clienteRepository.getAll()

        val bikesString = bikes.joinToString(separator = "\n") { it.toString() }
        val clientesString = clientes.joinToString(separator = "\n") { it.toString() }

        return "Bikes:\n$bikesString\n\nClientes:\n$clientesString"
    }
}