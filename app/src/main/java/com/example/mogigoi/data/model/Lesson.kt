package com.example.mogigoi.data.model

data class Lesson(
    val id: Int,
    val levelId: String,
    val title: String,
    val topic: String,
    val totalWords: Int,
    val learnedWords: Int = 0
) {
    val progressPercent: Int
        get() = if (totalWords > 0) (learnedWords * 100) / totalWords else 0

    val statusLabel: String
        get() = when {
            learnedWords == 0 -> "Chưa học"
            learnedWords == totalWords -> "Hoàn thành"
            else -> "Đang học"
        }
}