package com.example.mogigoi.ui.mode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mogigoi.R
import com.example.mogigoi.databinding.ActivityLearningModeBinding
import com.example.mogigoi.ui.flashcard.FlashCardActivity
import com.example.mogigoi.ui.quiz.QuizActivity

class LearningModeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LESSON_ID = "lesson_id"
        const val EXTRA_LESSON_TITLE = "lesson_title"
        const val EXTRA_LESSON_TOPIC = "lesson_topic"
        const val EXTRA_LEVEL_ID = "level_id"
    }

    private lateinit var binding: ActivityLearningModeBinding

    private var lessonId: Int = -1
    private var lessonTitle: String = ""
    private var lessonTopic: String = ""
    private var levelId: String = "N5"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lessonId = intent.getIntExtra(EXTRA_LESSON_ID, -1)
        lessonTitle = intent.getStringExtra(EXTRA_LESSON_TITLE) ?: ""
        lessonTopic = intent.getStringExtra(EXTRA_LESSON_TOPIC) ?: ""
        levelId = intent.getStringExtra(EXTRA_LEVEL_ID) ?: "N5"

        setupToolbar()
        setupLessonInfo()
        setupClicks()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.mode_title)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupLessonInfo() {
        binding.tvLessonName.text = "$lessonTitle — $lessonTopic"
    }

    private fun setupClicks() {
        binding.cardFlashcard.setOnClickListener {
            animateCardClick(it) {
                startActivity(
                    Intent(this, FlashCardActivity::class.java).apply {
                        putExtra(FlashCardActivity.EXTRA_LESSON_ID, lessonId)
                        putExtra(FlashCardActivity.EXTRA_LEVEL_ID, levelId)
                        putExtra(FlashCardActivity.EXTRA_LESSON_TOPIC, lessonTopic)
                    }
                )
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        binding.cardQuiz.setOnClickListener {
            animateCardClick(it) {
                startActivity(
                    Intent(this, QuizActivity::class.java).apply {
                        putExtra(QuizActivity.EXTRA_LESSON_ID, lessonId)
                        putExtra(QuizActivity.EXTRA_LEVEL_ID, levelId)
                        putExtra(QuizActivity.EXTRA_LESSON_TOPIC, lessonTopic)
                    }
                )
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    private fun animateCardClick(view: android.view.View, action: () -> Unit) {
        view.animate()
            .scaleX(0.95f).scaleY(0.95f)
            .setDuration(100)
            .withEndAction {
                view.animate()
                    .scaleX(1f).scaleY(1f)
                    .setDuration(100)
                    .withEndAction { action() }
                    .start()
            }
            .start()
    }
}
