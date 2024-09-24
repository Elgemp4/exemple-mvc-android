package com.example.labo2.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mHasCheated;

    private Boolean mAnsweredCorrectly = null;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean checkAnswer(boolean response) {
        if(mAnsweredCorrectly == null){
            mAnsweredCorrectly = response == mAnswerTrue;
        }

        return response == mAnswerTrue;
    }

    public Boolean hasAnsweredCorrectly() {
        return mAnsweredCorrectly;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean getAnswerTrue() {
        return mAnswerTrue;
    }

    public void setCheated(boolean cheated) {
        mHasCheated = cheated;
    }

    public boolean hasCheated() {
        return mHasCheated;
    }

    public boolean hasAnswered() {
        return mAnsweredCorrectly != null;
    }
}
