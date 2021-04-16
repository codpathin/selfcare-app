package com.example.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class CircleDecorator implements DayViewDecorator {

    private HashSet<CalendarDay> dates;
    private Drawable drawable;

    public CircleDecorator(Activity context, Collection<CalendarDay> dates, int id) {
        //drawable = ContextCompat.getDrawable(context, R.drawable.highlighteddate);
        drawable = ContextCompat.getDrawable(context, id);
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
