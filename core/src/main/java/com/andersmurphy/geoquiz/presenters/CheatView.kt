package com.andersmurphy.geoquiz.presenters

interface CheatView {
    fun setResultAnswerShown(answerShown: Boolean)
    fun saveAnswerShown(answerShown: Boolean)
    fun showAnswerIsTrue()
    fun showAnswerIsFalse()
}
