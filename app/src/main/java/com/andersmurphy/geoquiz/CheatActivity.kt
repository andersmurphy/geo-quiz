package com.andersmurphy.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.andersmurphy.geoquiz.presenters.CheatPage
import com.andersmurphy.geoquiz.presenters.CheatPresenter
import com.andersmurphy.geoquiz.presenters.CheatView
import kotlinx.android.synthetic.activity_cheat.*

@ContentView(R.layout.activity_cheat)
class CheatActivity : BaseActivity(), CheatView {

    private lateinit var presenter: CheatPresenter
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isQuestionAnswerTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        val hasAnswerButtonBeenShown = savedInstanceState != null && savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false)
        presenter = CheatPage(this, isQuestionAnswerTrue, hasAnswerButtonBeenShown)

        show_answer_button.setOnClickListener(showAnswerButtonListener())
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        presenter.saveState()
    }

    override fun onBackPressed() {
        presenter.setResult()
        super.onBackPressed()
    }

    override fun setResultAnswerShown(hasAnswerBeenShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, hasAnswerBeenShown)
        setResult(Activity.RESULT_OK, data)
    }

    override fun saveAnswerShown(answerShown: Boolean) {
        savedInstanceState!!.putBoolean(EXTRA_ANSWER_SHOWN, answerShown)
    }

    override fun showAnswerIsTrue() {
        answer_text_view.setText(R.string.true_button)
    }

    override fun showAnswerIsFalse() {
        answer_text_view.setText(R.string.false_button)
    }

    private fun showAnswerButtonListener(): View.OnClickListener {
        return View.OnClickListener { presenter.showAnswerPressed()}
    }

    companion object {

        public val EXTRA_ANSWER_IS_TRUE: String = "EXTRA_ANSWER_IS_TRUE"
        public val EXTRA_ANSWER_SHOWN: String = "EXTRA_ANSWER_SHOWN"
    }
}
