package com.andersmurphy.geoquiz.presenters

import com.andersmurphy.geoquiz.models.QuestionGenerator
import com.andersmurphy.geoquiz.models.TrueFalseQuestion
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.stub
import org.mockito.Mockito.verify

class QuizPageTest {

    @Test
    fun when_the_true_button_is_pressed_and_the_question_is_true_then_the_correct_toast_is_shown() {
        val question = anyTrueQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.truePressed()

        verify(view).toastCorrect()
    }

    @Test
    fun when_the_true_button_is_pressed_and_the_question_is_false_then_the_incorrect_toast_is_shown() {
        val question = anyFalseQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.truePressed()


        verify(view).toastIncorrect()
    }

    @Test
    fun when_the_false_button_is_pressed_and_the_question_is_true_then_the_correct_toast_is_shown() {
        val question = anyTrueQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.falsePressed()

        verify(view).toastIncorrect()
    }

    @Test
    fun when_the_false_button_is_pressed_and_the_question_is_false_then_the_correct_toast_is_shown() {
        val question = anyFalseQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.falsePressed()

        verify(view).toastCorrect()
    }

    @Test
    fun when_the_false_button_is_pressed_and_the_user_cheated_the_judgement_toast_is_shown() {
        val question = anyFalseQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.resultFromCheatScreen(true)
        presenter.falsePressed()

        verify(view, never()).toastIncorrect()
        verify(view).toastJudgement()
    }

    @Test
    fun when_the_true_button_is_pressed_and_the_user_cheated_the_judgement_toast_is_shown() {
        val question = anyFalseQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)


        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.resultFromCheatScreen(true)
        presenter.truePressed()

        verify(view, never()).toastCorrect()
        verify(view).toastJudgement()
    }

    @Test
    fun when_the_next_button_is_pressed_the_next_question_is_shown() {
        val question = TrueFalseQuestion(anyQuestionId(), true)
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateNextQuestion()).toReturn(question)

        val presenter = QuizPage(view, generator)
        presenter.nextPressed()

        verify(view).setQuestionTextView(anyQuestionId())
    }

    @Test
    fun when_the_previous_button_is_pressed_the_previous_question_is_shown() {
        val question = TrueFalseQuestion(anyQuestionId(), true)
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generatePreviousQuestion()).toReturn(question)

        val presenter = QuizPage(view, generator)
        presenter.previousPressed()

        verify(view).setQuestionTextView(anyQuestionId())
    }

    @Test
    fun when_the_cheat_button_is_pressed_the_cheat_activity_is_launched() {
        val question = anyTrueQuestion()
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.generateStartingQuestion()).toReturn(question)

        val presenter = QuizPage(view, generator)
        presenter.prepareFirstQuestion()
        presenter.cheatPressed()

        verify(view).launchCheatActivity(question.isTrue)
    }

    @Test
    fun when_saving_state_save_current_question_index() {
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)
        stub(generator.getCurrentQuestionIndex()).toReturn(1)

        val presenter = QuizPage(view, generator)
        presenter.saveState()

        verify(view).saveCurrentQuestionIndex(1)
    }

    @Test
    fun when_saving_state_save_whether_user_cheated() {
        val view = mock(QuizView::class.java)
        val generator = mock(QuestionGenerator::class.java)

        val presenter = QuizPage(view, generator)
        presenter.resultFromCheatScreen(true)
        presenter.saveState()

        verify(view).saveUserCheated(true)
    }

    private fun anyQuestionId(): Int {
        return 1
    }

    private fun anyTrueQuestion(): TrueFalseQuestion {
        return TrueFalseQuestion(1, true)
    }

    private fun anyFalseQuestion(): TrueFalseQuestion {
        return TrueFalseQuestion(2, false)
    }
}