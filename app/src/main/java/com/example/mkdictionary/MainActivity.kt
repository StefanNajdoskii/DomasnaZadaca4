package com.example.mkdictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mkdictionary.ui.DictionaryScreen
import com.example.mkdictionary.ui.Pathway1Screen
import com.example.mkdictionary.ui.Pathway2Screen
import com.example.mkdictionary.ui.theme.MKDictionaryTheme

sealed class Screen(val label: String, val icon: ImageVector) {
    object Pathway1   : Screen("Pathway 1", Icons.Default.Code)
    object Pathway2   : Screen("Pathway 2", Icons.Default.Palette)
    object Dictionary : Screen("Речник",    Icons.Default.MenuBook)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MKDictionaryTheme {
                var current by remember { mutableStateOf<Screen>(Screen.Pathway1) }
                val screens = listOf(Screen.Pathway1, Screen.Pathway2, Screen.Dictionary)

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    selected = current == screen,
                                    onClick  = { current = screen },
                                    icon     = { Icon(screen.icon, contentDescription = screen.label) },
                                    label    = { Text(screen.label) }
                                )
                            }
                        }
                    }
                ) { padding ->
                    when (current) {
                        is Screen.Pathway1   -> Pathway1Screen()
                        is Screen.Pathway2   -> Pathway2Screen()
                        is Screen.Dictionary -> DictionaryScreen()
                    }
                }
            }
        }
    }
}