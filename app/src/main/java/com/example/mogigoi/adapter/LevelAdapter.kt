package com.example.mogigoi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mogigoi.R
import com.example.mogigoi.data.model.Level
import com.example.mogigoi.databinding.ItemLevelBinding

class LevelAdapter(
    private val onClick: (Level) -> Unit
) : ListAdapter<Level, LevelAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Level>() {
        override fun areItemsTheSame(old: Level, new: Level) = old.id == new.id
        override fun areContentsTheSame(old: Level, new: Level) = old == new
    }

    private val levelBackgrounds = mapOf(
        "N5" to R.drawable.bg_level_n5,
        "N4" to R.drawable.bg_level_n4,
        "N3" to R.drawable.bg_level_n3,
        "N2" to R.drawable.bg_level_n2,
        "N1" to R.drawable.bg_level_n1
    )

    inner class ViewHolder(private val binding: ItemLevelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(level: Level) {
            binding.tvLevelName.text = level.id
            binding.tvLevelTitle.text = level.name
            binding.tvLevelDescription.text = level.description
            binding.tvProgressPercent.text = "${level.progressPercent}%"
            binding.tvLessonsCount.text = "${level.totalLessons} bài học"
            binding.tvWordsCount.text = "${level.totalWords} từ vựng"
            binding.progressBar.progress = level.progressPercent

            // Set level-specific gradient background
            val bgRes = levelBackgrounds[level.id] ?: R.drawable.bg_level_n5
            binding.layoutLevelBadge.setBackgroundResource(bgRes)

            binding.root.setOnClickListener {
                // Scale animation
                it.animate()
                    .scaleX(0.96f)
                    .scaleY(0.96f)
                    .setDuration(80)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(80)
                            .start()
                        onClick(level)
                    }
                    .start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLevelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
