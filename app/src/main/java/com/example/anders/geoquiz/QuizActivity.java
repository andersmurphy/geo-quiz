package com.example.anders.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anders.geoquiz.models.TrueFalseQuestion;
import com.example.anders.geoquiz.models.TrueFalseQuestionGenerator;
import com.example.anders.geoquiz.presenters.QuizPage;
import com.example.anders.geoquiz.presenters.QuizPresenter;
import com.example.anders.geoquiz.presenters.QuizView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends ActionBarActivity implements QuizView {

    private QuizPresenter presenter;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previousButton;

    private TextView questionTextView;

    private final List<TrueFalseQuestion> questionBank;

    public QuizActivity() {
        questionBank = new ArrayList<>();
        questionBank.add(new TrueFalseQuestion(R.string.question_oceans, true));
        questionBank.add(new TrueFalseQuestion(R.string.question_mideast, false));
        questionBank.add(new TrueFalseQuestion(R.string.question_africa, false));
        questionBank.add(new TrueFalseQuestion(R.string.question_americas, true));
        questionBank.add(new TrueFalseQuestion(R.string.question_asia, true));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        presenter = new QuizPage(this, new TrueFalseQuestionGenerator(questionBank));

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        presenter.prepareFirstQuestion();

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(trueButtonOnClickListener());

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(falseButtonOnClickListener());

        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(nextButtonOnClickListener());

        previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setOnClickListener(previousButtonOnClickListener());

    }

    private View.OnClickListener previousButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.previousPressed();
            }
        };
    }

    private View.OnClickListener nextButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextPressed();
            }
        };
    }

    private View.OnClickListener falseButtonOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.falsePressed();
            }
        };
    }

    private View.OnClickListener trueButtonOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.truePressed();
            }
        };
    }

    @Override
    public void toastIncorrect() {
        Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toastCorrect() {
        Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setQuestionTextView(int id) {
        questionTextView.setText(id);
    }
}
