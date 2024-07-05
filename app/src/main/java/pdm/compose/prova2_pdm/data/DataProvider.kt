package pdm.compose.prova2_pdm.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object DataProvider {
    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val bikesCollection: CollectionReference by lazy { firestore.collection("bikes") }
    val bikeRepository: BikeRepository by lazy { BikeRepository( bikesCollection ) }

    private val clientesCollection: CollectionReference by lazy { firestore.collection("clientes") }
    val clienteRepository: ClienteRepository by lazy { ClienteRepository( clientesCollection ) }
}