package com.example.anders.geoquiz.presenters;

/**
 * Created by anders on 15/12/14.
 */
public interface QuizPresenter {
    void prepareFirstQuestion();

    void truePressed();

    void falsePressed();

    void nextPressed();

    void previousPressed();

    int onSaveInstanceState();

    void cheatPressed();

    void resultFromCheatScreen(boolean wasAnswerShown);
}
