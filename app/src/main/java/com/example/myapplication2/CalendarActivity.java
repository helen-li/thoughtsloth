package com.example.myapplication2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private Map<String, Integer> emotionToColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ExtendedFloatingActionButton addEntry = (ExtendedFloatingActionButton) findViewById(R.id.addEntry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Input.class);
                startActivity(startIntent);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Graph.class);
                startActivity(startIntent);
            }
        });

        emotionToColors = new HashMap<>();
        emotionToColors.put("Happy", Color.YELLOW);
        emotionToColors.put("Sad", Color.BLUE);
        emotionToColors.put("Angry", Color.RED);
        emotionToColors.put("Fear", Color.rgb(102, 0, 153)); // purple
        emotionToColors.put("Surprise", Color.rgb(255, 102,0)); // orange
        emotionToColors.put("Disgust", Color.GREEN);

        calendarView = findViewById(R.id.calendarView);
        int[] threeColors = {
                Color.rgb(0, 0, 255),
                Color.rgb(0, 255, 0),
                Color.rgb(255, 0, 0)};

        List<CalendarDay> tenEventDays = new ArrayList<>();
        for(int i = 10; i < 20; i++)
            tenEventDays.add(CalendarDay.from(2021,8, i));

        Map<String, Integer> emotions = (Map<String, Integer>) getIntent().getSerializableExtra("hashMap");
        List<List<String>> allEmotions = new ArrayList<>();
        allEmotions.add(new ArrayList<>(Arrays.asList("Happy", "Surprise"))); // 9.10 (Yellow, Orange)
        allEmotions.add(new ArrayList<>(Arrays.asList("Fear"))); // 9.11 (Purple)
        allEmotions.add(new ArrayList<>(Arrays.asList("Fear"))); // 9.12 (Purple)
        allEmotions.add(new ArrayList<>(Arrays.asList("Happy"))); // 9.13 (Yellow)
        allEmotions.add(new ArrayList<>(Arrays.asList("Angry", "Disgust"))); // 9.14 (Red, Green)
        allEmotions.add(new ArrayList<>(Arrays.asList("Angry", "Surprise"))); // 9.15 (Orange, Red)
        allEmotions.add(new ArrayList<>(Arrays.asList("Happy", "Surprise"))); // 9.16 (Orange, Yellow)
        allEmotions.add(new ArrayList<>(Arrays.asList("Fear"))); // 9.17 (Purple)
        allEmotions.add(new ArrayList<>(Arrays.asList("Happy", "Surprise"))); // 9.18 (Orange, Yellow)
        String[] typeEmotions = new String[]{"Happy", "Sad", "Angry", "Fear", "Surprise", "Disgust"};

        List<String> temp = new ArrayList<>();
        if(emotions != null) {
            for(int i = 0; i < typeEmotions.length; i++) {
                if(emotions.get(typeEmotions[i]) == 1)
                    temp.add(typeEmotions[i]);
            }
        }
        allEmotions.add(temp);

        for(int i = 0; i < tenEventDays.size(); i++) {
            CalendarDay currDay = tenEventDays.get(i);
            List<String> currEmotions = allEmotions.get(i);
            int[] currColors = new int[currEmotions.size()];
            for(int j = 0; j < currEmotions.size(); j++)
                currColors[j] = emotionToColors.get(currEmotions.get(j));

            calendarView.addDecorator(new Decorator(currDay, currColors));
        }

        List<CalendarDay> threeEventDays = new ArrayList<>();
        threeEventDays.add(CalendarDay.today());
        threeEventDays.add(CalendarDay.from(2021, 9, 11));
        threeEventDays.add( CalendarDay.from(2021, 9,10));

        List<CalendarDay> fourEventDays = new ArrayList<>();
        threeEventDays.add(CalendarDay.from(2021, 8, 9));
        threeEventDays.add(CalendarDay.from(2021, 8, 11));
        threeEventDays.add( CalendarDay.from(2021, 8,10));

    }

}

