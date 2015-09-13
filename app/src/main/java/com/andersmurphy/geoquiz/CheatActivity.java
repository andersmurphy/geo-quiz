package com.andersmurphy.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andersmurphy.geoquiz.presenters.CheatPage;
import com.andersmurphy.geoquiz.presenters.CheatPresenter;
import com.andersmurphy.geoquiz.presenters.CheatView;

import butterknife.Bind;

@ContentView(R.layout.activity_cheat)
public class CheatActivity extends BaseActivity implements CheatView {

    public static final String EXTRA_ANSWER_IS_TRUE = "EXTRA_ANSWER_IS_TRUE";
    public static final String EXTRA_ANSWER_SHOWN = "EXTRA_ANSWER_SHOWN";

    @Bind(R.id.show_answer_button)
    Button showAnswerButton;

    @Bind(R.id.answer_text_view)
    TextView answerTextView;

    private Bundle savedInstanceState;
    private CheatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isQuestionAnswerTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        boolean hasAnswerButtonBeenShown = savedInstanceState != null && savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
        presenter = new CheatPage(this, isQuestionAnswerTrue, hasAnswerButtonBeenShown);

        showAnswerButton.setOnClickListener(showAnswerButtonListener());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        presenter.saveState();
    }

    @Override
    public void onBackPressed() {
        presenter.setResult();
        super.onBackPressed();
    }

    @Override
    public void setResultAnswerShown(boolean hasAnswerBeenShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, hasAnswerBeenShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void saveAnswerShown(boolean answerShown) {
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, answerShown);
    }

    @Override
    public void showAnswerIsTrue() {
        answerTextView.setText(R.string.true_button);
    }

    @Override
    public void showAnswerIsFalse() {
        answerTextView.setText(R.string.false_button);
    }

    private View.OnClickListener showAnswerButtonListener() {
        return v -> presenter.showAnswerPressed();
    }
}
