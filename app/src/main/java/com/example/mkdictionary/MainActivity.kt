package com.example.mkdictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mkdictionary.ui.DictionaryScreen
import com.example.mkdictionary.ui.theme.MKDictionaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MKDictionaryTheme {
                DictionaryScreen()
            }
        }
    }
}