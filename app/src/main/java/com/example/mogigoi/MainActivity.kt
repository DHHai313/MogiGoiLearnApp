package com.example.mogigoi

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mogigoi.databinding.ActivityMainBinding
import com.example.mogigoi.ui.level.LevelActivity
import com.example.mogigoi.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupMenuClicks()
    }

    private fun observeViewModel() {
        viewModel.studyStats.observe(this) { stats ->
            binding.tvTotalWords.text = stats.totalWords.toString()
            binding.tvLearnedWords.text = stats.learnedWords.toString()
            binding.tvCompletion.text = "${stats.completionPercent}%"
        }
    }

    private fun setupMenuClicks() {
        binding.cardVocabulary.setOnClickListener {
            animateCardClick(it) {
                startActivity(Intent(this, LevelActivity::class.java))
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        binding.cardProgress.setOnClickListener {
            animateCardClick(it) {
                android.widget.Toast.makeText(this, getString(R.string.coming_soon), android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        binding.cardSettings.setOnClickListener {
            animateCardClick(it) {
                android.widget.Toast.makeText(this, getString(R.string.coming_soon), android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun animateCardClick(view: android.view.View, action: () -> Unit) {
        view.animate()
            .scaleX(0.95f).scaleY(0.95f)
            .setDuration(80)
            .withEndAction {
                view.animate()
                    .scaleX(1f).scaleY(1f)
                    .setDuration(80)
                    .withEndAction { action() }
                    .start()
            }
            .start()
    }
}