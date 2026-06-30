package com.example.mogigoi.ui.lesson

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mogigoi.R
import com.example.mogigoi.adapter.LessonAdapter
import com.example.mogigoi.data.model.Lesson
import com.example.mogigoi.databinding.ActivityLessonListBinding
import com.example.mogigoi.ui.mode.LearningModeActivity
import com.example.mogigoi.viewmodel.LessonViewModel

class LessonListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LEVEL_ID = "level_id"
        const val EXTRA_LEVEL_NAME = "level_name"
    }

    private lateinit var binding: ActivityLessonListBinding
    private val viewModel: LessonViewModel by viewModels()
    private lateinit var lessonAdapter: LessonAdapter

    private var levelId: String = "N5"
    private var levelName: String = "N5"
    private var selectedMode: String = "vocabulary"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        levelId = intent.getStringExtra(EXTRA_LEVEL_ID) ?: "N5"
        levelName = intent.getStringExtra(EXTRA_LEVEL_NAME) ?: "N5"
        selectedMode = intent.getStringExtra("mode") ?: "vocabulary"

        setupToolbar()
        setupRecyclerView()
        observeViewModel()
        viewModel.loadLessons(levelId)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Cấp độ $levelName"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        lessonAdapter = LessonAdapter { lesson ->
            navigateToLearningMode(lesson)
        }
        binding.rvLessons.apply {
            layoutManager = LinearLayoutManager(this@LessonListActivity)
            adapter = lessonAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.lessons.observe(this) { lessons ->
            lessonAdapter.submitList(lessons)
        }
    }

    private fun navigateToLearningMode(lesson: Lesson) {
        val intent = Intent(this, LearningModeActivity::class.java).apply {
            putExtra(LearningModeActivity.EXTRA_LESSON_ID, lesson.id)
            putExtra(LearningModeActivity.EXTRA_LESSON_TITLE, lesson.title)
            putExtra(LearningModeActivity.EXTRA_LESSON_TOPIC, lesson.topic)
            putExtra(LearningModeActivity.EXTRA_LEVEL_ID, levelId)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}