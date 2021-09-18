package com.example.myapplication2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Checkboxes.class);
                startActivity(startIntent);
            }
        });

        Button calButton = findViewById(R.id.calButton);
        calButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(startIntent);
            }
        });

        lineChart = findViewById(R.id.lineChart);

        List<Integer> xAXES = new ArrayList<>();
        List<Entry> yAXES1 = new ArrayList<>();
        List<Entry> yAXES2 = new ArrayList<>();

        int numPoints = 10;
        for(int i = 0; i < numPoints; i++) {
            xAXES.add(i);
            int curr = (int) (Math.random() * 10);
            yAXES1.add(new Entry(curr, i));
            yAXES2.add(new Entry(10-curr, i));
        }

        String[] xaxis = new String[xAXES.size()];
        for(int i = 0; i < xAXES.size(); i++)
            xaxis[i] = xAXES.get(i).toString();

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(yAXES1, "Sad");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yAXES2, "Happy");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(xaxis, lineDataSets));
        lineChart.setVisibleXRangeMaximum(65f);
    }
}