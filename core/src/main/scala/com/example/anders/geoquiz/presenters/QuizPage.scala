package com.example.anders.geoquiz.presenters

import com.example.anders.geoquiz.models.{TrueFalseQuestion, QuestionGenerator}

final class QuizPage(val view: QuizView, val generator: QuestionGenerator) extends QuizPresenter {

  private var currentQuestion: TrueFalseQuestion = null

  override def prepareFirstQuestion(): Unit = {
    currentQuestion = generator.generateStartingQuestion
    view.setQuestionTextView(currentQuestion.getQuestionId)
  }

  override def truePressed(): Unit = {
    if (currentQuestion.isTrue) view.toastCorrect else view.toastIncorrect
  }

  override def falsePressed(): Unit = {
    if (currentQuestion.isTrue) view.toastIncorrect else view.toastCorrect
  }

  override def nextPressed(): Unit = {
    currentQuestion = generator.generateNextQuestion
    view.setQuestionTextView(currentQuestion.getQuestionId)
  }

  override def previousPressed(): Unit = {
    currentQuestion = generator.generatePreviousQuestion
    view.setQuestionTextView(currentQuestion.getQuestionId)
  }

  override def onSaveInstanceState(): Int = {
    generator.getCurrentQuestionIndex;
  }
}
