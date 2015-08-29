package com.example.anders.geoquiz.presenters;

import com.example.anders.geoquiz.models.QuestionGenerator;
import com.example.anders.geoquiz.models.TrueFalseQuestion;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
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