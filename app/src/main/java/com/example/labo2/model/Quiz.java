package com.example.labo2.model;

import com.example.labo2.R;

import java.io.Serializable;

public class Quiz implements Serializable {

    private int mCurrentIndex = 0;

    private Question[] mQuestionBank;

    public Quiz(Question[] questions) {
        mQuestionBank = questions;
    }

    public void changeQuestion(int direction)
    {
        mCurrentIndex = (mCurrentIndex + direction + mQuestionBank.length) % mQuestionBank.length;
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

    public boolean hasAnswered() {
        return mQuestionBank[mCurrentIndex].hasAnswered();
    }

    public boolean isQuizFinished() {
        for (Question question : mQuestionBank) {
            if (!question.hasAnswered()) {
                return false;
            }
        }

        return true;
    }

    public void setCheated(boolean cheated) {
        mQuestionBank[mCurrentIndex].setCheated(cheated);
    }
}
