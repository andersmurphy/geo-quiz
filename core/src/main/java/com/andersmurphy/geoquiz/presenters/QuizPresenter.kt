package com.andersmurphy.geoquiz.presenters

/**
 * Created by anders on 15/12/14.
 */
interface QuizPresenter {
    fun prepareFirstQuestion()
    fun truePressed()
    fun falsePressed()
    fun nextPressed()
    fun previousPressed()
    fun saveState()
    fun cheatPressed()
    fun resultFromCheatScreen(wasAnswerShown: Boolean)
}
