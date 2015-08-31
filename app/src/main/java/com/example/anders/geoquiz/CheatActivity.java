package com.example.anders.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anders.geoquiz.presenters.CheatPage;
import com.example.anders.geoquiz.presenters.CheatPresenter;
import com.example.anders.geoquiz.presenters.CheatView;

public class CheatActivity extends BaseActivity implements CheatView {

    private CheatPresenter presenter;
    private Button showAnswerButton;
    private TextView answerTextView;
    private boolean answerShown = false;
    public static final String EXTRA_ANSWER_IS_TRUE = "EXTRA_ANSWER_IS_TRUE";
    public static final String EXTRA_ANSWER_SHOWN = "EXTRA_ANSWER_SHOWN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        boolean isTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        presenter = new CheatPage(this, isTrue);
        answerShown = savedInstanceState != null && savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
        if(answerShown) {
            storeThatTheAnswerHasBeenShown();
        }
        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(showAnswerButtonListener());

        answerTextView = (TextView) findViewById(R.id.answerTextView);
    }

    @Override
    public void storeThatTheAnswerHasBeenShown() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, true);
        setResult(RESULT_OK, data);
        answerShown = true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
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
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showAnswerPressed();
            }
        };
    }
}
