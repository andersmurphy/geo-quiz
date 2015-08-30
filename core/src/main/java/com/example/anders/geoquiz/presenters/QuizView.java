package com.example.anders.geoquiz.presenters;

/**
 * Created by anders on 15/12/14.
 */
public interface QuizView {
    void toastIncorrect();

    void toastCorrect();

    void setQuestionTextView(int id);

    void launchCheatActivity(boolean isTrue);

    void toastJudgement();
}
