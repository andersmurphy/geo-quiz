package com.example.anders.geoquiz.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrueFalseQuestionGeneratorTest {

    @Test
    public void when_generate_first_question_is_called_it_returns_the_first_question() {
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        TrueFalseQuestion actualQuestion = generator.generateStartingQuestion();

        TrueFalseQuestion expectedQuestion = firstQuestion();
        assertThat(actualQuestion, is(equalTo(expectedQuestion)));
    }

    @Test
    public void when_generate_next_question_is_called_it_returns_the_next_question() {
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateStartingQuestion();
        generator.generateNextQuestion();
        TrueFalseQuestion actualQuestion = generator.generateNextQuestion();

        TrueFalseQuestion expectedQuestion = thirdQuestion();
        assertThat(actualQuestion, is(equalTo(expectedQuestion)));
    }

    @Test
    public void when_generate_next_question_is_called_and_there_are_no_questions_left_return_the_first_question() {
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateStartingQuestion();
        generator.generateNextQuestion();
        generator.generateNextQuestion();
        TrueFalseQuestion actualQuestion = generator.generateNextQuestion();

        TrueFalseQuestion expectedQuestion = firstQuestion();
        assertThat(actualQuestion, is(equalTo(expectedQuestion)));
    }

    @Test
    public void when_generate_previous_question_is_called_it_returns_the_previous_question() {
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateStartingQuestion();
        generator.generateNextQuestion();
        TrueFalseQuestion actualQuestion = generator.generatePreviousQuestion();

        TrueFalseQuestion expectedQuestion = firstQuestion();
        assertThat(actualQuestion, is(equalTo(expectedQuestion)));
    }

    @Test
    public void when_generate_previous_question_is_called_and_there_are_no_questions_left_return_the_last_question() {
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateStartingQuestion();
        TrueFalseQuestion actualQuestion = generator.generatePreviousQuestion();

        TrueFalseQuestion expectedQuestion = thirdQuestion();
        assertThat(actualQuestion, is(equalTo(expectedQuestion)));
    }


    private TrueFalseQuestion thirdQuestion() {
        return new TrueFalseQuestion(3, true);
    }

    private TrueFalseQuestion secondQuestion() {
        return new TrueFalseQuestion(2, true);
    }

    private TrueFalseQuestion firstQuestion() {
        return new TrueFalseQuestion(1, true);
    }
}