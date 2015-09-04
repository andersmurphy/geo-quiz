package com.andersmurphy.geoquiz.presenters

trait QuizView {
  def toastIncorrect()

  def toastCorrect()

  def setQuestionTextView(id: Int)
}
