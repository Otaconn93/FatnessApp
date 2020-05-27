package com.mobilesysteme.fatnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
    }

    private void openAddActivity() {

    }

}
