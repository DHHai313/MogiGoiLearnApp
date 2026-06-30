package com.example.mogigoi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mogigoi.data.model.Vocabulary
import com.example.mogigoi.data.repository.FakeVocabularyRepository
import com.example.mogigoi.data.repository.VocabularyRepository

class FlashCardViewModel : ViewModel() {

    private val repository: VocabularyRepository = FakeVocabularyRepository()

    private var allVocabulary: List<Vocabulary> = emptyList()

    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex

    private val _currentVocabulary = MutableLiveData<Vocabulary?>()
    val currentVocabulary: LiveData<Vocabulary?> = _currentVocabulary

    private val _isFlipped = MutableLiveData(false)
    val isFlipped: LiveData<Boolean> = _isFlipped

    private val _totalCount = MutableLiveData(0)
    val totalCount: LiveData<Int> = _totalCount

    /** Fires `true` once when the user moves past the very last card. */
    private val _isFinished = MutableLiveData(false)
    val isFinished: LiveData<Boolean> = _isFinished

    fun loadVocabulary(lessonId: Int) {
        allVocabulary = repository.getVocabularyByLesson(lessonId)
        _totalCount.value = allVocabulary.size
        _currentIndex.value = 0
        updateCurrentWord()
    }

    fun loadVocabularyByLevel(levelId: String) {
        allVocabulary = repository.getVocabularyByLevel(levelId)
        _totalCount.value = allVocabulary.size
        _currentIndex.value = 0
        updateCurrentWord()
    }

    fun nextWord() {
        val idx = _currentIndex.value ?: 0
        if (idx < allVocabulary.size - 1) {
            _currentIndex.value = idx + 1
            _isFlipped.value = false
            updateCurrentWord()
        } else {
            // Reached the end — signal completion
            _isFinished.value = true
        }
    }

    fun previousWord() {
        val idx = _currentIndex.value ?: 0
        if (idx > 0) {
            _currentIndex.value = idx - 1
            _isFlipped.value = false
            updateCurrentWord()
        }
    }

    fun flipCard() {
        _isFlipped.value = !(_isFlipped.value ?: false)
    }

    /** Reset to the first card and hide the completion screen. */
    fun restart() {
        _isFinished.value = false
        _currentIndex.value = 0
        _isFlipped.value = false
        updateCurrentWord()
    }

    fun shuffle() {
        allVocabulary = allVocabulary.shuffled()
        _currentIndex.value = 0
        _isFlipped.value = false
        updateCurrentWord()
    }

    private fun updateCurrentWord() {
        val idx = _currentIndex.value ?: 0
        _currentVocabulary.value = allVocabulary.getOrNull(idx)
    }

    val hasNext: Boolean
        get() = (_currentIndex.value ?: 0) < allVocabulary.size - 1

    val hasPrevious: Boolean
        get() = (_currentIndex.value ?: 0) > 0
}
