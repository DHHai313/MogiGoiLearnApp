package com.example.mogigoi.data.repository

import com.example.mogigoi.data.model.*

/**
 * Interface Repository cho từ vựng JLPT.
 *
 * Để chuyển sang Room Database:
 * 1. Tạo VocabularyDatabase (RoomDatabase)
 * 2. Tạo VocabularyDao, LessonDao, LevelDao
 * 3. Tạo RoomVocabularyRepository implement interface này
 * 4. Inject RoomVocabularyRepository thay FakeVocabularyRepository
 */
interface VocabularyRepository {
    fun getLevels(): List<Level>
    fun getLessonsByLevel(levelId: String): List<Lesson>
    fun getVocabularyByLesson(lessonId: Int): List<Vocabulary>
    fun getVocabularyByLevel(levelId: String): List<Vocabulary>
    fun getStudyStats(): StudyStats
    fun generateQuiz(vocabulary: List<Vocabulary>): List<QuizQuestion>

    /** Mark a single vocabulary word as learned (is_learned = 1). */
    fun markVocabularyAsLearned(vocabularyId: Int)

    /** Mark all vocabulary in a lesson as learned and update counters. */
    fun markLessonCompleted(lessonId: Int)
}
