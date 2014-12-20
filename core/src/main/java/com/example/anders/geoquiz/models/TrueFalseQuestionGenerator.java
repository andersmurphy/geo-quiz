package com.example.anders.geoquiz.models;

import java.util.List;

/**
 * Created by anders on 18/12/14.
 */
public class TrueFalseQuestionGenerator implements QuestionGenerator {

    private final List<TrueFalseQuestion> questions;
    private int currentQuestionIndex;

    public TrueFalseQuestionGenerator(List<TrueFalseQuestion> questions){
        this.questions = questions;
        currentQuestionIndex = 0;
    }

    @Override
    public TrueFalseQuestion generateQuestion() {
        if(currentQuestionIndex >= questions.size()){
            currentQuestionIndex = 0;
        }
        TrueFalseQuestion question = questions.get(currentQuestionIndex);
        currentQuestionIndex++;
        return question;
    }
}
