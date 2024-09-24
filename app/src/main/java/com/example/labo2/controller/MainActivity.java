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
import com.example.labo2.model.Question;
import com.example.labo2.model.Quiz;
import com.example.labo2.view.MainView;
import com.example.labo2.view.View;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainView.Listener {

    private MainView mView;

    private int mQuizId;

    private Map<Integer, Quiz> mQuizzes;

    public MainActivity() {
        mQuizzes = new HashMap<>();

        //Geography
        mQuizzes.put(R.id.geography_radio, new Quiz(new Question[]{
                new Question(R.string.question_australia, true),
                new Question(R.string.question_oceans, true),
                new Question(R.string.question_mideast, false),
                new Question(R.string.question_africa, false),
                new Question(R.string.question_everest, true),
                new Question(R.string.question_asia, true),
        }));

        mQuizzes.put(R.id.history_radio, new Quiz(new Question[]{
                new Question(R.string.question_revolution_francaise, true),
                new Question(R.string.question_guerre_froide, true),
                new Question(R.string.question_rome, false),
                new Question(R.string.question_deuxieme_guerre, false),
                new Question(R.string.question_napoleon, true),
                new Question(R.string.question_renaissance, false),
        }));
        mQuizzes.put(R.id.geek_radio, new Quiz(new Question[]{
                new Question(R.string.question_star_wars, true),
                new Question(R.string.question_superman, false),
                new Question(R.string.question_marvel, false),
                new Question(R.string.question_harry_potter, true),
                new Question(R.string.question_pokemon, true),
                new Question(R.string.question_lotr, true),
        }));
    }

    private Quiz mQuiz;

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

        mQuizId = getIntent().getIntExtra("quizId", 0);
        mQuiz = mQuizzes.get(mQuizId);

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
        mView.disableAnswerButtons();

        if(mQuiz.isQuizFinished()){
            mView.disableNavigationButtons();
            mView.disableCheatButton();
        }

        mView.refresh();
    }

    @Override
    public void changeQuestion(int direction) {
        mQuiz.changeQuestion(direction);
        if(!mQuiz.hasAnswered()){
            mView.enableAnswerButtons();
        }
        else{
            mView.disableAnswerButtons();
        }
        mView.refresh();
    }

    @Override
    public void resetQuiz() {
        mQuiz = mQuizzes.get(mQuizId);
        mView.enableAnswerButtons();
        mView.enableCheatButton();
        mView.enableNavigationButtons();
        mView.refresh();
    }

    @Override
    public void cheat() {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.ANSWER_EXTRA, mQuiz.getAnswer());
        mGetContent.launch(intent);
    }
}