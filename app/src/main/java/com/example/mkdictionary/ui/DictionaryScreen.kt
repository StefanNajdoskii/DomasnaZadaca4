@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mkdictionary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mkdictionary.data.dictionaryWords
import com.example.mkdictionary.model.WordEntry




@Composable
fun DictionaryScreen() {
    var words             by remember { mutableStateOf(dictionaryWords) }
    var searchQuery       by remember { mutableStateOf("") }
    var selectedCategory  by remember { mutableStateOf("Сите") }
    var showFavoritesOnly by remember { mutableStateOf(false) }

    val categories = listOf("Сите") +
            dictionaryWords.map { it.category }.distinct().sorted()

    val filtered = words.filter { entry ->
        val matchSearch = entry.macedonian.contains(searchQuery, ignoreCase = true) ||
                entry.english.contains(searchQuery, ignoreCase = true)
        val matchCat = selectedCategory == "Сите" || entry.category == selectedCategory
        val matchFav = !showFavoritesOnly || entry.isFavorite
        matchSearch && matchCat && matchFav
    }

    fun toggleFavorite(entry: WordEntry) {
        words = words.map {
            if (it.id == entry.id) it.copy(isFavorite = !it.isFavorite) else it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MenuBook, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("МК–EN Речник", fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = { showFavoritesOnly = !showFavoritesOnly }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Омилени",
                            tint = if (showFavoritesOnly)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value         = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder   = { Text("Пребарај македонски или англиски...") },
                leadingIcon   = { Icon(Icons.Default.Search, null) },
                trailingIcon  = {
                    if (searchQuery.isNotEmpty())
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, null)
                        }
                },
                modifier   = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true,
                shape      = MaterialTheme.shapes.large
            )

            LazyRow(
                contentPadding        = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick  = { selectedCategory = cat },
                        label    = { Text(cat) }
                    )
                }
            }

            Text(
                "${filtered.size} зборови",
                style    = MaterialTheme.typography.labelMedium,
                color    = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            LazyColumn(
                modifier            = Modifier.fillMaxSize(),
                contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filtered, key = { it.id }) { entry ->
                    WordCard(
                        entry            = entry,
                        onFavoriteToggle = { toggleFavorite(it) }
                    )
                }
                if (filtered.isEmpty()) {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("🔍", style = MaterialTheme.typography.displayLarge)
                                Spacer(Modifier.height(12.dp))
                                Text("Нема резултати",
                                    style = MaterialTheme.typography.titleMedium)
                                Text("Обиди се со друг збор",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}
