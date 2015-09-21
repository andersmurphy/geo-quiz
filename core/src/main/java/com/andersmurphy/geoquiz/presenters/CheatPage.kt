package com.andersmurphy.geoquiz.presenters

class CheatPage(private val view: CheatView,
                       private val isQuestionAnswerTrue: Boolean,
                       private var hasAnswerBeenShown: Boolean) : CheatPresenter {

    override fun showAnswerPressed() {
        hasAnswerBeenShown = true
        if (isQuestionAnswerTrue) view.showAnswerIsTrue() else view.showAnswerIsFalse()
    }

    override fun setResult() {
        view.setResultAnswerShown(hasAnswerBeenShown)
    }

    override fun saveState() {
        view.saveAnswerShown(hasAnswerBeenShown)
    }
}
