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

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.SharedPreferenceUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGoalFragment extends Fragment {

    Button confirmButton;
    EditText timeGoalNumber;
    OnFirstLaunchStepFinished finishListener;

    public TimeGoalFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timegoal, container, false);
        confirmButton = view.findViewById(R.id.btn_confirmTimeGoal);
        timeGoalNumber = view.findViewById(R.id.edit_timeGoalNumber);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Date date = sdf.parse(String.valueOf(timeGoalNumber.getText()));
                    SharedPreferenceUtils.saveUserDeadline(TimeGoalFragment.this.getContext(), date);
                } catch (ParseException e) {
                    // TODO Handling für falsches Format
                    e.printStackTrace();
                }
                try {
                    finishListener.onStepFinished();
                } catch (java.lang.InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
