package com.example.anders.geoquiz.presenters;

public interface CheatView {
    void setResultAnswerShown(boolean answerShown);

    void saveAnswerShown(boolean answerShown);

    void showAnswerIsTrue();

    void showAnswerIsFalse();
}
