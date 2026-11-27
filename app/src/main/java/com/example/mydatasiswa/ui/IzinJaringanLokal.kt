package com.example.mydatasiswa.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect

@Composable
fun IzinJaringanLokal(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
) {
    val context = LocalContext.current
    val permission = Manifest.permission.ACCESS_LOCAL_NETWORK
    var izinDiberikan by remember(context) {
        mutableStateOf(context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        izinDiberikan = it
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        izinDiberikan = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    LaunchedEffect(Unit) {
        if (!izinDiberikan) launcher.launch(permission)
    }

    if (izinDiberikan) {
        content(modifier)
    } else {
        PesanIzinJaringan(modifier = modifier, mintaIzin = { launcher.launch(permission) })
    }
}
