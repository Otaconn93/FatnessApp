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

public class HeightFragment extends Fragment {

    private EditText heightNumber;
    private final OnFirstLaunchStepFinished finishListener;

    public HeightFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_height, container, false);
        Button confirmButton = view.findViewById(R.id.btn_confirmHeight);
        heightNumber = view.findViewById(R.id.edit_heightNumber);
        confirmButton.setOnClickListener(v -> {
            int height = Integer.parseInt(String.valueOf(heightNumber.getText()));
            SharedPreferenceUtils.saveUserHeight(HeightFragment.this.getContext(), height);
            finishListener.onStepFinished();
        });
        return view;
    }
}