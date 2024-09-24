package com.example.labo2.view;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;

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

    private Listerner listerner;


    public MainView(AppCompatActivity activity) {
        super(activity);
    }

    public void setListener(Listerner listerner) {
        this.listerner = listerner;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    public interface Listerner{

    }
}
