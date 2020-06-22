package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.UserAttributeHandler;

public class TimeGoalFragment extends Fragment {

    private final OnFirstLaunchStepFinished finishListener;

    public TimeGoalFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timegoal, container, false);

        DatePicker datePicker = view.findViewById(R.id.edit_datePicker);
        datePicker.setMinDate(System.currentTimeMillis() + DateUtils.DAY_IN_MILLI_SECS);

        view.findViewById(R.id.btn_confirmTimeGoal).setOnClickListener(v -> {

            if (new UserAttributeHandler(getContext()).handleSaveDeadline(DateUtils.getDateFromDatePicker(datePicker))) {
                finishListener.onStepFinished();
            }
        });

        return view;
    }
}
