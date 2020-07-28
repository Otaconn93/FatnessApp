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

public class GenderFragment extends Fragment {

    private final OnFirstLaunchStepFinished finishListener;

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

                int genderId = -1;
                if (selectedId == R.id.radio_male) {

                    genderId = Gender.MALE.getId();
                } else if (selectedId == R.id.radio_female) {

                    genderId = Gender.FEMALE.getId();
                }

                new UserAttributeHandler(getContext()).handleSaveGender(genderId);
                finishListener.onStepFinished();

            } else {

                Toast.makeText(getContext(), R.string.error_gender, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
