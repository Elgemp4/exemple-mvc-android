package com.example.labo2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.labo2.R;
import com.example.labo2.view.CheatView;

public class CheatActivity extends AppCompatActivity implements CheatView.Listener {

    public static final String ANSWER_EXTRA = "com.example.labo2.answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.labo2.answer_shown";

    private CheatView mView;

    private boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new CheatView(this);
        mView.setListener(this);

        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_EXTRA, false);
    }


    @Override
    public void onShowAnswer() {
        mView.setAnswer(mAnswerIsTrue);

        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, true);
        this.setResult(RESULT_OK, data);
    }
}