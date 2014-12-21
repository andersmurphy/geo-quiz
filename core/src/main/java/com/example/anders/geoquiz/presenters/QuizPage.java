package com.example.anders.geoquiz.presenters;

import com.example.anders.geoquiz.models.QuestionGenerator;
import com.example.anders.geoquiz.models.TrueFalseQuestion;

/**
 * Created by anders on 15/12/14.
 */
public final class QuizPage implements QuizPresenter {

    private final QuizView view;
    private final QuestionGenerator generator;
    private TrueFalseQuestion currentQuestion;

    public QuizPage(QuizView view, QuestionGenerator generator) {
        this.view = view;
        this.generator = generator;
    }

    @Override
    public void prepareFirstQuestion() {
        currentQuestion = generator.generateFirstQuestion();
        view.setQuestionTextView(currentQuestion.getQuestionId());
    }

    @Override
    public void truePressed() {
        if (currentQuestion.isTrue()) {
            view.toastCorrect();
        } else {
            view.toastIncorrect();
        }
    }

    @Override
    public void falsePressed() {
        if (currentQuestion.isTrue()) {
            view.toastIncorrect();
        } else {
            view.toastCorrect();
        }
    }

    @Override
    public void nextPressed() {
        currentQuestion = generator.generateNextQuestion();
        view.setQuestionTextView(currentQuestion.getQuestionId());
    }

    @Override
    public void previousPressed() {
        currentQuestion = generator.generatePreviousQuestion();
        view.setQuestionTextView(currentQuestion.getQuestionId());
    }

}
