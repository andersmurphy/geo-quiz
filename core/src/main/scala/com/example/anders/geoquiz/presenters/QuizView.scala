package com.example.anders.geoquiz.presenters

trait QuizView {
  def toastIncorrect()

  def toastCorrect()

  def setQuestionTextView(id: Int)
}
