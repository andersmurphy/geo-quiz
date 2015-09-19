package com.andersmurphy.geoquiz.presenters

class NoOpQuizPage : QuizPresenter {
    override fun prepareFirstQuestion() {}
    override fun truePressed() {}
    override fun falsePressed() {}
    override fun nextPressed() {}
    override fun previousPressed() {}
    override fun saveState() {}
    override fun cheatPressed() {}
    override fun resultFromCheatScreen(wasAnswerShown: Boolean) {}
}