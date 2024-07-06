package pdm.compose.prova2_pdm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.compose.prova2_pdm.data.DataProvider
import pdm.compose.prova2_pdm.model.Bike

class BikeViewModel : ViewModel() {
    private val _bikes = MutableStateFlow<List<Bike>>(emptyList())
    val bikes: StateFlow<List<Bike>> = _bikes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        buscarBikes()
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

    fun getBikeById(bikeId: String): Bike? {
        // Busca a bike na lista existente
        return _bikes.value.find { it.codigo == bikeId }
    }
}