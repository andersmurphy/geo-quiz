package com.andersmurphy.geoquiz.presenters;

public final class CheatPage implements CheatPresenter {

    private final CheatView view;
    private final boolean isQuestionAnswerTrue;
    private boolean hasAnswerBeenShown;

    public CheatPage(CheatView view, boolean isQuestionAnswerTrue, boolean hasAnswerBeenShown) {
        this.view = view;
        this.isQuestionAnswerTrue = isQuestionAnswerTrue;
        this.hasAnswerBeenShown = hasAnswerBeenShown;
    }

    @Override
    public void showAnswerPressed() {
        hasAnswerBeenShown = true;
        if(isQuestionAnswerTrue) {
            view.showAnswerIsTrue();
        } else {
            view.showAnswerIsFalse();
        }
    }

    @Override
    public void setResult() {
        view.setResultAnswerShown(hasAnswerBeenShown);
    }

    @Override
    public void saveState() {
        view.saveAnswerShown(hasAnswerBeenShown);
    }
}
