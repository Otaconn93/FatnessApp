package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.SharedPreferenceUtils;

import java.lang.reflect.InvocationTargetException;

public class GenderFragment extends Fragment {

    private Button confirmButton;
    private RadioGroup radioGroup;
    private OnFirstLaunchStepFinished finishListener;

    public GenderFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        confirmButton = view.findViewById(R.id.btn_confirmGender);
        radioGroup = view.findViewById(R.id.rdgrp_gender);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radio_male) {
                    SharedPreferenceUtils.saveUserGender(GenderFragment.this.getContext(), Gender.MALE);
                }else if(selectedId == R.id.radio_female) {
                    SharedPreferenceUtils.saveUserGender(GenderFragment.this.getContext(), Gender.FEMALE);
                }
                try {
                    finishListener.onStepFinished();
                } catch (java.lang.InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
