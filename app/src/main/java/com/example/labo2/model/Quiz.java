package com.example.labo2.model;

import com.example.labo2.R;

import java.io.Serializable;

public class Quiz implements Serializable {

    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
    };

    private void changeIndex(int direction)
    {
        mCurrentIndex = (mCurrentIndex + direction + mQuestionBank.length) % mQuestionBank.length;
    }

    public void nextQuestion() {
        changeIndex(1);
    }

    public void previousQuestion() {
        changeIndex(-1);
    }


    public int getQuestionText() {
        return mQuestionBank[mCurrentIndex].getTextResId();
    }

    public boolean checkAnswer(boolean response) {
        return mQuestionBank[mCurrentIndex].checkAnswer(response);
    }

    public int getScore() {
        int score = 0;

        for (Question question : mQuestionBank) {
            if (question.hasAnsweredCorrectly() != null && question.hasAnsweredCorrectly()) {
                score++;
            }
        }

        return score;
    }

    public int getCheatCount() {
        int cheatCount = 0;

        for (Question question : mQuestionBank) {
            if (question.hasCheated()) {
                cheatCount++;
            }
        }

        return cheatCount;
    }

    public int getQuestionCount() {
        return mQuestionBank.length;
    }

    public boolean getAnswer() {
        return mQuestionBank[mCurrentIndex].getAnswerTrue();
    }

    public void setCheated(boolean cheated) {
        mQuestionBank[mCurrentIndex].setCheated(cheated);
    }
}
