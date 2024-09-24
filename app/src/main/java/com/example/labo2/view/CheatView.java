package com.example.labo2.view;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;

public class CheatView extends View<CheatView.Listener>{
    private Button mShowAnswerButton;

    public CheatView(AppCompatActivity activity) {
        super(activity);

        mShowAnswerButton = mActivity.findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(v -> {
            mListener.onShowAnswer();
        });
    }

    @Override
    public void refresh() {}

    @Override
    protected int getLayout() {
        Log.d("Main", "getLayout method");
        return R.layout.activity_cheat;
    }

    public void setAnswer(boolean answerIsTrue) {
        String answerResult = mActivity.getString(answerIsTrue ? R.string.true_button : R.string.false_button);
        mShowAnswerButton.setText(mActivity.getString(R.string.cheat_reveal_text, answerResult));
        mShowAnswerButton.setEnabled(false);
    }

    public interface Listener {

        void onShowAnswer();
    }
}
