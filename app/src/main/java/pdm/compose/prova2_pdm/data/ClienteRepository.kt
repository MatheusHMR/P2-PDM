package pdm.compose.prova2_pdm.data

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import pdm.compose.prova2_pdm.model.Cliente

class ClienteRepository(
    private val clientesCollection: CollectionReference
) {
    suspend fun addCliente(cliente: Cliente) {
        try {
            val newDocRef = clientesCollection.document()
            val clienteWithId = cliente.copy(clienteId = newDocRef.id)
            newDocRef.set(clienteWithId).addOnSuccessListener {
                Log.d("ClienteRepository", "Added cliente: $cliente successfully!")
            }.await()
        } catch (e: Exception){
            Log.e("ClienteRepository", "Error inside ClienteRepository - addCliente")
        }
    }

    suspend fun getAll() : List<Cliente> {
        return try {
            clientesCollection.get().await().documents
                .mapNotNull { it.toObject(Cliente::class.java) }
        } catch (e: Exception){
            Log.e("ClienteRepository", "Error inside ClienteRepository - getAllClientes")
            emptyList()
        }
    }

    suspend fun getOneById() {
        try {

        } catch (e: Exception){
            Log.e("ClienteRepository", "Error inside ClienteRepository - getOneClienteById")
        }
    }

    suspend fun update() {
        try {

        } catch (e: Exception){
            Log.e("BikeRepository", "Error inside BikeRepository - updateBike")
        }
    }

    suspend fun delete() {
        try {

        } catch (e: Exception){
            Log.e("BikeRepository", "Error inside BikeRepository - deleteBike")
        }
    }
}