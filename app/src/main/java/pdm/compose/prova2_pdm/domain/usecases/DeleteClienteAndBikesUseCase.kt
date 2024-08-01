package pdm.compose.prova2_pdm.domain.usecases

import android.util.Log
import pdm.compose.prova2_pdm.data.DataProvider
import pdm.compose.prova2_pdm.util.EventBus

class DeleteClienteAndBikesUseCase {
    companion object {
        suspend operator fun invoke(clienteId: String): Boolean {
            return try {
                // Excluir as bikes relacionadas ao cliente
                DataProvider.bikeRepository.deleteBikesByClienteId(clienteId)

                // Excluir o cliente
                DataProvider.clienteRepository.delete(clienteId)
                Log.d("DeleteClienteAndBikesUseCase", "Excluindo clienteId: $clienteId")
                EventBus.notifyClienteDeleted() // Notificar a exclusão do cliente
                true
            } catch (e: Exception) {
                Log.e("DeleteClienteAndBikes", "Falha ao excluir", e)
                false
            }
        }
    }
}