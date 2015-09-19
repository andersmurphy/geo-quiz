package com.andersmurphy.geoquiz.models

/**
 * Created by anders on 18/12/14.
 */
public class TrueFalseQuestionGenerator (private val questions: List<TrueFalseQuestion>,
                                         private var currentQuestionIndex: Int = 0) : QuestionGenerator {

    private val FIRST_QUESTION_INDEX = 0
    private val lastQuestionIndex: Int

    init {
        lastQuestionIndex = calculateLastQuestionIndex()
    }

    override fun generateStartingQuestion(): TrueFalseQuestion {
        return questions.get(currentQuestionIndex)
    }

    override fun generateNextQuestion(): TrueFalseQuestion {
        val nextQuestionIndex = if (currentQuestionIndex >= lastQuestionIndex) FIRST_QUESTION_INDEX else ++currentQuestionIndex
        val nextQuestion = questions.get(nextQuestionIndex)
        currentQuestionIndex = nextQuestionIndex
        return nextQuestion
    }

    override fun generatePreviousQuestion(): TrueFalseQuestion {
        val previousQuestionIndex = if (currentQuestionIndex <= FIRST_QUESTION_INDEX) lastQuestionIndex else --currentQuestionIndex
        val previousQuestion = questions.get(previousQuestionIndex)
        currentQuestionIndex = previousQuestionIndex
        return previousQuestion
    }

    override fun getCurrentQuestionIndex(): Int {
        return currentQuestionIndex
    }

    private fun calculateLastQuestionIndex(): Int {
        return questions.size() - 1
    }
}
