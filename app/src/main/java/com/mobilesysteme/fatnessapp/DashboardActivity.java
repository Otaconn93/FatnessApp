package com.mobilesysteme.fatnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class DashboardActivity extends AppCompatActivity {
    private LineChart lineChart;
    private LineData lineData;
    private LineDataSet lineDataSet;
    private ArrayList lineEntries;
    private static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        lineChart = findViewById(R.id.lineChart);
        getEntries();
        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
        init();
        setTitle("Fatness-App");
    }

    private void init() {
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });



        Toolbar toolbar = findViewById(R.id.dashToolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(getApplicationContext());
         databaseHelper.refillDatabase();

    }

    private void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    private void openSettings() {
        Intent intent = new Intent(this, MySettingsActivity.class);
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

}

