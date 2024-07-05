package pdm.compose.prova2_pdm.data

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pdm.compose.prova2_pdm.model.Bike


class BikeRepository(
    private val bikeCollection: CollectionReference
) {
    suspend fun addBike(bike: Bike) {
        try {
            val newDocRef = bikeCollection.document()
            val bikeWithId = bike.copy(codigo = newDocRef.id)
            newDocRef.set(bikeWithId).await()
        } catch (e: Exception){
            Log.e("BikeRepository", "Error inside BikeRepository - addBike")
        }
    }

    suspend fun getAll() : List<Bike> {
        return try {
            bikeCollection.get().await().documents
                .mapNotNull { it.toObject(Bike::class.java) }
        } catch (e: Exception){
            Log.e("BikeRepository", "Error inside BikeRepository - getAllBikes")
            emptyList()
        }
    }

    suspend fun getOneById() {
        try {

        } catch (e: Exception){
            Log.e("BikeRepository", "Error inside BikeRepository - getBikeById")
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