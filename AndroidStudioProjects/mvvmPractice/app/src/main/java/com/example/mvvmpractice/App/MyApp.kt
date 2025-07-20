package com.example.mvvmpractice.App

import android.app.Application
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}