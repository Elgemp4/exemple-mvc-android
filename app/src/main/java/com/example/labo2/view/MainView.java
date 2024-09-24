package com.example.labo2.view;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;

public class MainView extends View<MainView.Listener> {
    private Button mTrueButton;
    private Button mFalsebButton;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mResetButton;
    private Button mCheatButton;

    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private TextView mCheatCountTextView;

    public MainView(AppCompatActivity activity) {
        super(activity);
        findComponents();
        registerListeners();
    }

    public void showMessage(CharSequence message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh() {
        mScoreTextView.setText(mActivity.getString(R.string.score, mListener.getScore(), mListener.getQuestionCount()));
        mCheatCountTextView.setText(mActivity.getString(R.string.cheat_count, mListener.getCheatCount(), mListener.getQuestionCount()));
        mQuestionTextView.setText(mListener.getQuestionText());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private void findComponents() {
        mTrueButton = mActivity.findViewById(R.id.true_button);
        mFalsebButton = mActivity.findViewById(R.id.false_button);
        mNextButton = mActivity.findViewById(R.id.next_button);
        mPreviousButton = mActivity.findViewById(R.id.previous_button);
        mResetButton = mActivity.findViewById(R.id.reset_button);
        mCheatButton = mActivity.findViewById(R.id.cheat_button);

        mQuestionTextView = mActivity.findViewById(R.id.question_text);
        mScoreTextView = mActivity.findViewById(R.id.score_text);
        mCheatCountTextView = mActivity.findViewById(R.id.cheat_count_text);
    }

    private void registerListeners(){
        mTrueButton.setOnClickListener((v) -> {
            mListener.setResponse(true);
        });

        mFalsebButton.setOnClickListener((v) -> {
            mListener.setResponse(false);
        });

        mNextButton.setOnClickListener((v) -> {
            mListener.changeQuestion(1);
        });

        mPreviousButton.setOnClickListener((v) -> {
            mListener.changeQuestion(-1);
        });

        mResetButton.setOnClickListener((v) -> {
            mListener.resetQuiz();
        });

        mCheatButton.setOnClickListener((v) -> {
            mListener.cheat();
        });
    }

    public void disableCheatButton() {
        mCheatButton.setEnabled(false);
    }

    public void enableCheatButton() {
        mCheatButton.setEnabled(true);
    }

    public void disableNavigationButtons() {
        mNextButton.setEnabled(false);
        mPreviousButton.setEnabled(false);
    }

    public void enableNavigationButtons() {
        mNextButton.setEnabled(true);
        mPreviousButton.setEnabled(true);
    }

    public void disableAnswerButtons() {
        mTrueButton.setEnabled(false);
        mFalsebButton.setEnabled(false);
    }

    public void enableAnswerButtons() {
        mTrueButton.setEnabled(true);
        mFalsebButton.setEnabled(true);
    }

    public interface Listener {
        int getQuestionCount();
        int getScore();
        int getCheatCount();
        int getQuestionText();

        void setResponse(boolean response);
        void changeQuestion(int direction);
        void resetQuiz();
        void cheat();
    }
}
