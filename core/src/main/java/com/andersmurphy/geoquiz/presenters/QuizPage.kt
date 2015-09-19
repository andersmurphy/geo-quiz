package com.andersmurphy.geoquiz.presenters

import com.andersmurphy.geoquiz.models.QuestionGenerator
import com.andersmurphy.geoquiz.models.TrueFalseQuestion

/**
 * Created by anders on 15/12/14.
 */
public class QuizPage(private val view: QuizView,
                      private val generator: QuestionGenerator) : QuizPresenter {

    private var currentQuestion = TrueFalseQuestion(0, true);
    private var userCheated = false

    override fun prepareFirstQuestion() {
        currentQuestion = generator.generateStartingQuestion()
        view.setQuestionTextView(currentQuestion.questionId)
    }

    override fun truePressed() {
        if (userCheated) {
            view.toastJudgement()
        } else {
            if (currentQuestion.isTrue) {
                view.toastCorrect()
            } else {
                view.toastIncorrect()
            }
        }
    }

    override fun falsePressed() {
        if (userCheated) {
            view.toastJudgement()
        } else {
            if (currentQuestion.isTrue) {
                view.toastIncorrect()
            } else {
                view.toastCorrect()
            }
        }
    }

    override fun nextPressed() {
        currentQuestion = generator.generateNextQuestion()
        view.setQuestionTextView(currentQuestion.questionId)
    }

    override fun previousPressed() {
        currentQuestion = generator.generatePreviousQuestion()
        view.setQuestionTextView(currentQuestion.questionId)
    }

    override fun saveState() {
        view.saveCurrentQuestionIndex(generator.getCurrentQuestionIndex())
        view.saveUserCheated(userCheated)
    }

    override fun cheatPressed() {
        view.launchCheatActivity(currentQuestion.isTrue)
    }

    override fun resultFromCheatScreen(wasAnswerShown: Boolean) {
        this.userCheated = wasAnswerShown
    }
}
