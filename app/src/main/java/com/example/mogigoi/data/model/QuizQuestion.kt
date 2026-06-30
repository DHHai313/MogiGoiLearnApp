package com.example.mogigoi.data.model

data class QuizQuestion(
    val vocabularyId: Int,
    val kanji: String,
    val furigana: String,
    val correctAnswer: String,
    val options: List<String>  // 4 options including correct answer
)
