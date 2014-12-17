package com.example.anders.geoquiz.presenters;

/**
 * Created by anders on 15/12/14.
 */
public final class QuizPage implements QuizPresenter {

    private final QuizView view;

    public QuizPage(QuizView view){
        this.view = view;
    }


    @Override
    public void truePressed() {
        view.toastIncorrect();
    }

    @Override
    public void falsePressed() {
        view.toastCorrect();
    }
}
