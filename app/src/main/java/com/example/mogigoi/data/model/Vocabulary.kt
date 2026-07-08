package com.example.mogigoi.data.model

data class Vocabulary(
    val id: Int,
    val lessonId: Int,
    val levelId: String,
    val kanji: String,
    val furigana: String,
    val meaning: String,
    val exampleJapanese: String,
    val exampleVietnamese: String,
    val isLearned: Boolean = false
)
