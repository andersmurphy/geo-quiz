package com.andersmurphy.geoquiz.models

import org.junit.Test

import java.util.ArrayList

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*

class TrueFalseQuestionGeneratorTest {

    @Test
    fun when_generate_first_question_is_called_it_returns_the_first_question() {
        val questions = ArrayList<TrueFalseQuestion>()
        questions.add(firstQuestion())
        questions.add(secondQuestion())
        questions.add(thirdQuestion())

        val generator = TrueFalseQuestionGenerator(questions)
        val actualQuestion = generator.generateStartingQuestion()

        val expectedQuestion = firstQuestion()
        assertThat(actualQuestion, `is`(equalTo(expectedQuestion)))
    }

    @Test
    fun when_generate_next_question_is_called_it_returns_the_next_question() {
        val questions = ArrayList<TrueFalseQuestion>()
        questions.add(firstQuestion())
        questions.add(secondQuestion())
        questions.add(thirdQuestion())

        val generator = TrueFalseQuestionGenerator(questions)
        generator.generateStartingQuestion()
        generator.generateNextQuestion()
        val actualQuestion = generator.generateNextQuestion()

        val expectedQuestion = thirdQuestion()
        assertThat(actualQuestion, `is`(equalTo(expectedQuestion)))
    }

    @Test
    fun when_generate_next_question_is_called_and_there_are_no_questions_left_return_the_first_question() {
        val questions = ArrayList<TrueFalseQuestion>()
        questions.add(firstQuestion())
        questions.add(secondQuestion())
        questions.add(thirdQuestion())

        val generator = TrueFalseQuestionGenerator(questions)
        generator.generateStartingQuestion()
        generator.generateNextQuestion()
        generator.generateNextQuestion()
        val actualQuestion = generator.generateNextQuestion()

        val expectedQuestion = firstQuestion()
        assertThat(actualQuestion, `is`(equalTo(expectedQuestion)))
    }

    @Test
    fun when_generate_previous_question_is_called_it_returns_the_previous_question() {
        val questions = ArrayList<TrueFalseQuestion>()
        questions.add(firstQuestion())
        questions.add(secondQuestion())
        questions.add(thirdQuestion())

        val generator = TrueFalseQuestionGenerator(questions)
        generator.generateStartingQuestion()
        generator.generateNextQuestion()
        val actualQuestion = generator.generatePreviousQuestion()

        val expectedQuestion = firstQuestion()
        assertThat(actualQuestion, `is`(equalTo(expectedQuestion)))
    }

    @Test
    fun when_generate_previous_question_is_called_and_there_are_no_questions_left_return_the_last_question() {
        val questions = ArrayList<TrueFalseQuestion>()
        questions.add(firstQuestion())
        questions.add(secondQuestion())
        questions.add(thirdQuestion())

        val generator = TrueFalseQuestionGenerator(questions)
        generator.generateStartingQuestion()
        val actualQuestion = generator.generatePreviousQuestion()

        val expectedQuestion = thirdQuestion()
        assertThat(actualQuestion, `is`(equalTo(expectedQuestion)))
    }


    private fun thirdQuestion(): TrueFalseQuestion {
        return TrueFalseQuestion(3, true)
    }

    private fun secondQuestion(): TrueFalseQuestion {
        return TrueFalseQuestion(2, true)
    }

    private fun firstQuestion(): TrueFalseQuestion {
        return TrueFalseQuestion(1, true)
    }
}