package com.mobilesysteme.fatnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.UserAttributeHandler;

public class AgeFragment extends Fragment {

    private final OnFirstLaunchStepFinished finishListener;

    public AgeFragment(OnFirstLaunchStepFinished myFinishListener) {
        finishListener = myFinishListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_age, container, false);
        EditText ageNumber = view.findViewById(R.id.edit_ageNumber);

        // On-Confirm operation
        view.findViewById(R.id.btn_confirmAge).setOnClickListener(v -> {

            String stringAge = ageNumber.getText().toString();
            if(!stringAge.matches("")) {

                new UserAttributeHandler(getContext()).handleSaveAge(stringAge);
                finishListener.onStepFinished();
            } else {

                Toast.makeText(getContext(), R.string.error_number, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
