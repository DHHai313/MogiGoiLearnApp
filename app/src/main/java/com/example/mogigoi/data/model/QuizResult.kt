package com.example.mogigoi.data.model

data class QuizResultItem(
    val question: QuizQuestion,
    val userAnswer: String,
    val isCorrect: Boolean
)

data class QuizResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val results: List<QuizResultItem>
) {
    val scorePercent: Int
        get() = if (totalQuestions > 0) (correctAnswers * 100) / totalQuestions else 0
}
