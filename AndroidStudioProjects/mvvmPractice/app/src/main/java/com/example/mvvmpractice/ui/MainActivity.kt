package com.example.mvvmpractice.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmpractice.App.MyApp
import com.example.mvvmpractice.Model.MyWeather
import com.example.mvvmpractice.Model.Repository
import com.example.mvvmpractice.ViewModel.MainActivityViewModel
import com.example.mvvmpractice.ui.theme.MvvmPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmPracticeTheme {

                val mainActivityViewModel: MainActivityViewModel = hiltViewModel()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyCardComposable(viewModel = mainActivityViewModel)

                    Spacer(modifier = Modifier.size(20.dp))

                    MyWeatherComposable(viewModel = mainActivityViewModel)
                }
            }
        }
    }
}


@Composable
fun MyCardComposable(viewModel: MainActivityViewModel) {
    val cardData by viewModel.cardState.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        content = {

            if (cardData.isLoading == true){
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            else{
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(cardData.data.title, fontSize = 30.sp)
                    Text(cardData.data.description, fontSize = 23.sp)
                    Text(cardData.data.category, fontSize = 23.sp)
                }
            }



        })

    Button(onClick = { viewModel.loadCardData() }) {
        Text("Update data")
    }

}

@Composable
fun MyWeatherComposable(viewModel: MainActivityViewModel) {
    val weatherData by viewModel.weatherState.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        content = {

            if (weatherData.isLoading == true){
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            else{
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(weatherData.data.address, fontSize = 30.sp)
                    Text(
                        (weatherData.data.currentConditions.temp?.toString() + "°C")
                            ?: "Температура: N/A", fontSize = 23.sp)
                }
            }



        })

}



