package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.UserAttributeHandler;

public class CurrentWeightFragment extends Fragment {

    private final OnFirstLaunchStepFinished finishListener;

    public CurrentWeightFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_currentweight, container, false);
        EditText currentWeightNumber = view.findViewById(R.id.edit_currentWeightNumber);

        // On-Confirm operation
        view.findViewById(R.id.btn_confirmCurrentWeight).setOnClickListener(v -> {

            new UserAttributeHandler(getContext()).handleSaveWeight(String.valueOf(currentWeightNumber.getText()));
            finishListener.onStepFinished();
        });

        return view;
    }
}
