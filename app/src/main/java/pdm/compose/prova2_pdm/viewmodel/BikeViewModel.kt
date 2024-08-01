package pdm.compose.prova2_pdm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pdm.compose.prova2_pdm.data.DataProvider
import pdm.compose.prova2_pdm.model.Bike
import pdm.compose.prova2_pdm.model.Cliente
import pdm.compose.prova2_pdm.util.EventBus

class BikeViewModel : ViewModel() {

    private val _bikes = MutableStateFlow<List<Bike>>(emptyList())
    val bikes: StateFlow<List<Bike>> = _bikes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedBike = MutableStateFlow(Bike.EMPTY)
    val selectedBike: StateFlow<Bike> = _selectedBike.asStateFlow()

    private val _filteredBikes = MutableStateFlow<List<Bike>>(emptyList())
    val filteredBikes: StateFlow<List<Bike>> = _filteredBikes.asStateFlow()

    private val _selectedClienteId = MutableStateFlow("")
    val selectedClienteId: StateFlow<String> = _selectedClienteId.asStateFlow()

    init {
        buscarBikes()
        viewModelScope.launch {
            EventBus.clienteDeletedFlow.collect {
                buscarBikes()
            }
        }
    }

    fun buscarBikes() {
        viewModelScope.launch {
            _isLoading.value = true
            _bikes.value = DataProvider.bikeRepository.getAll()
            _isLoading.value = false
        }
    }

    fun adicionarBike(bike: Bike) {
        viewModelScope.launch {
            _isLoading.value = true
            DataProvider.bikeRepository.addBike(bike)
            _bikes.value = DataProvider.bikeRepository.getAll()
            _isLoading.value = false
        }
    }

    fun atualizarBike(bike: Bike) {
        viewModelScope.launch {
            _isLoading.value = true
            DataProvider.bikeRepository.update(bike)
            _bikes.value = DataProvider.bikeRepository.getAll()
            _isLoading.value = false
        }
    }

    fun excluirBike(codigoBike: String) {
        viewModelScope.launch {
            _isLoading.value = true
            DataProvider.bikeRepository.delete(codigoBike)
            _bikes.value = DataProvider.bikeRepository.getAll()
            _isLoading.value = false
        }
    }

    fun setSelectedBike(bike: Bike) {
        _selectedBike.value = bike
    }

    fun setChosenCliente(clienteId: String){
        _selectedClienteId.value = clienteId
    }

    fun filtrarBikes(texto: String, clienteViewModel: ClienteViewModel) {
        Log.d("BikeViewModel", "Texto: $texto")
        viewModelScope.launch {
            _isLoading.value = true
            if(texto.trim().isBlank()){
                _filteredBikes.value = emptyList()
            } else {
                _filteredBikes.value = _bikes.value.filter { bike ->
                    val cliente = clienteViewModel.getClienteById(bike.clienteId)
                    bike.codigo.contains(texto, ignoreCase = false) ||
                            bike.modelo.contains(texto, ignoreCase = true) ||
                            bike.aro.toString().contains(texto, ignoreCase = true) ||
                            cliente?.nome?.contains(texto, ignoreCase = true) ?: false ||
                            cliente?.cpf?.contains(texto, ignoreCase = true) ?: false
                }
                _isLoading.value = false
            }
        }
    }

    fun getBikeById(bikeId: String): Bike? {
        // Busca a bike na lista existente
        return _bikes.value.find { it.codigo == bikeId }
    }
}