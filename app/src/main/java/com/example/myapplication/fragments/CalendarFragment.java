package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.myapplication.AddNoteActivity;
import com.example.myapplication.MyEventDay;
import com.example.myapplication.NotePreviewActivity;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static android.app.Activity.RESULT_OK;

public class CalendarFragment extends Fragment {

    public static final String TAG = "CalendarFragment";
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    public static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    private ArrayList<EventDay> mEventDays = new ArrayList<>();

    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();

    private FloatingActionButton fab;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_view2, container, false);

        fab = view.findViewById(R.id.fab);
        mCalendarView = view.findViewById(R.id.calendarView);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        //RESET SHAREDPREFERENCES
        //prefs.edit().clear().commit();

        Set<String> dateSet = prefs.getStringSet("dates", new HashSet<String>());
        dates.addAll(dateSet);

        //Log.e("Event", dates.toString());
        //Log.e("Event", notes.toString());
        Date currentDate = Calendar.getInstance().getTime();
        //Log.e("Event", currentDate.toString());


        //parses the date and notes
        for (int i = 0; i < dates.size(); i++) {
            Calendar calendar = Calendar.getInstance();

            String date = dates.get(i);
            String[] values = date.split(" ");
            int day = Integer.parseInt(values[0]);
            int month = Integer.parseInt(values[1]) - 1;
            int year = Integer.parseInt(values[2]);
            String note = values[3];
            Log.e("test", " " + day + " " + month + " " + year + " "+ note);

            calendar.clone();
            calendar.set(year, month, day);
            mCalendarView.setDate(calendar);

            mEventDays.add(new MyEventDay(calendar, R.drawable.ic_message_black_48dp, note));
            mCalendarView.setEvents(mEventDays);
        }

        Log.e("Event", dates.toString());
        mCalendarView.setDate(currentDate);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                //NOTE: formatting - Toast.makeText(getContext(), ((eventDay.getCalendar().get(Calendar.MONTH))+"."+(eventDay.getCalendar().get(Calendar.DATE))+"."+(eventDay.getCalendar().get(Calendar.YEAR))),Toast.LENGTH_LONG).show();
                previewNote(eventDay);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences prefs = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);

            String note = myEventDay.getNote();

            //puts the date list into a hashset so u can retrieve it in the sharedpreferences
            Set<String> dateSet = new HashSet<>();
            String formattedDate = getFormattedDate(myEventDay.getCalendar().getTime());
            dates.add(formattedDate + " " + note);
            dateSet.addAll(dates);

            SharedPreferences.Editor edit = prefs.edit();
            edit.putStringSet("dates", dateSet);
            edit.commit();
        }
    }

    private void addNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(getActivity(), NotePreviewActivity.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay) eventDay);
        }
        startActivity(intent);
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

}
