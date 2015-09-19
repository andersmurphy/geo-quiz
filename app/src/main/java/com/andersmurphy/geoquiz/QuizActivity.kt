package com.andersmurphy.geoquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.andersmurphy.geoquiz.models.TrueFalseQuestion
import com.andersmurphy.geoquiz.models.TrueFalseQuestionGenerator
import com.andersmurphy.geoquiz.presenters.NoOpQuizPage
import com.andersmurphy.geoquiz.presenters.QuizPage
import com.andersmurphy.geoquiz.presenters.QuizPresenter
import com.andersmurphy.geoquiz.presenters.QuizView

import java.util.ArrayList

import kotlinx.android.synthetic.activity_quiz.*

@ContentView(R.layout.activity_quiz)
class QuizActivity : BaseActivity(), QuizView {

    private var presenter: QuizPresenter = NoOpQuizPage()
    private var savedInstanceState: Bundle? = null

    private val questionBank: MutableList<TrueFalseQuestion>

    init {
        questionBank = ArrayList<TrueFalseQuestion>()
        questionBank.add(TrueFalseQuestion(R.string.question_oceans, true))
        questionBank.add(TrueFalseQuestion(R.string.question_mideast, false))
        questionBank.add(TrueFalseQuestion(R.string.question_africa, false))
        questionBank.add(TrueFalseQuestion(R.string.question_americas, true))
        questionBank.add(TrueFalseQuestion(R.string.question_asia, true))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentQuestionIndex = if (savedInstanceState != null) savedInstanceState.getInt(KEY_QUESTION_INDEX, 0) else 0
        presenter = QuizPage(this, TrueFalseQuestionGenerator(questionBank, currentQuestionIndex))
        presenter.resultFromCheatScreen(savedInstanceState != null && savedInstanceState.getBoolean(CheatActivity.EXTRA_ANSWER_SHOWN, false))
        presenter.prepareFirstQuestion()

        true_button.setOnClickListener(trueButtonOnClickListener())
        false_button.setOnClickListener(falseButtonOnClickListener())
        next_button.setOnClickListener(nextButtonOnClickListener())
        previous_button.setOnClickListener(previousButtonOnClickListener())
        cheat_button.setOnClickListener(cheatButtonOnClickListener())
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        presenter.saveState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            presenter.resultFromCheatScreen(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false))
        }
    }

    override fun toastIncorrect() {
        Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
    }

    override fun toastCorrect() {
        Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
    }

    override fun setQuestionTextView(id: Int) {
        question_text_view.setText(id)
    }

    override fun launchCheatActivity(isTrue: Boolean) {
        val intent = Intent(this, CheatActivity::class.java)
        intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, isTrue)
        startActivityForResult(intent, 0)
    }

    override fun toastJudgement() {
        Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_LONG).show()
    }

    override fun saveCurrentQuestionIndex(currentQuestionIndex: Int) {
        savedInstanceState!!.putInt(KEY_QUESTION_INDEX, currentQuestionIndex)
    }

    override fun saveUserCheated(userCheated: Boolean) {
        savedInstanceState!!.putBoolean(CheatActivity.EXTRA_ANSWER_SHOWN, userCheated)
    }

    private fun previousButtonOnClickListener(): View.OnClickListener {
        return View.OnClickListener { presenter.previousPressed() }
    }

    private fun nextButtonOnClickListener(): View.OnClickListener {
        return View.OnClickListener { presenter.nextPressed() }
    }

    private fun falseButtonOnClickListener(): View.OnClickListener {
        return View.OnClickListener { presenter.falsePressed() }
    }

    private fun trueButtonOnClickListener(): View.OnClickListener {
        return View.OnClickListener { presenter.truePressed() }
    }

    private fun cheatButtonOnClickListener(): View.OnClickListener {
        return View.OnClickListener { presenter.cheatPressed() }
    }

    companion object {

        private val KEY_QUESTION_INDEX = "questionIndex"
    }
}
