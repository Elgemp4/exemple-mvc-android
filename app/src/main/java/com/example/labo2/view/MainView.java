package com.example.labo2.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;
import com.example.labo2.controller.CheatActivity;
import com.example.labo2.model.Quiz;

public class MainView extends View {
    private Button mTrueButton;
    private Button mFalsebButton;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mResetButton;
    private Button mCheatButton;

    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private TextView mCheatCountTextView;

    private Listerner listener;

    private AppCompatActivity mActivity;


    public MainView(AppCompatActivity activity) {
        super(activity);
        this.mActivity = activity;
        findComponents();
        registerListeners();
    }

    @Override
    public void refresh() {
        mScoreTextView.setText(mActivity.getString(R.string.score, listener.getScore(), listener.getQuestionCount()));
        mCheatCountTextView.setText(mActivity.getString(R.string.cheat_count, listener.getCheatCount(), listener.getQuestionCount()));
        mQuestionTextView.setText(listener.getQuestionText());
    }

    public void setListener(Listerner listerner) {
        this.listener = listerner;
        refresh();
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
            listener.setResponse(true);
        });

        mFalsebButton.setOnClickListener((v) -> {
            listener.setResponse(false);
        });

        mNextButton.setOnClickListener((v) -> {
            listener.changeQuestion(1);
        });

        mPreviousButton.setOnClickListener((v) -> {
            listener.changeQuestion(-1);
        });

        mResetButton.setOnClickListener((v) -> {
            listener.resetQuiz();
        });

        mCheatButton.setOnClickListener((v) -> {
            listener.cheat();
        });
    }

    public interface Listerner{
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
