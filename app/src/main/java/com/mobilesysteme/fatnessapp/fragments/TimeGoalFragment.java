package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.SharedPreferenceUtils;

import java.util.Date;

public class TimeGoalFragment extends Fragment {

    private EditText timeGoalNumber;
    private final OnFirstLaunchStepFinished finishListener;

    public TimeGoalFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timegoal, container, false);
        Button confirmButton = view.findViewById(R.id.btn_confirmTimeGoal);
        timeGoalNumber = view.findViewById(R.id.edit_timeGoalNumber);
        confirmButton.setOnClickListener(v -> {
            Date date = DateUtils.getDateFromString(String.valueOf(timeGoalNumber.getText()));
            SharedPreferenceUtils.saveUserDeadline(TimeGoalFragment.this.getContext(), date);
            finishListener.onStepFinished();
        });
        return view;
    }
}
