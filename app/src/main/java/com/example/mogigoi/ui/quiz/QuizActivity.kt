package com.example.mogigoi.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mogigoi.R
import com.example.mogigoi.databinding.ActivityQuizBinding
import com.example.mogigoi.ui.result.ResultActivity
import com.example.mogigoi.viewmodel.QuizViewModel

class QuizActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LESSON_ID = "lesson_id"
        const val EXTRA_LEVEL_ID = "level_id"
        const val EXTRA_LESSON_TOPIC = "lesson_topic"
    }

    private lateinit var binding: ActivityQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private var isAnswered = false
    private val autoAdvanceHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = intent.getIntExtra(EXTRA_LESSON_ID, -1)
        val levelId = intent.getStringExtra(EXTRA_LEVEL_ID) ?: "N5"
        val topic = intent.getStringExtra(EXTRA_LESSON_TOPIC) ?: ""

        setupToolbar(topic)
        setupOptions()
        observeViewModel()

        if (lessonId != -1) {
            viewModel.loadQuiz(lessonId)
        } else {
            viewModel.loadQuizByLevel(levelId)
        }
    }

    private fun setupToolbar(topic: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = if (topic.isNotEmpty()) "📝 $topic" else "Trắc nghiệm"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupOptions() {
        binding.optionA.setOnClickListener { if (!isAnswered) handleAnswer(0) }
        binding.optionB.setOnClickListener { if (!isAnswered) handleAnswer(1) }
        binding.optionC.setOnClickListener { if (!isAnswered) handleAnswer(2) }
        binding.optionD.setOnClickListener { if (!isAnswered) handleAnswer(3) }
    }

    private fun observeViewModel() {
        viewModel.currentQuestion.observe(this) { question ->
            question ?: return@observe
            isAnswered = false
            resetOptionsUI()

            binding.tvKanji.text = question.kanji
            binding.tvFurigana.text = question.furigana

            val options = question.options
            binding.tvOptionA.text = options.getOrElse(0) { "" }
            binding.tvOptionB.text = options.getOrElse(1) { "" }
            binding.tvOptionC.text = options.getOrElse(2) { "" }
            binding.tvOptionD.text = options.getOrElse(3) { "" }
        }

        viewModel.currentQuestionIndex.observe(this) { idx ->
            val total = viewModel.totalCount.value ?: 0
            if (total > 0) {
                binding.tvQuestionCounter.text = "Câu ${idx + 1}/$total"
                binding.progressBar.progress = ((idx + 1) * 100) / total
            }
        }

        viewModel.correctCount.observe(this) { count ->
            binding.tvScore.text = "${count}✓"
        }

        viewModel.quizFinished.observe(this) { finished ->
            if (finished) {
                navigateToResult()
            }
        }
    }

    private fun handleAnswer(optionIndex: Int) {
        val question = viewModel.currentQuestion.value ?: return
        val selectedAnswer = question.options.getOrElse(optionIndex) { "" }
        val isCorrect = viewModel.submitAnswer(selectedAnswer)
        isAnswered = true

        // Highlight all options
        val options = listOf(binding.optionA, binding.optionB, binding.optionC, binding.optionD)
        val resultIcons = listOf(binding.tvResultA, binding.tvResultB, binding.tvResultC, binding.tvResultD)

        options.forEachIndexed { idx, view ->
            val optText = question.options.getOrElse(idx) { "" }
            val icon = resultIcons[idx]
            icon.visibility = View.VISIBLE

            when {
                optText == question.correctAnswer -> {
                    view.setBackgroundResource(R.drawable.bg_answer_correct)
                    icon.text = "✓"
                    icon.setTextColor(ContextCompat.getColor(this, R.color.correct))
                }
                idx == optionIndex && !isCorrect -> {
                    view.setBackgroundResource(R.drawable.bg_answer_wrong)
                    icon.text = "✗"
                    icon.setTextColor(ContextCompat.getColor(this, R.color.wrong))
                }
                else -> {
                    // keep default
                    icon.visibility = View.INVISIBLE
                }
            }
        }

        // Auto advance after 1.2 seconds
        autoAdvanceHandler.postDelayed({
            viewModel.nextQuestion()
        }, 1200)
    }

    private fun resetOptionsUI() {
        val options = listOf(binding.optionA, binding.optionB, binding.optionC, binding.optionD)
        val icons = listOf(binding.tvResultA, binding.tvResultB, binding.tvResultC, binding.tvResultD)

        options.forEach { it.setBackgroundResource(R.drawable.bg_answer_default) }
        icons.forEach { it.visibility = View.INVISIBLE }
    }

    private fun navigateToResult() {
        val result = viewModel.quizResult.value ?: return
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(ResultActivity.EXTRA_CORRECT, result.correctAnswers)
            putExtra(ResultActivity.EXTRA_TOTAL, result.totalQuestions)
            // Pass serialized results
        }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        autoAdvanceHandler.removeCallbacksAndMessages(null)
    }
}
