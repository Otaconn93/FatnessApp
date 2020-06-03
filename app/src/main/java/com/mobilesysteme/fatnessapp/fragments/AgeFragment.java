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

public class AgeFragment extends Fragment {

    private Button confirmButton;
    private EditText ageNumber;
    private OnFirstLaunchStepFinished finishListener;

    public AgeFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);
        confirmButton = view.findViewById(R.id.btn_confirmAge);
        ageNumber = view.findViewById(R.id.edit_ageNumber);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(String.valueOf(ageNumber.getText()));
                SharedPreferenceUtils.saveUserAge(AgeFragment.this.getContext(), age);
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
