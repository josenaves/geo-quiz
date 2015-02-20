package com.josenaves.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class QuizActivity extends ActionBarActivity {

    @InjectView(R.id.true_button) Button mTrueButton;
    @InjectView(R.id.false_button) Button mFalseButton;
    @InjectView(R.id.next_button) ImageButton mNextButton;
    @InjectView(R.id.previous_button) ImageButton mPreviousButton;
    @InjectView(R.id.question_text_view) TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_oceans, true),
        new TrueFalse(R.string.question_mideast, false),
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // inflates the layout (instantiates components)

        ButterKnife.inject(this); // ButterKnife do its stuff

        updateQuestion();
    }

    @OnClick(R.id.true_button)
    void checkAnswerTrue() {
        checkAnswer(true);
    }

    @OnClick(R.id.false_button)
    void checkAnswerFalse() {
        checkAnswer(false);
    }

    @OnClick({R.id.next_button, R.id.question_text_view})
    void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    @OnClick(R.id.previous_button)
    void previousQuestion() {
        if (mCurrentIndex < 1) return;

        mCurrentIndex =  (mCurrentIndex - 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }
}
