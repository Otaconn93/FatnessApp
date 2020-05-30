package com.mobilesysteme.fatnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.dashToolbar);
        setSupportActionBar(toolbar);

        setTitle("Fatness-App");

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });

        LineChartView lineChartView = findViewById(R.id.chart);
        String[] axisData= {"Mon","Die","Mit","Don","Fre","Sam","Son"};

        int[] yAxisData = {50,20,15,30,20,60,15,40,45,10,90,18,14};

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);


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
}
