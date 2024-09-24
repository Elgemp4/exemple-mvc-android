package com.example.labo2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.labo2.R;
import com.example.labo2.model.Quiz;
import com.example.labo2.view.MainView;

public class MainActivity extends AppCompatActivity {

    private MainView mView;



    private Quiz mQuiz = new Quiz();

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainApp", "onStart method");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainApp", "onStop method");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainApp", "onDestroy method");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainApp", "onPause method");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainApp", "onResume method");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainApp", "onRestart method");
    }

    private ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                Toast.makeText(this, R.string.answer_has_been_shown, Toast.LENGTH_SHORT).show();
                mQuiz.setCheated(true);
                actualizeDisplay();
            }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new MainView(this);

        if(savedInstanceState != null){
            mQuiz = (Quiz) savedInstanceState.getSerializable("quiz");
        }
        Log.d("MainApp", "onCreate method");
        findComponents();
        registerListeners();

        actualizeDisplay();


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("quiz", mQuiz);
    }

    private void findComponents() {
        mTrueButton = findViewById(R.id.true_button);
        mFalsebButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);
        mResetButton = findViewById(R.id.reset_button);
        mCheatButton = findViewById(R.id.cheat_button);


        mQuestionTextView = findViewById(R.id.question_text);
        mScoreTextView = findViewById(R.id.score_text);
        mCheatCountTextView = findViewById(R.id.cheat_count_text);
    }

    private void registerListeners(){
        mTrueButton.setOnClickListener((v) -> {
            checkAnswer(true);
            actualizeDisplay();
        });

        mFalsebButton.setOnClickListener((v) -> {
            checkAnswer(false);
            actualizeDisplay();
        });

        mNextButton.setOnClickListener((v) -> {
            mQuiz.nextQuestion();
            actualizeDisplay();
        });

        mPreviousButton.setOnClickListener((v) -> {
            mQuiz.previousQuestion();
            actualizeDisplay();
        });

        mResetButton.setOnClickListener((v) -> {
            mQuiz = new Quiz();
            actualizeDisplay();
        });

        mCheatButton.setOnClickListener((v) -> {
            Intent intent = new Intent(this, CheatActivity.class);
            intent.putExtra(CheatActivity.ANSWER_EXTRA, mQuiz.getAnswer());
            mGetContent.launch(intent);
        });
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuiz.checkAnswer(userPressedTrue);
        int messageResId = 0;
        if (answerIsTrue) {
            messageResId = R.string.good_answer;
        } else {
            messageResId = R.string.wrong_answer;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private void actualizeDisplay() {
        mScoreTextView.setText(getString(R.string.score, mQuiz.getScore(), mQuiz.getQuestionCount()));
        mCheatCountTextView.setText(getString(R.string.cheat_count, mQuiz.getCheatCount(), mQuiz.getQuestionCount()));
        mQuestionTextView.setText(mQuiz.getQuestionText());
    }
}