package com.example.mvvmpractice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpractice.Model.Repository
import com.example.mvvmpractice.Model.MyCard
import com.example.mvvmpractice.Model.MyWeather
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val cardRepository: Repository) : ViewModel() {

    private val _cardState = MutableStateFlow(MyCard())
    val cardState = _cardState.asStateFlow()

    private val _weatherState = MutableStateFlow(MyWeather())
    val weatherState = _weatherState.asStateFlow()

    init {
        getWeather("Karaganda")
    }

    fun loadCardData() {
        viewModelScope.launch {
            _cardState.value = _cardState.value.copy(isLoading = true)
            delay(2000)
            val infoCard = cardRepository.getData()
            _cardState.value = MyCard(data = infoCard, isLoading = false)
        }
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            _weatherState.value = _weatherState.value.copy(isLoading = true)
            delay(2000)
            val infoWeather = cardRepository.getTemperature(city)
            _weatherState.value = MyWeather(data = infoWeather, isLoading = false)
        }
    }
}

