package com.example.mogigoi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mogigoi.R
import com.example.mogigoi.data.model.QuizResultItem
import com.example.mogigoi.databinding.ItemQuizResultBinding

class QuizResultAdapter : ListAdapter<QuizResultItem, QuizResultAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<QuizResultItem>() {
        override fun areItemsTheSame(old: QuizResultItem, new: QuizResultItem) =
            old.question.vocabularyId == new.question.vocabularyId
        override fun areContentsTheSame(old: QuizResultItem, new: QuizResultItem) = old == new
    }

    inner class ViewHolder(private val binding: ItemQuizResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuizResultItem) {
            val ctx = binding.root.context
            binding.tvQuestionKanji.text = "${item.question.kanji} (${item.question.furigana})"

            if (item.isCorrect) {
                binding.tvResultIcon.text = "✓"
                binding.tvResultIcon.setTextColor(ContextCompat.getColor(ctx, R.color.correct))
                binding.tvResultIcon.setBackgroundColor(ContextCompat.getColor(ctx, R.color.correct_light))
                binding.tvCorrectAnswer.text = "Đúng: ${item.question.correctAnswer}"
                binding.tvUserAnswer.text = ""
            } else {
                binding.tvResultIcon.text = "✗"
                binding.tvResultIcon.setTextColor(ContextCompat.getColor(ctx, R.color.wrong))
                binding.tvResultIcon.setBackgroundColor(ContextCompat.getColor(ctx, R.color.wrong_light))
                binding.tvCorrectAnswer.text = "Đúng: ${item.question.correctAnswer}"
                binding.tvUserAnswer.text = "Bạn chọn: ${item.userAnswer}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuizResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
