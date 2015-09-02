package com.example.anders.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anders.geoquiz.models.TrueFalseQuestion;
import com.example.anders.geoquiz.models.TrueFalseQuestionGenerator;
import com.example.anders.geoquiz.presenters.QuizPage;
import com.example.anders.geoquiz.presenters.QuizPresenter;
import com.example.anders.geoquiz.presenters.QuizView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends BaseActivity implements QuizView {

    private final static String KEY_QUESTION_INDEX = "questionIndex";

    private QuizPresenter presenter;

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private TextView questionTextView;
    private Button cheatButton;
    private Bundle savedInstanceState;

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
        presenter.resultFromCheatScreen(savedInstanceState != null && savedInstanceState.getBoolean(CheatActivity.EXTRA_ANSWER_SHOWN, false));

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

        cheatButton = (Button) findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(cheatButtonOnClickListener());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        presenter.saveState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            presenter.resultFromCheatScreen(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
        }
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

    @Override
    public void launchCheatActivity(boolean isTrue) {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, isTrue);
        startActivityForResult(intent, 0);
    }

    @Override
    public void toastJudgement() {
        Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void saveCurrentQuestionIndex(int currentQuestionIndex) {
        savedInstanceState.putInt(KEY_QUESTION_INDEX, currentQuestionIndex);
    }

    @Override
    public void saveUserCheated(boolean userCheated) {
        savedInstanceState.putBoolean(CheatActivity.EXTRA_ANSWER_SHOWN, userCheated);
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

    private View.OnClickListener cheatButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cheatPressed();
            }
        };
    }
}
