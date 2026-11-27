package com.example.mydatasiswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import java.io.IOException

class EntryViewModel(private val repositoryDataSiswa: RepositoryDataSiswa) : ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    fun updateUiState(detailSiswa: DetailSiswa) {
        if (uiStateSiswa.isSaving) return

        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa),
        )
    }

    private fun validasiInput(detailSiswa: DetailSiswa): Boolean = with(detailSiswa) {
        nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
    }

    suspend fun addSiswa(): Boolean {
        val state = uiStateSiswa
        if (!state.isEntryValid || state.isSaving) return false

        uiStateSiswa = state.copy(isSaving = true, saveFailed = false)
        try {
            val response = repositoryDataSiswa.postDataSiswa(state.detailSiswa.toDataSiswa())
            response.errorBody()?.close()
            uiStateSiswa = uiStateSiswa.copy(saveFailed = !response.isSuccessful)
            return response.isSuccessful
        } catch (_: IOException) {
            uiStateSiswa = uiStateSiswa.copy(saveFailed = true)
            return false
        } finally {
            uiStateSiswa = uiStateSiswa.copy(isSaving = false)
        }
    }
}
