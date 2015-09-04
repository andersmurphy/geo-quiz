package com.andersmurphy.geoquiz.presenters;

import com.andersmurphy.geoquiz.models.QuestionGenerator;
import com.andersmurphy.geoquiz.models.TrueFalseQuestion;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class QuizPageTest {


    @Test
    public void when_the_true_button_is_pressed_and_the_question_is_true_then_the_correct_toast_is_shown() {
        TrueFalseQuestion question = anyTrueQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.truePressed();

        verify(view).toastCorrect();
    }

    @Test
    public void when_the_true_button_is_pressed_and_the_question_is_false_then_the_incorrect_toast_is_shown() {
        TrueFalseQuestion question = anyFalseQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.truePressed();


        verify(view).toastIncorrect();
    }

    @Test
    public void when_the_false_button_is_pressed_and_the_question_is_true_then_the_correct_toast_is_shown() {
        TrueFalseQuestion question = anyTrueQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.falsePressed();

        verify(view).toastIncorrect();
    }

    @Test
    public void when_the_false_button_is_pressed_and_the_question_is_false_then_the_correct_toast_is_shown() {
        TrueFalseQuestion question = anyFalseQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.falsePressed();

        verify(view).toastCorrect();
    }

    @Test
    public void when_the_false_button_is_pressed_and_the_user_cheated_the_judgement_toast_is_shown() {
        TrueFalseQuestion question = anyFalseQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.resultFromCheatScreen(true);
        presenter.falsePressed();

        verify(view, never()).toastIncorrect();
        verify(view).toastJudgement();
    }

    @Test
    public void when_the_true_button_is_pressed_and_the_user_cheated_the_judgement_toast_is_shown() {
        TrueFalseQuestion question = anyFalseQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);


        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.resultFromCheatScreen(true);
        presenter.truePressed();

        verify(view, never()).toastCorrect();
        verify(view).toastJudgement();
    }

    @Test
    public void when_the_next_button_is_pressed_the_next_question_is_shown() {
        TrueFalseQuestion question = new TrueFalseQuestion(anyQuestionId(), true);
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateNextQuestion()).toReturn(question);

        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.nextPressed();

        verify(view).setQuestionTextView(anyQuestionId());
    }

    @Test
    public void when_the_previous_button_is_pressed_the_previous_question_is_shown() {
        TrueFalseQuestion question = new TrueFalseQuestion(anyQuestionId(), true);
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generatePreviousQuestion()).toReturn(question);

        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.previousPressed();

        verify(view).setQuestionTextView(anyQuestionId());
    }

    @Test
    public void when_the_cheat_button_is_pressed_the_cheat_activity_is_launched() {
        TrueFalseQuestion question = anyTrueQuestion();
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.generateStartingQuestion()).toReturn(question);

        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.prepareFirstQuestion();
        presenter.cheatPressed();

        verify(view).launchCheatActivity(question.isTrue());
    }

    @Test
    public void when_saving_state_save_current_question_index() throws Exception {
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);
        stub(generator.getCurrentQuestionIndex()).toReturn(1);

        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.saveState();

        verify(view).saveCurrentQuestionIndex(1);
    }

    @Test
    public void when_saving_state_save_whether_user_cheated() throws Exception {
        QuizView view = mock(QuizView.class);
        QuestionGenerator generator = mock(QuestionGenerator.class);

        QuizPresenter presenter = new QuizPage(view, generator);
        presenter.resultFromCheatScreen(true);
        presenter.saveState();

        verify(view).saveUserCheated(true);
    }

    private int anyQuestionId() {
        return 1;
    }

    private TrueFalseQuestion anyTrueQuestion() {
        return new TrueFalseQuestion(1, true);
    }

    private TrueFalseQuestion anyFalseQuestion() {
        return new TrueFalseQuestion(2, false);
    }
}