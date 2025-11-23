package com.ipf.technicalrulesquiz.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ipf.technicalrulesquiz.R
import com.ipf.technicalrulesquiz.data.model.AnsweredQuestion
import com.ipf.technicalrulesquiz.databinding.ItemAnswerReviewBinding

class AnswerReviewAdapter(
    private val answeredQuestions: List<AnsweredQuestion>
) : RecyclerView.Adapter<AnswerReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnswerReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(answeredQuestions[position], position + 1)
    }

    override fun getItemCount(): Int = answeredQuestions.size

    class ViewHolder(
        private val binding: ItemAnswerReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(answeredQuestion: AnsweredQuestion, questionNumber: Int) {
            val context = binding.root.context
            val question = answeredQuestion.question

            binding.questionNumber.text = context.getString(
                R.string.question_number_format,
                questionNumber
            )

            binding.questionText.text = question.question

            binding.userAnswer.text = question.options[answeredQuestion.userAnswerIndex]

            binding.correctAnswer.text = question.options[question.correctAnswerIndex]

            if (answeredQuestion.isCorrect) {
                binding.resultIndicator.text = context.getString(R.string.result_correct)
                binding.resultIndicator.setBackgroundColor(
                    ContextCompat.getColor(context, android.R.color.holo_green_dark)
                )
                binding.resultIndicator.setTextColor(
                    ContextCompat.getColor(context, android.R.color.white)
                )
                binding.correctAnswerLabel.visibility = View.GONE
                binding.correctAnswer.visibility = View.GONE
            } else {
                binding.resultIndicator.text = context.getString(R.string.result_incorrect)
                binding.resultIndicator.setBackgroundColor(
                    ContextCompat.getColor(context, android.R.color.holo_red_dark)
                )
                binding.resultIndicator.setTextColor(
                    ContextCompat.getColor(context, android.R.color.white)
                )
                binding.correctAnswerLabel.visibility = View.VISIBLE
                binding.correctAnswer.visibility = View.VISIBLE
            }

            binding.ruleReference.text = question.ruleReference.getFullReference()

            if (question.explanation != null) {
                binding.explanation.text = question.explanation
                binding.explanation.visibility = View.VISIBLE
            } else {
                binding.explanation.visibility = View.GONE
            }
        }
    }
}
