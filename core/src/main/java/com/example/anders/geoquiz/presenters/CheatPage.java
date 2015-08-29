package com.example.anders.geoquiz.presenters;

public final class CheatPage implements CheatPresenter {

    private final CheatView view;
    private final boolean isTrue;

    public CheatPage(CheatView view, boolean isTrue) {
        this.view = view;
        this.isTrue = isTrue;
    }

    @Override
    public void showAnswerPressed() {
        view.storeThatUserHasCheated();
        view.showAnswer(isTrue);
    }
}
