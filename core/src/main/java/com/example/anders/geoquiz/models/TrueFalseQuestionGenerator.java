package com.example.anders.geoquiz.models;

import java.util.List;

/**
 * Created by anders on 18/12/14.
 */
public final class TrueFalseQuestionGenerator implements QuestionGenerator {

    private final List<TrueFalseQuestion> questions;
    private int currentQuestionIndex;
    private final int FIRST_QUESTION_INDEX = 0;
    private final int lastQuestionIndex;

    public TrueFalseQuestionGenerator(List<TrueFalseQuestion> questions) {
        this(questions, 0);
    }

    public TrueFalseQuestionGenerator(List<TrueFalseQuestion> questions, int startingQuestionIndex) {
        this.questions = questions;
        currentQuestionIndex = startingQuestionIndex;
        lastQuestionIndex = calculateLastQuestionIndex();
    }

    @Override
    public TrueFalseQuestion generateStartingQuestion() {
        return questions.get(currentQuestionIndex);
    }

    @Override
    public TrueFalseQuestion generateNextQuestion() {
        int nextQuestionIndex = currentQuestionIndex >= lastQuestionIndex ? FIRST_QUESTION_INDEX : ++currentQuestionIndex;
        TrueFalseQuestion nextQuestion = questions.get(nextQuestionIndex);
        currentQuestionIndex = nextQuestionIndex;
        return nextQuestion;
    }

    @Override
    public TrueFalseQuestion generatePreviousQuestion() {
        int previousQuestionIndex = currentQuestionIndex <= FIRST_QUESTION_INDEX ? lastQuestionIndex : --currentQuestionIndex;
        TrueFalseQuestion previousQuestion = questions.get(previousQuestionIndex);
        currentQuestionIndex = previousQuestionIndex;
        return previousQuestion;
    }

    @Override
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    private int calculateLastQuestionIndex() {
        return questions.size() - 1;
    }
}
