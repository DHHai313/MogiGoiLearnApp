package com.example.mogigoi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mogigoi.R
import com.example.mogigoi.data.model.Lesson
import com.example.mogigoi.databinding.ItemLessonBinding

class LessonAdapter(
    private val onClick: (Lesson) -> Unit
) : ListAdapter<Lesson, LessonAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Lesson>() {
        override fun areItemsTheSame(old: Lesson, new: Lesson) = old.id == new.id
        override fun areContentsTheSame(old: Lesson, new: Lesson) = old == new
    }

    inner class ViewHolder(private val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson, position: Int) {
            binding.tvLessonNumber.text = String.format("%02d", position + 1)
            binding.tvLessonTitle.text = lesson.title
            binding.tvLessonTopic.text = lesson.topic
            binding.progressBar.progress = lesson.progressPercent
            binding.tvWordCount.text = "${lesson.learnedWords}/${lesson.totalWords}"
            binding.tvStatus.text = lesson.statusLabel

            // Status styling
            val ctx = binding.root.context
            when (lesson.statusLabel) {
                "Hoàn thành" -> {
                    binding.tvStatus.setTextColor(ContextCompat.getColor(ctx, R.color.correct))
                    binding.tvStatus.setBackgroundColor(ContextCompat.getColor(ctx, R.color.correct_light))
                }
                "Đang học" -> {
                    binding.tvStatus.setTextColor(ContextCompat.getColor(ctx, R.color.primary))
                    binding.tvStatus.setBackgroundColor(ContextCompat.getColor(ctx, R.color.primary_container))
                }
                else -> {
                    binding.tvStatus.setTextColor(ContextCompat.getColor(ctx, R.color.on_surface_variant))
                    binding.tvStatus.setBackgroundColor(ContextCompat.getColor(ctx, R.color.surface_variant))
                }
            }

            binding.root.setOnClickListener {
                it.animate()
                    .scaleX(0.97f)
                    .scaleY(0.97f)
                    .setDuration(80)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(80)
                            .start()
                        onClick(lesson)
                    }
                    .start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLessonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}
