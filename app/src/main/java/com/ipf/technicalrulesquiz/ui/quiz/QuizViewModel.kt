package com.ipf.technicalrulesquiz.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ipf.technicalrulesquiz.data.model.AnsweredQuestion
import com.ipf.technicalrulesquiz.data.model.QuizQuestion
import com.ipf.technicalrulesquiz.data.model.QuizResult
import com.ipf.technicalrulesquiz.data.repository.QuizRepository

class QuizViewModel : ViewModel() {

    private val repository = QuizRepository()

    private val _currentQuestion = MutableLiveData<QuizQuestion>()
    val currentQuestion: LiveData<QuizQuestion> = _currentQuestion

    private val _currentQuestionNumber = MutableLiveData<Int>()
    val currentQuestionNumber: LiveData<Int> = _currentQuestionNumber

    private val _totalQuestions = MutableLiveData<Int>()
    val totalQuestions: LiveData<Int> = _totalQuestions

    private val _selectedAnswerIndex = MutableLiveData<Int?>()
    val selectedAnswerIndex: LiveData<Int?> = _selectedAnswerIndex

    private val _quizCompleted = MutableLiveData<Boolean>()
    val quizCompleted: LiveData<Boolean> = _quizCompleted

    private var questions: List<QuizQuestion> = emptyList()
    private var currentIndex = 0
    private val answeredQuestions = mutableListOf<AnsweredQuestion>()
    private var questionStartTime = 0L

    fun startQuiz(numberOfQuestions: Int = 10) {
        questions = repository.getRandomQuestions(numberOfQuestions)
        currentIndex = 0
        answeredQuestions.clear()
        _totalQuestions.value = questions.size
        _quizCompleted.value = false
        loadCurrentQuestion()
    }

    private fun loadCurrentQuestion() {
        if (currentIndex < questions.size) {
            _currentQuestion.value = questions[currentIndex]
            _currentQuestionNumber.value = currentIndex + 1
            _selectedAnswerIndex.value = null
            questionStartTime = System.currentTimeMillis()
        } else {
            _quizCompleted.value = true
        }
    }

    fun selectAnswer(answerIndex: Int) {
        _selectedAnswerIndex.value = answerIndex
    }

    fun submitAnswer() {
        val answerIndex = _selectedAnswerIndex.value ?: return
        val question = _currentQuestion.value ?: return

        val timeTaken = System.currentTimeMillis() - questionStartTime
        val isCorrect = answerIndex == question.correctAnswerIndex

        answeredQuestions.add(
            AnsweredQuestion(
                question = question,
                userAnswerIndex = answerIndex,
                isCorrect = isCorrect,
                timeTaken = timeTaken
            )
        )

        currentIndex++
        loadCurrentQuestion()
    }

    fun getQuizResult(): QuizResult {
        val correct = answeredQuestions.count { it.isCorrect }
        val incorrect = answeredQuestions.count { !it.isCorrect }

        return QuizResult(
            totalQuestions = questions.size,
            correctAnswers = correct,
            incorrectAnswers = incorrect,
            answeredQuestions = answeredQuestions
        )
    }

    fun hasSelectedAnswer(): Boolean {
        return _selectedAnswerIndex.value != null
    }
}
