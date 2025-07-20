package com.example.mvvmpractice.Model
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

interface CardAPI{
    fun getData() : InfoCard
    suspend fun getTemperature(city: String) : InfoWeather
}

class Repository @Inject constructor(private val httpClient: HttpClient) : CardAPI {

    override fun getData() : InfoCard {
        return InfoCard(
            title = "Вентилятор",
            description = "Быстрый, хорошо охлаждает",
            category = "Электроника")
    }

    override suspend fun getTemperature(city: String): InfoWeather {
        val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${city}?unitGroup=us&key=8KCPJFCYREGNCDZGHUF4Z6LKY&contentType=json"
        val response: InfoWeather = httpClient.get(url).body()
        return response
    }

}