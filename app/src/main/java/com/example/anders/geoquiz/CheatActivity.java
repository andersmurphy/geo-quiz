package com.example.anders.geoquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anders.geoquiz.presenters.CheatPage;
import com.example.anders.geoquiz.presenters.CheatPresenter;
import com.example.anders.geoquiz.presenters.CheatView;

public class CheatActivity extends BaseActivity implements CheatView {

    private CheatPresenter presenter;
    private Button showAnswerButton;
    public final static String EXTRA_ANSWER_IS_TRUE = "EXTRA_ANSWER_IS_TRUE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        boolean isTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        presenter = new CheatPage(this, isTrue);
        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(showAnswerButtonListener());
    }

    @Override
    public void storeThatUserHasCheated() {

    }

    @Override
    public void showAnswer(boolean isTrue) {
        Toast.makeText(this, String.valueOf(isTrue), Toast.LENGTH_LONG).show();
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
