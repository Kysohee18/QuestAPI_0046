package com.example.mydatasiswa.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mydatasiswa.R

@Composable
internal fun PesanIzinJaringan(modifier: Modifier, mintaIzin: () -> Unit) {
    val context = LocalContext.current
    val padding = dimensionResource(R.dimen.padding_large)
    Column(
        modifier = modifier.fillMaxSize().padding(padding),
        verticalArrangement = Arrangement.spacedBy(padding, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.izin_jaringan_lokal), textAlign = TextAlign.Center)
        Button(onClick = mintaIzin) { Text(stringResource(R.string.btn_izin_jaringan)) }
        TextButton(onClick = {
            context.startActivity(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null),
                )
            )
        }) {
            Text(stringResource(R.string.pengaturan_aplikasi))
        }
    }
}
