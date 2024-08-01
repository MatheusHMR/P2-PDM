package pdm.compose.prova2_pdm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.compose.prova2_pdm.data.DataProvider
import pdm.compose.prova2_pdm.domain.usecases.DeleteClienteAndBikesUseCase
import pdm.compose.prova2_pdm.model.Cliente

class ClienteViewModel : ViewModel() {
    private val _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes: StateFlow<List<Cliente>> = _clientes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedCliente = MutableStateFlow(Cliente.EMPTY)
    val selectedCliente: StateFlow<Cliente> = _selectedCliente.asStateFlow()

    private val _filteredClientes = MutableStateFlow<List<Cliente>>(emptyList())
    val filteredClientes: StateFlow<List<Cliente>> = _filteredClientes.asStateFlow()

    init {
        buscarClientes()
    }

    fun buscarClientes() {
        viewModelScope.launch {
            _isLoading.value = true
            _clientes.value = DataProvider.clienteRepository.getAll()
            _isLoading.value = false
        }
    }

    fun adicionarCliente(cliente: Cliente) {
        viewModelScope.launch {
            _isLoading.value = true
            DataProvider.clienteRepository.addCliente(cliente)
            _clientes.value = DataProvider.clienteRepository.getAll()
            _isLoading.value = false
        }
    }

    fun atualizarCliente(cliente: Cliente) {
        viewModelScope.launch {
            _isLoading.value = true
            DataProvider.clienteRepository.update(cliente)
            _clientes.value = DataProvider.clienteRepository.getAll()
            _isLoading.value = false
        }
    }

    fun excluirCliente(clienteId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val deleted = DeleteClienteAndBikesUseCase.invoke(clienteId)
            if(deleted) {
                _clientes.value = DataProvider.clienteRepository.getAll()
            }
            _isLoading.value = false
        }
    }

    fun getClienteById(clienteId: String): Cliente? {
        // Busca o cliente na lista existente
        return _clientes.value.find { it.clienteId == clienteId }
    }

    fun setSelectedCliente(cliente: Cliente) {
        _selectedCliente.value = cliente
    }

    fun filtrarClientes(texto: String) {
        Log.d("ClienteViewModel", "Texto: $texto")
        viewModelScope.launch{
            _isLoading.value = true
            if(texto.trim().isBlank()) {
                _filteredClientes.value = emptyList()
            } else {
                _filteredClientes.value = _clientes.value.filter { cliente ->
                    cliente.cpf.contains(texto, ignoreCase = true) ||
                            cliente.nome.contains(texto, ignoreCase = true)
                }
            }
            _isLoading.value = false
        }
    }
}