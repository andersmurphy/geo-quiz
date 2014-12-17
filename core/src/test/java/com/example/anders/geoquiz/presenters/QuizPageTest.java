package com.example.anders.geoquiz.presenters;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class QuizPageTest {

    @Test
    public void pressing_the_true_button_toasts_incorrect(){
        QuizView view = mock(QuizView.class);

        QuizPresenter presenter = new QuizPage(view);
        presenter.truePressed();

        verify(view).toastIncorrect();
    }

    @Test
    public void pressing_the_false_button_toasts_correct(){
        QuizView view = mock(QuizView.class);

        QuizPresenter presenter = new QuizPage(view);
        presenter.falsePressed();

        verify(view).toastCorrect();
    }
}