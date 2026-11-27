package com.example.mydatasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.consumeWindowInsets
import com.example.mydatasiswa.navigasi.DataSiswaApp
import com.example.mydatasiswa.ui.IzinJaringanLokal
import com.example.mydatasiswa.ui.theme.MydatasiswaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MydatasiswaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IzinJaringanLokal(
                        modifier = Modifier.padding(innerPadding).consumeWindowInsets(innerPadding)
                    ) { modifier ->
                        DataSiswaApp(modifier = modifier)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MydatasiswaTheme {
        Greeting("Android")
    }
}