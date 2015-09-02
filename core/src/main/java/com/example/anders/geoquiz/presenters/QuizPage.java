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
    private boolean userCheated = false;


    public QuizPage(QuizView view, QuestionGenerator generator) {
        this.view = view;
        this.generator = generator;
    }

    @Override
    public void prepareFirstQuestion() {
        currentQuestion = generator.generateStartingQuestion();
        view.setQuestionTextView(currentQuestion.getQuestionId());
    }

    @Override
    public void truePressed() {
        if (userCheated) {
            view.toastJudgement();
        } else {
            if (currentQuestion.isTrue()) {
                view.toastCorrect();
            } else {
                view.toastIncorrect();
            }
        }
    }

    @Override
    public void falsePressed() {
        if (userCheated) {
            view.toastJudgement();
        } else {
            if (currentQuestion.isTrue()) {
                view.toastIncorrect();
            } else {
                view.toastCorrect();
            }
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

    @Override
    public void saveState() {
        view.saveCurrentQuestionIndex(generator.getCurrentQuestionIndex());
        view.saveUserCheated(userCheated);
    }

    @Override
    public void cheatPressed() {
        view.launchCheatActivity(currentQuestion.isTrue());
    }

    @Override
    public void resultFromCheatScreen(boolean wasAnswerShown) {
        this.userCheated = wasAnswerShown;
    }
}
