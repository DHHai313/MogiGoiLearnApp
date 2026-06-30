package com.example.mogigoi.ui.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mogigoi.R
import com.example.mogigoi.adapter.QuizResultAdapter
import com.example.mogigoi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CORRECT = "correct"
        const val EXTRA_TOTAL = "total"
    }

    private lateinit var binding: ActivityResultBinding
    private lateinit var resultAdapter: QuizResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correct = intent.getIntExtra(EXTRA_CORRECT, 0)
        val total = intent.getIntExtra(EXTRA_TOTAL, 0)

        setupResult(correct, total)
        setupRecyclerView()
        setupButtons()
    }

    private fun setupResult(correct: Int, total: Int) {
        val scorePercent = if (total > 0) (correct * 100) / total else 0

        binding.tvScore.text = "$correct/$total"
        binding.tvCorrectCount.text = correct.toString()
        binding.tvWrongCount.text = (total - correct).toString()

        // Feedback based on score
        val feedback = when {
            scorePercent >= 90 -> getString(R.string.result_excellent)
            scorePercent >= 70 -> getString(R.string.result_good)
            scorePercent >= 50 -> getString(R.string.result_average)
            else -> getString(R.string.result_poor)
        }
        binding.tvFeedback.text = feedback

        val emoji = when {
            scorePercent >= 90 -> "🏆"
            scorePercent >= 70 -> "⭐"
            scorePercent >= 50 -> "💪"
            else -> "📚"
        }
        binding.tvScoreEmoji.text = emoji
    }

    private fun setupRecyclerView() {
        resultAdapter = QuizResultAdapter()
        binding.rvResults.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = resultAdapter
        }
        // Note: in a full implementation, results would be passed via Parcelable/shared ViewModel
    }

    private fun setupButtons() {
        binding.btnRetry.setOnClickListener {
            finish() // Go back to quiz to restart
        }

        binding.btnBack.setOnClickListener {
            // Navigate back to lesson list
            finishAffinity()
        }
    }
}
