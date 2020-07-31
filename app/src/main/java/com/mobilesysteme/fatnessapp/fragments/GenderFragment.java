package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.UserAttributeHandler;

/**
 * @author Maximilian Grabau
 */
public class GenderFragment extends Fragment {

    private OnFirstLaunchStepFinished finishListener;

    public GenderFragment() {
    }

    public GenderFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.rdgrp_gender);

        // On-Confirm operation
        view.findViewById(R.id.btn_confirmGender).setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();
            if(selectedId >= 0) {

                Gender gender = null;
                if (selectedId == R.id.radio_male) {

                    gender = Gender.MALE;
                } else if (selectedId == R.id.radio_female) {

                    gender = Gender.FEMALE;
                }

                if (new UserAttributeHandler(getContext()).handleSaveGender(gender)) {
                    finishListener.onStepFinished();
                }
            } else {

                Toast.makeText(getContext(), R.string.error_gender, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
