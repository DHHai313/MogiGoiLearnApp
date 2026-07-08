package com.example.mogigoi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mogigoi.data.model.*
import com.example.mogigoi.data.repository.RepositoryProvider
import com.example.mogigoi.data.repository.VocabularyRepository

class QuizViewModel : ViewModel() {

    private val repository: VocabularyRepository = RepositoryProvider.repository

    private var questions: List<QuizQuestion> = emptyList()
    private val resultItems = mutableListOf<QuizResultItem>()

    /** The lessonId that was loaded, -1 if loaded by level. */
    private var currentLessonId: Int = -1

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex

    private val _currentQuestion = MutableLiveData<QuizQuestion?>()
    val currentQuestion: LiveData<QuizQuestion?> = _currentQuestion

    private val _correctCount = MutableLiveData(0)
    val correctCount: LiveData<Int> = _correctCount

    private val _totalCount = MutableLiveData(0)
    val totalCount: LiveData<Int> = _totalCount

    private val _quizFinished = MutableLiveData(false)
    val quizFinished: LiveData<Boolean> = _quizFinished

    private val _quizResult = MutableLiveData<QuizResult?>()
    val quizResult: LiveData<QuizResult?> = _quizResult

    fun loadQuiz(lessonId: Int) {
        currentLessonId = lessonId
        val vocabulary = repository.getVocabularyByLesson(lessonId)
        startQuiz(vocabulary)
    }

    fun loadQuizByLevel(levelId: String) {
        currentLessonId = -1
        val vocabulary = repository.getVocabularyByLevel(levelId)
        startQuiz(vocabulary)
    }

    private fun startQuiz(vocabulary: List<Vocabulary>) {
        questions = repository.generateQuiz(vocabulary)
        _totalCount.value = questions.size
        _currentQuestionIndex.value = 0
        _correctCount.value = 0
        _quizFinished.value = false
        _quizResult.value = null
        resultItems.clear()
        updateCurrentQuestion()
    }

    fun submitAnswer(answer: String): Boolean {
        val question = _currentQuestion.value ?: return false
        val isCorrect = answer == question.correctAnswer

        if (isCorrect) {
            _correctCount.value = (_correctCount.value ?: 0) + 1
        }

        resultItems.add(QuizResultItem(question, answer, isCorrect))
        return isCorrect
    }

    fun nextQuestion() {
        val idx = _currentQuestionIndex.value ?: 0
        if (idx < questions.size - 1) {
            _currentQuestionIndex.value = idx + 1
            updateCurrentQuestion()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        // Save progress — mark correctly answered vocab as learned
        saveProgress()

        _quizResult.value = QuizResult(
            totalQuestions = questions.size,
            correctAnswers = _correctCount.value ?: 0,
            results = resultItems.toList()
        )
        _quizFinished.value = true
    }

    /**
     * Save quiz progress. If loaded by lesson and the user answered ALL
     * questions correctly, mark the whole lesson as completed.
     * Otherwise, mark only the correctly answered vocabulary as learned.
     */
    private fun saveProgress() {
        val correctVocabIds = resultItems
            .filter { it.isCorrect }
            .map { it.question.vocabularyId }

        if (currentLessonId != -1 && correctVocabIds.size == questions.size) {
            // Perfect score — mark entire lesson complete
            repository.markLessonCompleted(currentLessonId)
        } else {
            // Mark only the correct ones as learned
            correctVocabIds.forEach { vocabId ->
                repository.markVocabularyAsLearned(vocabId)
            }
            // If loaded by lesson, update the lesson counter too
            if (currentLessonId != -1) {
                repository.markLessonCompleted(currentLessonId)
            }
        }
    }

    fun restartQuiz() {
        resultItems.clear()
        _currentQuestionIndex.value = 0
        _correctCount.value = 0
        _quizFinished.value = false
        _quizResult.value = null
        questions = questions.shuffled()
        updateCurrentQuestion()
    }

    private fun updateCurrentQuestion() {
        val idx = _currentQuestionIndex.value ?: 0
        _currentQuestion.value = questions.getOrNull(idx)
    }
}
