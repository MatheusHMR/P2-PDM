package pdm.compose.prova2_pdm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object EventBus {
    private val _clienteDeletedFlow = MutableSharedFlow<Unit>()
    val clienteDeletedFlow = _clienteDeletedFlow.asSharedFlow()

    fun notifyClienteDeleted() {
        CoroutineScope(Dispatchers.IO).launch {
            _clienteDeletedFlow.emit(Unit)
        }
    }
}