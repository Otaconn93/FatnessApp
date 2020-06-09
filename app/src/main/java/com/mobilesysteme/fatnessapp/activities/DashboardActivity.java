package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.SharedPreferenceUtils;
import com.mobilesysteme.fatnessapp.preferences.SettingsActivity;

public class DashboardActivity extends AppCompatActivity {

    private static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        if(SharedPreferenceUtils.getFirstLaunch(this)) {
            Intent intent = new Intent(this, FirstLaunchActivity.class);
            startActivity(intent);
            //DatabaseContentHelperUtils.fillDatabase(databaseHelper);
            finish();
        } else {
            init();
        }
    }

    private void init() {
        setTitle("Fatness-App");
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.dashToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.open_settings:
                startActivity(new Intent(this.getApplicationContext(), SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

}
