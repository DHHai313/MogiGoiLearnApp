package com.example.mogigoi.data.repository

import com.example.mogigoi.data.fake.FakeDataSource
import com.example.mogigoi.data.model.*

class FakeVocabularyRepository : VocabularyRepository {

    override fun getLevels(): List<Level> = FakeDataSource.levels

    override fun getLessonsByLevel(levelId: String): List<Lesson> =
        FakeDataSource.getLessonsByLevel(levelId)

    override fun getVocabularyByLesson(lessonId: Int): List<Vocabulary> =
        FakeDataSource.getVocabularyByLesson(lessonId)

    override fun getVocabularyByLevel(levelId: String): List<Vocabulary> =
        FakeDataSource.getVocabularyByLevel(levelId)

    override fun getStudyStats(): StudyStats {
        val totalWords = FakeDataSource.vocabulary.size
        val learnedWords = FakeDataSource.lessons.sumOf { it.learnedWords }
        val completionPercent = if (totalWords > 0) (learnedWords * 100) / totalWords else 0
        return StudyStats(totalWords, learnedWords, completionPercent)
    }

    override fun generateQuiz(vocabulary: List<Vocabulary>): List<QuizQuestion> {
        if (vocabulary.size < 4) return emptyList()
        val allMeanings = FakeDataSource.vocabulary.map { it.meaning }.distinct()

        return vocabulary.shuffled().map { word ->
            val wrongOptions = allMeanings
                .filter { it != word.meaning }
                .shuffled()
                .take(3)
            val options = (listOf(word.meaning) + wrongOptions).shuffled()

            QuizQuestion(
                vocabularyId = word.id,
                kanji = word.kanji,
                furigana = word.furigana,
                correctAnswer = word.meaning,
                options = options
            )
        }
    }
}
