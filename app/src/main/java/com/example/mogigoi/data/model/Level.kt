package com.example.mogigoi.data.model

data class Level(
    val id: String,           // "N5", "N4", "N3", "N2", "N1"
    val name: String,
    val description: String,
    val totalLessons: Int = 0,
    val totalWords: Int = 0,
    val learnedWords: Int = 0
) {
    val progressPercent: Int
        get() = if (totalWords > 0) (learnedWords * 100) / totalWords else 0
}