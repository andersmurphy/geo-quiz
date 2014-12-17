package com.example.anders.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anders.geoquiz.presenters.QuizPage;
import com.example.anders.geoquiz.presenters.QuizPresenter;
import com.example.anders.geoquiz.presenters.QuizView;

public class QuizActivity extends ActionBarActivity implements QuizView {

    private Button trueButton;
    private Button falseButton;
    private QuizPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        presenter = new QuizPage(this);

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.truePressed();
            }
        });

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.falsePressed();
            }
        });
    }

    @Override
    public void toastIncorrect() {
        Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toastCorrect() {
        Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show();
    }
}
