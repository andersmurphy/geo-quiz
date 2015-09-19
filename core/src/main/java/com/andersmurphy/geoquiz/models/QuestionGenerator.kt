package com.andersmurphy.geoquiz.models

/**
 * Created by anders on 18/12/14.
 */
interface QuestionGenerator {
    fun generateStartingQuestion(): TrueFalseQuestion
    fun generateNextQuestion(): TrueFalseQuestion
    fun generatePreviousQuestion(): TrueFalseQuestion
    fun getCurrentQuestionIndex(): Int
}
