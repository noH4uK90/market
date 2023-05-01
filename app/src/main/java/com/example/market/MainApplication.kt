package com.example.market

import android.app.Application
import com.example.market.data.AppContainer
import com.example.market.data.DefaultAppContainer

class MainApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}