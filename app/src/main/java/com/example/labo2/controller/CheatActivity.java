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

public class CheatActivity extends AppCompatActivity {

    public static final String ANSWER_EXTRA = "com.example.labo2.answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.labo2.answer_shown";

    private boolean mAnswerIsTrue;

    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_EXTRA, false);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(v -> {
            String answerResult = getString(mAnswerIsTrue ? R.string.true_button : R.string.false_button);
            mShowAnswerButton.setText(getString(R.string.cheat_reveal_text, answerResult));
            mShowAnswerButton.setEnabled(false);

            Intent data = new Intent();
            data.putExtra(EXTRA_ANSWER_SHOWN, true);
            this.setResult(RESULT_OK, data);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}