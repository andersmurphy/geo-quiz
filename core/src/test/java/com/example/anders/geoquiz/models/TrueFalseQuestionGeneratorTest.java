package com.example.anders.geoquiz.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrueFalseQuestionGeneratorTest {

    @Test
    public void when_generate_question_is_called_1_time_it_generates_question_in_position_1_of_the_list(){
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        TrueFalseQuestion actualQuestion = generator.generateQuestion();

        TrueFalseQuestion expectedQuestion = firstQuestion();
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void when_generate_question_is_called_x_times_it_generates_question_in_position_x_of_the_list(){
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateQuestion();
        generator.generateQuestion();
        TrueFalseQuestion actualQuestion = generator.generateQuestion();

        TrueFalseQuestion expectedQuestion = thirdQuestion();
        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void when_generate_question_is_called_and_there_are_no_questions_left_generate_the_question_in_position_1_of_the_list(){
        List<TrueFalseQuestion> questions = new ArrayList<>();
        questions.add(firstQuestion());
        questions.add(secondQuestion());
        questions.add(thirdQuestion());

        QuestionGenerator generator = new TrueFalseQuestionGenerator(questions);
        generator.generateQuestion();
        generator.generateQuestion();
        generator.generateQuestion();
        TrueFalseQuestion actualQuestion = generator.generateQuestion();

        TrueFalseQuestion expectedQuestion = firstQuestion();
        assertEquals(expectedQuestion, actualQuestion);
    }

    private TrueFalseQuestion thirdQuestion() {
        return new TrueFalseQuestion(3,true);
    }

    private TrueFalseQuestion secondQuestion() {
        return new TrueFalseQuestion(2,true);
    }

    private TrueFalseQuestion firstQuestion() {
        return new TrueFalseQuestion(1,true);
    }
}