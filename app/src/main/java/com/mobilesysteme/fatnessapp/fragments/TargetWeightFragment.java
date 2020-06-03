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

public class TargetWeightFragment extends Fragment {

    Button confirmButton;
    EditText targetWeightNumber;
    OnFirstLaunchStepFinished finishListener;

    public TargetWeightFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_targetweight, container, false);
        confirmButton = view.findViewById(R.id.btn_confirmTargetWeight);
        targetWeightNumber = view.findViewById(R.id.edit_targetWeightNumber);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weight = Integer.parseInt(String.valueOf(targetWeightNumber.getText()));
                SharedPreferenceUtils.saveUserTargetWeight(TargetWeightFragment.this.getContext(), weight);
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
