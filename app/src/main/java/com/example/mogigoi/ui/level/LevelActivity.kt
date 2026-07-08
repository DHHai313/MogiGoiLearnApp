package com.example.mogigoi.ui.level

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mogigoi.R
import com.example.mogigoi.adapter.LevelAdapter
import com.example.mogigoi.data.model.Level
import com.example.mogigoi.databinding.ActivityLevelBinding
import com.example.mogigoi.ui.lesson.LessonListActivity
import com.example.mogigoi.viewmodel.HomeViewModel

class LevelActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FROM_HOME = "from_home"
        const val EXTRA_MODE = "mode"
        const val MODE_VOCABULARY = "vocabulary"
        const val MODE_QUIZ = "quiz"
    }

    private lateinit var binding: ActivityLevelBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var levelAdapter: LevelAdapter

    private var selectedMode: String = MODE_VOCABULARY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedMode = intent.getStringExtra(EXTRA_MODE) ?: MODE_VOCABULARY

        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.level_screen_title)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        levelAdapter = LevelAdapter { level ->
            navigateToLesson(level)
        }

        binding.rvLevels.apply {
            layoutManager = LinearLayoutManager(this@LevelActivity)
            adapter = levelAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.levels.observe(this) { levels ->
            levelAdapter.submitList(levels.reversed())
        }
    }

    private fun navigateToLesson(level: Level) {
        val intent = Intent(this, LessonListActivity::class.java).apply {
            putExtra(LessonListActivity.EXTRA_LEVEL_ID, level.id)
            putExtra(LessonListActivity.EXTRA_LEVEL_NAME, level.name)
            putExtra(EXTRA_MODE, selectedMode)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
