package com.example.anders.geoquiz.models;

/**
 * Created by anders on 18/12/14.
 */
public interface QuestionGenerator {
    TrueFalseQuestion generateStartingQuestion();

    TrueFalseQuestion generateNextQuestion();

    TrueFalseQuestion generatePreviousQuestion();

    int getCurrentQuestionIndex();
}
