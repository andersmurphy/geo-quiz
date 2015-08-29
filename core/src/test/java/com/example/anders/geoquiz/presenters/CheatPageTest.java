package com.example.anders.geoquiz.presenters;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheatPageTest {

    @Test
    public void when_the_show_answer_pressed_store_that_the_user_has_cheated() {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true);
        presenter.showAnswerPressed();

        verify(view).storeThatUserHasCheated();
    }

    @Test
    public void when_the_show_answer_button_is_pressed_the_answer_is_shown() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true);
        presenter.showAnswerPressed();

        verify(view).showAnswer(true);
    }
}