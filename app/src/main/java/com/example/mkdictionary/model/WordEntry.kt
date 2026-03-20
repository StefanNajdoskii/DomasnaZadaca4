package com.example.mkdictionary.model

data class WordEntry(
    val id: Int,
    val macedonian: String,
    val english: String,
    val category: String,
    val exampleMk: String,
    val exampleEn: String,
    val isFavorite: Boolean = false
)