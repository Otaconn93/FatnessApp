package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;

public class CurrentWeightFragment extends Fragment {

    private EditText currentWeightNumber;
    private final OnFirstLaunchStepFinished finishListener;

    public CurrentWeightFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currentweight, container, false);
        Button confirmButton = view.findViewById(R.id.btn_confirmCurrentWeight);
        currentWeightNumber = view.findViewById(R.id.edit_currentWeightNumber);

        // On-Confirm operation
        confirmButton.setOnClickListener(v -> {
            int weight = Integer.parseInt(String.valueOf(currentWeightNumber.getText()));
            SharedPreferenceUtils.saveUserWeightNow(CurrentWeightFragment.this.getContext(), weight);
            finishListener.onStepFinished();
        });
        return view;
    }
}
