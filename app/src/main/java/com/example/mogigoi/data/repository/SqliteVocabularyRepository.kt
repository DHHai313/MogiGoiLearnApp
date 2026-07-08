package com.example.mogigoi.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.mogigoi.data.db.DatabaseHelper
import com.example.mogigoi.data.model.*

/**
 * Real implementation of [VocabularyRepository] backed by the pre-built
 * SQLite database bundled inside assets/database/vocabulary.db.
 */
class SqliteVocabularyRepository(context: Context) : VocabularyRepository {

    private val dbHelper = DatabaseHelper(context)

    // ── Levels ────────────────────────────────────────────────────────────────

    override fun getLevels(): List<Level> {
        val db = dbHelper.openDatabase()
        val list = mutableListOf<Level>()
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_LEVELS,
            null, null, null, null, null,
            "id ASC"
        )
        cursor.use {
            while (it.moveToNext()) {
                list += Level(
                    id           = it.getString(it.getColumnIndexOrThrow("id")),
                    name         = it.getString(it.getColumnIndexOrThrow("name")),
                    description  = it.getString(it.getColumnIndexOrThrow("description")) ?: "",
                    totalLessons = it.getInt(it.getColumnIndexOrThrow("total_lessons")),
                    totalWords   = it.getInt(it.getColumnIndexOrThrow("total_words")),
                    learnedWords = it.getInt(it.getColumnIndexOrThrow("learned_words"))
                )
            }
        }
        db.close()
        return list
    }

    // ── Lessons ───────────────────────────────────────────────────────────────

    override fun getLessonsByLevel(levelId: String): List<Lesson> {
        val db = dbHelper.openDatabase()
        val list = mutableListOf<Lesson>()
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_LESSONS,
            null,
            "level_id = ?", arrayOf(levelId),
            null, null,
            "id ASC"
        )
        cursor.use {
            while (it.moveToNext()) {
                list += Lesson(
                    id           = it.getInt(it.getColumnIndexOrThrow("id")),
                    levelId      = it.getString(it.getColumnIndexOrThrow("level_id")),
                    title        = it.getString(it.getColumnIndexOrThrow("title")),
                    topic        = it.getString(it.getColumnIndexOrThrow("topic")) ?: "",
                    totalWords   = it.getInt(it.getColumnIndexOrThrow("total_words")),
                    learnedWords = it.getInt(it.getColumnIndexOrThrow("learned_words"))
                )
            }
        }
        db.close()
        return list
    }

    // ── Vocabulary ────────────────────────────────────────────────────────────

    override fun getVocabularyByLesson(lessonId: Int): List<Vocabulary> {
        val db = dbHelper.openDatabase()
        val list = mutableListOf<Vocabulary>()
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_VOCABULARY,
            null,
            "lesson_id = ?", arrayOf(lessonId.toString()),
            null, null, null
        )
        cursor.use {
            while (it.moveToNext()) {
                list += it.toVocabulary()
            }
        }
        db.close()
        return list
    }

    override fun getVocabularyByLevel(levelId: String): List<Vocabulary> {
        val db = dbHelper.openDatabase()
        val list = mutableListOf<Vocabulary>()
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_VOCABULARY,
            null,
            "level_id = ?", arrayOf(levelId),
            null, null, null
        )
        cursor.use {
            while (it.moveToNext()) {
                list += it.toVocabulary()
            }
        }
        db.close()
        return list
    }

    // ── Stats ─────────────────────────────────────────────────────────────────

    override fun getStudyStats(): StudyStats {
        val db = dbHelper.openDatabase()

        var totalWords   = 0
        var learnedWords = 0

        db.rawQuery(
            "SELECT SUM(total_words), SUM(learned_words) FROM ${DatabaseHelper.TABLE_LEVELS}",
            null
        ).use {
            if (it.moveToFirst()) {
                totalWords   = it.getInt(0)
                learnedWords = it.getInt(1)
            }
        }

        db.close()
        val completionPercent = if (totalWords > 0) (learnedWords * 100) / totalWords else 0
        return StudyStats(totalWords, learnedWords, completionPercent)
    }

    // ── Quiz ──────────────────────────────────────────────────────────────────

    override fun generateQuiz(vocabulary: List<Vocabulary>): List<QuizQuestion> {
        if (vocabulary.size < 4) return emptyList()

        // Fetch all meanings from DB for distractor pool
        val db = dbHelper.openDatabase()
        val allMeanings = mutableListOf<String>()
        db.rawQuery(
            "SELECT DISTINCT meaning FROM ${DatabaseHelper.TABLE_VOCABULARY}",
            null
        ).use {
            while (it.moveToNext()) {
                allMeanings += it.getString(0)
            }
        }
        db.close()

        return vocabulary.shuffled().map { word ->
            val wrongOptions = allMeanings
                .filter { it != word.meaning }
                .shuffled()
                .take(3)
            val options = (listOf(word.meaning) + wrongOptions).shuffled()

            QuizQuestion(
                vocabularyId  = word.id,
                kanji         = word.kanji,
                furigana      = word.furigana,
                correctAnswer = word.meaning,
                options       = options
            )
        }
    }

    // ── Progress tracking ─────────────────────────────────────────────────────

    override fun markVocabularyAsLearned(vocabularyId: Int) {
        val db = dbHelper.openDatabase()
        val values = ContentValues().apply {
            put("is_learned", 1)
        }
        db.update(
            DatabaseHelper.TABLE_VOCABULARY,
            values,
            "id = ?",
            arrayOf(vocabularyId.toString())
        )
        db.close()
    }

    override fun markLessonCompleted(lessonId: Int) {
        val db = dbHelper.openDatabase()
        try {
            db.beginTransaction()

            // 1. Mark all vocabulary in this lesson as learned
            val vocabValues = ContentValues().apply { put("is_learned", 1) }
            db.update(
                DatabaseHelper.TABLE_VOCABULARY,
                vocabValues,
                "lesson_id = ?",
                arrayOf(lessonId.toString())
            )

            // 2. Count how many words are now learned in this lesson
            var learnedInLesson = 0
            var levelId = ""
            db.rawQuery(
                """SELECT COUNT(*), level_id FROM ${DatabaseHelper.TABLE_VOCABULARY}
                   WHERE lesson_id = ? AND is_learned = 1
                   GROUP BY level_id""",
                arrayOf(lessonId.toString())
            ).use {
                if (it.moveToFirst()) {
                    learnedInLesson = it.getInt(0)
                    levelId = it.getString(1)
                }
            }

            // 3. Update lesson's learned_words counter
            val lessonValues = ContentValues().apply {
                put("learned_words", learnedInLesson)
            }
            db.update(
                DatabaseHelper.TABLE_LESSONS,
                lessonValues,
                "id = ?",
                arrayOf(lessonId.toString())
            )

            // 4. Recalculate level's total learned_words from all its lessons
            if (levelId.isNotEmpty()) {
                var totalLearnedInLevel = 0
                db.rawQuery(
                    """SELECT SUM(learned_words) FROM ${DatabaseHelper.TABLE_LESSONS}
                       WHERE level_id = ?""",
                    arrayOf(levelId)
                ).use {
                    if (it.moveToFirst()) {
                        totalLearnedInLevel = it.getInt(0)
                    }
                }

                val levelValues = ContentValues().apply {
                    put("learned_words", totalLearnedInLevel)
                }
                db.update(
                    DatabaseHelper.TABLE_LEVELS,
                    levelValues,
                    "id = ?",
                    arrayOf(levelId)
                )
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
            db.close()
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private fun Cursor.toVocabulary() = Vocabulary(
        id               = getInt(getColumnIndexOrThrow("id")),
        lessonId         = getInt(getColumnIndexOrThrow("lesson_id")),
        levelId          = getString(getColumnIndexOrThrow("level_id")),
        kanji            = getString(getColumnIndexOrThrow("kanji"))            ?: "",
        furigana         = getString(getColumnIndexOrThrow("furigana"))         ?: "",
        meaning          = getString(getColumnIndexOrThrow("meaning")),
        exampleJapanese  = getString(getColumnIndexOrThrow("example_japanese")) ?: "",
        exampleVietnamese= getString(getColumnIndexOrThrow("example_vietnamese")) ?: "",
        isLearned        = getInt(getColumnIndexOrThrow("is_learned")) == 1
    )
}
