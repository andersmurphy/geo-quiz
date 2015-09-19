package com.andersmurphy.geoquiz.presenters

/**
 * Created by anders on 15/12/14.
 */
interface QuizView {
    fun saveCurrentQuestionIndex(currentQuestionIndex: Int)
    fun saveUserCheated(userCheated: Boolean)
    fun toastIncorrect()
    fun toastCorrect()
    fun setQuestionTextView(id: Int)
    fun launchCheatActivity(isTrue: Boolean)
    fun toastJudgement()
}
