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

/**
 * @author Maximilian Grabau
 */
public class HeightFragment extends Fragment {

    private OnFirstLaunchStepFinished finishListener;
    private final int actionId = R.id.action_heightFragment_to_currentWeightFragment;

    public HeightFragment() {
        // Silence is golden
    }

    @Override
    public void onStart() {
        super.onStart();
        finishListener = (OnFirstLaunchStepFinished) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_height, container, false);
        EditText heightNumber = view.findViewById(R.id.edit_heightNumber);

        // On-Confirm operation
        view.findViewById(R.id.btn_confirmHeight).setOnClickListener(v -> {

            String stringHeight = heightNumber.getText().toString();
            if(!stringHeight.matches("")) {

                if (new UserAttributeHandler(getContext()).handleSaveHeight(stringHeight)) {
                    finishListener.onStepFinished(actionId);
                }
            } else {

                Toast.makeText(getContext(), R.string.error_number, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
