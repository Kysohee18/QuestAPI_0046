package com.example.mydatasiswa.navigasi

import androidx.annotation.StringRes

interface DestinasiNavigasi {
    val route: String
    @get:StringRes
    val titleRes: Int
}
