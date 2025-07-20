package com.example.mvvmpractice.Model

import androidx.wear.compose.material.dialog.Alert
import kotlinx.serialization.Serializable

data class InfoCard(
    val title: String = "Loading",
    val description: String = "Loading",
    val category: String = "Loading"
)

@Serializable
data class InfoWeather(
    val address: String = "Loading",
    val currentConditions: CurrentConditions = CurrentConditions(0.0)
)

@Serializable
data class CurrentConditions(
    val temp: Double
)



data class MyWeather(
    val data: InfoWeather = InfoWeather(),
    val isLoading: Boolean = false
)

data class MyCard(
    val data: InfoCard = InfoCard(),
    val isLoading: Boolean = false
)
