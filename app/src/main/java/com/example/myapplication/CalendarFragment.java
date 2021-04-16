package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.HashSet;

import static android.content.Context.MODE_PRIVATE;

public class CalendarFragment extends Fragment {

    private static final String TAG = "CalendarFragment";

    MaterialCalendarView calendar;
    //the dates for sad days
    HashSet<CalendarDay> sad = new HashSet<CalendarDay>();
    //the dates for happy dates
    HashSet<CalendarDay> happy = new HashSet<CalendarDay>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFS", MODE_PRIVATE);

        getStars();

        calendar.addDecorators(new CircleDecorator(getActivity(), sad, R.drawable.highlighteddate));
        calendar.addDecorators(new CircleDecorator(getActivity(), happy, R.drawable.happydate));

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_view, container, false);

        return view;
    }

    public void getStars() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFS", MODE_PRIVATE);

        int day = preferences.getInt("day", 1);
        int month = preferences.getInt("month", 1);
        int year = preferences.getInt("year", 1);

        calendar = getView().findViewById(R.id.calendarView);

        CalendarDay a = CalendarDay.from(year, month, day);

        String rating = preferences.getString("rating", "defValue");

        //Toast.makeText(getActivity(), rating, Toast.LENGTH_SHORT).show();

        if (rating.equals("1.0")) {
            Toast.makeText(getActivity(), "in if statement", Toast.LENGTH_SHORT).show();
            //make sure to make the first launch day is resetted
            //make sure to save the hash in the savedpreferences.
            sad.add(a);
            //calendar.addDecorators(new CircleDecorator(getActivity(), sad, R.drawable.highlighteddate));
            //return R.drawable.highlighteddate;
        }
        else if (rating.equals("5.0")) {
            Toast.makeText(getActivity(), "in if statement", Toast.LENGTH_SHORT).show();
            happy.add(a);
            //return R.drawable.happydate;
        }

        //return R.drawable.highlighteddate;
    }


}
