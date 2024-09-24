package com.example.labo2.view;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.labo2.R;

public abstract class View<T> {
    protected T mListener;
    protected AppCompatActivity mActivity;

    public View(AppCompatActivity activity) {
        mActivity = activity;
        EdgeToEdge.enable(activity);
        activity.setContentView(getLayout());

        ViewCompat.setOnApplyWindowInsetsListener(activity.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setListener(T listener) {
        mListener = listener;
        refresh();
    }

    public abstract void refresh();

    protected abstract int getLayout();
}
