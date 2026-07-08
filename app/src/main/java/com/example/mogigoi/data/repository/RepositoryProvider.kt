package com.example.mogigoi.data.repository

import android.content.Context

/**
 * Singleton provider for [VocabularyRepository].
 *
 * Call [init] once from Application (or MainActivity) before any ViewModel
 * tries to access [repository].
 */
object RepositoryProvider {

    private var _repository: VocabularyRepository? = null

    val repository: VocabularyRepository
        get() = _repository ?: error(
            "RepositoryProvider is not initialised. " +
            "Call RepositoryProvider.init(context) in your Application class."
        )

    fun init(context: Context) {
        if (_repository == null) {
            _repository = SqliteVocabularyRepository(context.applicationContext)
        }
    }
}
