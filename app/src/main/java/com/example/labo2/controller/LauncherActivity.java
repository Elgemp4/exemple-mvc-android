package com.example.labo2.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.labo2.R;
import com.example.labo2.view.LauncherView;

public class LauncherActivity extends AppCompatActivity implements LauncherView.Listener {

    private LauncherView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new LauncherView(this);
        mView.setListener(this);
    }

    @Override
    public void onLaunchQuiz(int quizId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("quizId", quizId);
        startActivity(intent);
    }
}