package com.example.anders.geoquiz.models;

/**
 * Created by anders on 18/12/14.
 */
public final class TrueFalseQuestion {

    private final int questionId;
    private final boolean isTrue;

    public TrueFalseQuestion(int questionId, boolean isTrue) {

        this.questionId = questionId;
        this.isTrue = isTrue;
    }

    public int getQuestionId() {
        return questionId;
    }

    public boolean isTrue() {
        return isTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrueFalseQuestion question = (TrueFalseQuestion) o;

        if (isTrue != question.isTrue) return false;
        if (questionId != question.questionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (isTrue ? 1 : 0);
        return result;
    }
}
