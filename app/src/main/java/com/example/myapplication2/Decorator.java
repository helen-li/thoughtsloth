package com.example.myapplication2;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class Decorator implements DayViewDecorator {

    private final int[] colors;
    private final HashSet<CalendarDay> dates;


    public Decorator(Collection<CalendarDay> dates, int[] colors) {
        //this.color = color;
        this.dates = new HashSet<>(dates);
        this.colors = colors;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan((new CustmMultipleDotSpan(5, colors)));
    }


}