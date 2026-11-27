package com.example.mydatasiswa.viewmodel

import com.example.mydatasiswa.modeldata.DataSiswa

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false,
    val isSaving: Boolean = false,
    val saveFailed: Boolean = false,
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)

internal fun DetailSiswa.toDataSiswa() = DataSiswa(
    id = id,
    nama = nama.trim(),
    alamat = alamat.trim(),
    telpon = telpon.trim(),
)
