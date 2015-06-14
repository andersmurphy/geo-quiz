package com.example.anders.geoquiz.presenters

trait QuizPresenter {
  def prepareFirstQuestion()

  def truePressed()

  def falsePressed()

  def nextPressed()

  def previousPressed()

  def onSaveInstanceState(): Int
}
