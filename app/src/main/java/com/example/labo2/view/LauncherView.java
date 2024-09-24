package com.example.labo2.view;

import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labo2.R;

public class LauncherView extends View<LauncherView.Listener> {
    private RadioGroup mRadioGroup;

    private Button mLaunchButton;

    private int selectedQuiz;

    public LauncherView(AppCompatActivity activity) {
        super(activity);

        mRadioGroup = activity.findViewById(R.id.radio_group);
        mLaunchButton = activity.findViewById(R.id.launch_button);

        selectedQuiz = mRadioGroup.getCheckedRadioButtonId();

        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            selectedQuiz = checkedId;
        });

        mLaunchButton.setOnClickListener((v) -> {
            mListener.onLaunchQuiz(selectedQuiz);
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launcher;
    }

    public interface Listener {
        void onLaunchQuiz(int quizId);
    }
}
