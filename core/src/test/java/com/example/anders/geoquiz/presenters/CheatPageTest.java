package com.example.anders.geoquiz.presenters;

import com.example.anders.geoquiz.models.QuestionGenerator;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheatPageTest {

    @Test
    public void when_the_show_answer_button_is_pressed_true_is_shown_if_the_answer_is_true() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true, false);
        presenter.showAnswerPressed();

        verify(view).showAnswerIsTrue();
    }

    @Test
    public void when_the_show_answer_button_is_pressed_false_is_shown_if_the_answer_is_false() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, false, false);
        presenter.showAnswerPressed();

        verify(view).showAnswerIsFalse();
    }

    @Test
    public void when_saving_state_save_answer_shown() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true, hasAnswerBeenShown());
        presenter.saveState();

        verify(view).saveAnswerShown(hasAnswerBeenShown());
    }

    @Test
    public void when_saving_state_set_result_to_answer_shown() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true, hasAnswerBeenShown());
        presenter.setResult();

        verify(view).setResultAnswerShown(hasAnswerBeenShown());
    }

    @Test
    public void when_saving_state_save_answer_shown_to_true_if_show_answer_pressed() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true, false);
        presenter.showAnswerPressed();
        presenter.saveState();

        verify(view).saveAnswerShown(true);
    }

    @Test
    public void when_saving_state_set_result_to__answer_shown_to_true_if_show_answer_pressed() throws Exception {
        CheatView view = mock(CheatView.class);

        CheatPresenter presenter = new CheatPage(view, true, false);
        presenter.showAnswerPressed();
        presenter.setResult();

        verify(view).setResultAnswerShown(true);
    }

    private boolean hasAnswerBeenShown() {
        return false;
    }
}