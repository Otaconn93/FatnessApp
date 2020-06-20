package com.mobilesysteme.fatnessapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.CalorieCalculator;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.SettingsActivity;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;


public class DashboardActivity extends AppCompatActivity {
    private TextView dailyCalories;
    private List<Entry> weigtEntries;
    private List<String> weightDateEntries;
    private ProgressBar progressBar;
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

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        if(SharedPreferenceUtils.getFirstLaunch(this)) {

            Intent intent = new Intent(this, FirstLaunchActivity.class);
            startActivity(intent);
            databaseHelper.refillDatabase();
            finish();
        } else {

            init();
        }

        if(checkLastWeightDateOutdated()){
            createDialogForWeightUpdate();
        }
    }

    private void init() {

        setTitle("Healthy Fatness");

        Toolbar toolbar = findViewById(R.id.dashToolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));

        initLineChart();
    }

    /**
     * Creates line chart to show last user weight inputs
     */
    private void initLineChart() {
        weigtEntries = new ArrayList<>();
        weightDateEntries = new ArrayList<>();
        LineChart lineChart = findViewById(R.id.lineChart);
        fillLineChartList();
        LineDataSet lineDataSet = new LineDataSet(weigtEntries, "");
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setEnabled(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weightDateEntries));
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
        lineChart.setData(lineData);
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

    /**
     * Put user weight entries into line chart lists
     */
    private void fillLineChartList() {
        TreeMap userWeightEntries = SharedPreferenceUtils.getUserWeightHistory(this);
        Set<Map.Entry<Long, Integer>> userEntriesSet =  userWeightEntries.entrySet();
        int i = 0;
        for(Map.Entry<Long, Integer> history : userEntriesSet){
            weigtEntries.add(new Entry(i, history.getValue().intValue()));
            Date date = new Date(history.getKey());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
            weightDateEntries.add(sdf.format(date));
            i++;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        calorieCalculator = new CalorieCalculator(this);
        int caloriesLeft = calorieCalculator.getDailyCaloriesLeft();
        if(caloriesLeft >= 0){
            dailyCalories.setText(String.valueOf(caloriesLeft));
            int progress = (int) (((float)(calorieCalculator.getDailyCalories()-calorieCalculator.getDailyCaloriesLeft())/calorieCalculator.getDailyCalories()) * 100);
            progressBar.setProgress(progress);
        }else{
            dailyCalories.setText("0");
            progressBar.setProgress(100);
        }

        initLineChart();
    }

    /**
     * @return true if last weight is older than one week
     */
    private boolean checkLastWeightDateOutdated(){
        Date lastEntry = new Date();
        Date today = new Date();
        try {
            lastEntry = new SimpleDateFormat("dd.MM.yyyy").parse(weightDateEntries.get(weightDateEntries.size()-1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(lastEntry.getTime() < today.getTime()- 604800000){
            return true;
        }
        return false;
    }

    /**
     * Creates a dialog to update the current weight in settings
     * @return dialog window with one button
     */
    private void createDialogForWeightUpdate() {

        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    startActivity(new Intent(this.getApplicationContext(), SettingsActivity.class));
                    finish();
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deine letzte Gewichtsmessung liegt schon eine Woche zurück! Update jetzt dein Gewicht!").setPositiveButton("Zu den Settings", dialogClickListener);
        builder.create();
        builder.show();
    }

}

