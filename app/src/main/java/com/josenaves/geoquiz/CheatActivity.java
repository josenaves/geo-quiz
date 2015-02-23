package com.josenaves.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";

    public static final String EXTRA_ANSWER_IS_TRUE = "com.josenaves.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.josenaves.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    @InjectView(R.id.answerTextView) TextView mAnswerTextView;
    @InjectView(R.id.showAnswerButton) Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        setAnswerShownResult(false);

        // ButterKnife, please inject your views!
        ButterKnife.inject(this);
    }

    @OnClick(R.id.showAnswerButton)
    void buttonShowAnswerClick() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        }
        else {
            mAnswerTextView.setText(R.string.false_button);
        }
        setAnswerShownResult(true);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
