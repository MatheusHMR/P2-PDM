package pdm.compose.prova2_pdm.repository

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

//    suspend fun getOneById(clienteId: String): {
//       return try {
//            clientesCollection.document(clienteId).get().addOnSuccessListener {
//                Log.d("ClienteRepository", "Got client of $clienteId successfully!")
//            }.await()
//        } catch (e: Exception){
//            Log.e("ClienteRepository", "Error inside ClienteRepository - getOneClienteById")
//        }
//    }

    suspend fun update(cliente: Cliente) {
        try {
            clientesCollection.document(cliente.clienteId).set(cliente)
                .addOnSuccessListener {
                Log.d("ClienteRepository", "Updated cliente: $cliente successfully")
            }.await()
        } catch (e: Exception){
            Log.e("ClienteRepository", "Error inside ClienteRepository - updateCliente")
        }
    }

    suspend fun delete(clienteId: String) {
        try {
            clientesCollection.document(clienteId).delete().addOnSuccessListener {
                Log.d("ClienteRepository", "Deleted cliente: $clienteId")
            }.await()
        } catch (e: Exception) {
            Log.e("ClienteRepository", "Error inside ClienteRepository - deleteCliente")
        }
    }
}