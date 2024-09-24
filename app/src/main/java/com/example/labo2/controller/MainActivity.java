package com.example.labo2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;
import com.example.labo2.model.Quiz;
import com.example.labo2.view.MainView;
import com.example.labo2.view.View;

public class MainActivity extends AppCompatActivity implements MainView.Listener {

    private MainView mView;

    private Quiz mQuiz = new Quiz();

    private ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                mView.showMessage(getString(R.string.answer_has_been_shown));
                mQuiz.setCheated(true);
                mView.refresh();
            }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new MainView(this);
        mView.setListener(this);

        if(savedInstanceState != null){
            mQuiz = (Quiz) savedInstanceState.getSerializable("quiz");
            mView.refresh();
        }
        Log.d("MainApp", "onCreate method");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("quiz", mQuiz);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuiz.checkAnswer(userPressedTrue);
        int messageResId;
        if (answerIsTrue) {
            messageResId = R.string.good_answer;
        } else {
            messageResId = R.string.wrong_answer;
        }
        mView.showMessage(getString(messageResId));
    }


    @Override
    public int getQuestionCount() {
        return mQuiz.getQuestionCount();
    }

    @Override
    public int getScore() {
        return mQuiz.getScore();
    }

    @Override
    public int getCheatCount() {
        return mQuiz.getCheatCount();
    }

    @Override
    public int getQuestionText() {
        return mQuiz.getQuestionText();
    }

    @Override
    public void setResponse(boolean response) {
        checkAnswer(response);
        mView.refresh();
    }

    @Override
    public void changeQuestion(int direction) {
        mQuiz.changeQuestion(direction);
        mView.refresh();
    }

    @Override
    public void resetQuiz() {
        mQuiz = new Quiz();
        mView.refresh();
    }

    @Override
    public void cheat() {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.ANSWER_EXTRA, mQuiz.getAnswer());
        mGetContent.launch(intent);
    }
}