package com.example.mydatasiswa.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mydatasiswa.repositori.AplikasiDataSiswa

private fun CreationExtras.aplikasiDataSiswa() = this[APPLICATION_KEY] as AplikasiDataSiswa

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(aplikasiDataSiswa().container.repositoryDataSiswa) }
        initializer { EntryViewModel(aplikasiDataSiswa().container.repositoryDataSiswa) }
    }
}
