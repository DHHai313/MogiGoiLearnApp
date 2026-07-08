package com.example.mogigoi

import android.app.Application
import com.example.mogigoi.data.repository.RepositoryProvider

class MogiGoiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.init(this)
    }
}
