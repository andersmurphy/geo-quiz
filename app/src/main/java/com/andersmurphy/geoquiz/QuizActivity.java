package com.andersmurphy.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.andersmurphy.geoquiz.models.TrueFalseQuestion;
import com.andersmurphy.geoquiz.models.TrueFalseQuestionGenerator;
import com.andersmurphy.geoquiz.presenters.QuizPage;
import com.andersmurphy.geoquiz.presenters.QuizPresenter;
import com.andersmurphy.geoquiz.presenters.QuizView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends ActionBarActivity implements QuizView {

    private final static String KEY_QUESTION_INDEX = "questionIndex";

    private QuizPresenter presenter;

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton previousButton;
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

        int currentQuestionIndex = savedInstanceState != null ? savedInstanceState.getInt(KEY_QUESTION_INDEX, 0) : 0;
        presenter = new QuizPage(this, new TrueFalseQuestionGenerator(questionBank, currentQuestionIndex));

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        presenter.prepareFirstQuestion();

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(trueButtonOnClickListener());

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(falseButtonOnClickListener());

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(nextButtonOnClickListener());

        previousButton = (ImageButton) findViewById(R.id.previous_button);
        previousButton.setOnClickListener(previousButtonOnClickListener());

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int currentQuestionIndex = presenter.onSaveInstanceState();
        savedInstanceState.putInt(KEY_QUESTION_INDEX, currentQuestionIndex);
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
}
