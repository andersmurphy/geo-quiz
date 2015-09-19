package com.andersmurphy.geoquiz.presenters
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CheatPageTest {

    @Test
    fun when_the_show_answer_button_is_pressed_true_is_shown_if_the_answer_is_true() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, true, false)
        presenter.showAnswerPressed()

        verify(view).showAnswerIsTrue()
    }

    @Test
    fun when_the_show_answer_button_is_pressed_false_is_shown_if_the_answer_is_false() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, false, false)
        presenter.showAnswerPressed()

        verify(view).showAnswerIsFalse()
    }

    @Test
    fun when_saving_state_save_answer_shown() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, true, hasAnswerBeenShown())
        presenter.saveState()

        verify(view).saveAnswerShown(hasAnswerBeenShown())
    }

    @Test
    fun when_saving_state_set_result_to_answer_shown() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, true, hasAnswerBeenShown())
        presenter.setResult()

        verify(view).setResultAnswerShown(hasAnswerBeenShown())
    }

    @Test
    fun when_saving_state_save_answer_shown_to_true_if_show_answer_pressed() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, true, false)
        presenter.showAnswerPressed()
        presenter.saveState()

        verify(view).saveAnswerShown(true)
    }

    @Test
    fun when_saving_state_set_result_to__answer_shown_to_true_if_show_answer_pressed() {
        val view = mock(CheatView::class.java)

        val presenter = CheatPage(view, true, false)
        presenter.showAnswerPressed()
        presenter.setResult()

        verify(view).setResultAnswerShown(true)
    }

    private fun hasAnswerBeenShown(): Boolean {
        return false
    }
}