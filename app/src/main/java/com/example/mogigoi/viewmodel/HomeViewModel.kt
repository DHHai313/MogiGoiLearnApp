package com.example.mogigoi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mogigoi.data.model.Level
import com.example.mogigoi.data.model.StudyStats
import com.example.mogigoi.data.repository.FakeVocabularyRepository
import com.example.mogigoi.data.repository.VocabularyRepository

class HomeViewModel : ViewModel() {

    private val repository: VocabularyRepository = FakeVocabularyRepository()

    private val _levels = MutableLiveData<List<Level>>()
    val levels: LiveData<List<Level>> = _levels

    private val _studyStats = MutableLiveData<StudyStats>()
    val studyStats: LiveData<StudyStats> = _studyStats

    init {
        loadData()
    }

    private fun loadData() {
        _levels.value = repository.getLevels()
        _studyStats.value = repository.getStudyStats()
    }
}
