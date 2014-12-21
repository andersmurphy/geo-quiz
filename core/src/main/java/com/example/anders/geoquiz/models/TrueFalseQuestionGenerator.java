package com.example.anders.geoquiz.models;

import java.util.List;

/**
 * Created by anders on 18/12/14.
 */
public class TrueFalseQuestionGenerator implements QuestionGenerator {

    private final List<TrueFalseQuestion> questions;
    private int currentQuestionIndex;
    private final int FIRST_QUESTION_INDEX = 0;
    private final int lastQuestionIndex;

    public TrueFalseQuestionGenerator(List<TrueFalseQuestion> questions){
        this.questions = questions;
        currentQuestionIndex = FIRST_QUESTION_INDEX;
        lastQuestionIndex = calculateLastQuestionIndex();
    }

    @Override
    public TrueFalseQuestion generateFirstQuestion() {
        currentQuestionIndex = FIRST_QUESTION_INDEX;
        return questions.get(FIRST_QUESTION_INDEX);
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

    private int calculateLastQuestionIndex() {
        return questions.size() - 1;
    }
}
