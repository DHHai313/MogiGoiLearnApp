package com.example.mogigoi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mogigoi.data.model.Lesson
import com.example.mogigoi.data.repository.FakeVocabularyRepository
import com.example.mogigoi.data.repository.VocabularyRepository

class LessonViewModel : ViewModel() {

    private val repository: VocabularyRepository = FakeVocabularyRepository()

    private val _lessons = MutableLiveData<List<Lesson>>()
    val lessons: LiveData<List<Lesson>> = _lessons

    fun loadLessons(levelId: String) {
        _lessons.value = repository.getLessonsByLevel(levelId)
    }
}
