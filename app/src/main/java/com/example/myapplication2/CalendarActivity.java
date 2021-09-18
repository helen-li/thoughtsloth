package com.example.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
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

        emotionToColors = new HashMap<>();
        emotionToColors.put("Happy", Color.YELLOW);
        emotionToColors.put("Sad", Color.BLUE);
        emotionToColors.put("Angry", Color.RED);
        emotionToColors.put("Fear", Color.rgb(102, 0, 153)); // purple
        emotionToColors.put("Surprise", Color.YELLOW);
        emotionToColors.put("Disgust", Color.rgb(255, 102,0)); // orange

        TextView textView = findViewById(R.id.textView);
        // CalendarDay is 0 indexed for months
        textView.setText(CalendarDay.today().getYear() + " " + CalendarDay.today().getMonth() + " " + CalendarDay.today().getDay());

        calendarView = findViewById(R.id.calendarView);
        int[] threeColors = {
                Color.rgb(0, 0, 255),
                Color.rgb(0, 255, 0),
                Color.rgb(255, 0, 0)};

        List<CalendarDay> threeEventDays = new ArrayList<>();
        threeEventDays.add(CalendarDay.today());
        threeEventDays.add(CalendarDay.from(2021, 9, 11));
        threeEventDays.add( CalendarDay.from(2021, 9,10));

        List<CalendarDay> fourEventDays = new ArrayList<>();
        threeEventDays.add(CalendarDay.from(2021, 8, 9));
        threeEventDays.add(CalendarDay.from(2021, 8, 11));
        threeEventDays.add( CalendarDay.from(2021, 8,10));
        calendarView.addDecorator(new Decorator(threeEventDays,threeColors));
    }

}

