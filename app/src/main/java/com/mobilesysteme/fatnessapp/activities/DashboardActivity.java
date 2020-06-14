package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.CalorieCalculator;
import com.mobilesysteme.fatnessapp.DatabaseContentHelperUtils;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.SettingsActivity;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;
import java.util.ArrayList;


public class DashboardActivity extends AppCompatActivity {
    private LineChart lineChart;
    private LineData lineData;
    private LineDataSet lineDataSet;
    private TextView dailyCalories;
    private ArrayList lineEntries;
    private ProgressBar progressBar;
    private static DatabaseHelper databaseHelper;
    private CalorieCalculator calorieCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dailyCalories = findViewById(R.id.tv_dailyCalories);
        progressBar = findViewById(R.id.progressBar);

        calorieCalculator = new CalorieCalculator(this);
        dailyCalories.setText(String.valueOf(calorieCalculator.getDailyCaloriesLeft()));
        int progress = (int) (((float)(calorieCalculator.getDailyCalories()-calorieCalculator.getDailyCaloriesLeft())/calorieCalculator.getDailyCalories()) * 100);
        progressBar.setProgress(progress);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        if(SharedPreferenceUtils.getFirstLaunch(this)) {
            Intent intent = new Intent(this, FirstLaunchActivity.class);
            startActivity(intent);
            DatabaseContentHelperUtils.fillDatabase(databaseHelper);
            finish();
        } else {
            init();
        }
    }

    private void init() {
        createLinechart();
        setTitle("Fatness-App");
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(v -> openAddActivity());
        Toolbar toolbar = findViewById(R.id.dashToolbar);
        setSupportActionBar(toolbar);
    }

    private void createLinechart() {
        lineChart = findViewById(R.id.lineChart);
        getEntries();
        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
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

    private void getEntries() {
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(2f, 0));
        lineEntries.add(new Entry(4f, 1));
        lineEntries.add(new Entry(6f, 1));
        lineEntries.add(new Entry(8f, 3));
        lineEntries.add(new Entry(7f, 4));
        lineEntries.add(new Entry(3f, 3));
    }

    @Override
    public void onResume() {
        super.onResume();
        dailyCalories.setText(String.valueOf(calorieCalculator.getDailyCaloriesLeft()));
        int progress = (int) (((float)(calorieCalculator.getDailyCalories()-calorieCalculator.getDailyCaloriesLeft())/calorieCalculator.getDailyCalories()) * 100);
        progressBar.setProgress(progress);
    }

}

