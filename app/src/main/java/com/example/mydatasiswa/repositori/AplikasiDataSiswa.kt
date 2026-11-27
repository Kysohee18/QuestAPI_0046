package com.example.mydatasiswa.repositori

import android.app.Application

class AplikasiDataSiswa : Application() {
    lateinit var container: ContainerApp
        private set

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}
