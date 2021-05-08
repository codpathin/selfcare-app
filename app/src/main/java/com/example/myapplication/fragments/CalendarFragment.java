package com.example.myapplication.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.myapplication.AddNoteActivity;
import com.example.myapplication.MyEventDay;
import com.example.myapplication.NotePreviewActivity;
import com.example.myapplication.R;
import com.example.myapplication.TinyDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class CalendarFragment extends Fragment {

    public static final String TAG = "CalendarFragment";
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    public static final String DATE = "date";
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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        fab = view.findViewById(R.id.fab);
        mCalendarView = view.findViewById(R.id.calendarView);

        addEvents();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            try {
                mCalendarView.setDate(myEventDay.getCalendar());
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);

            String note = myEventDay.getNote();

            TinyDB tinydb = new TinyDB(getContext());

            dates = tinydb.getListString("dates");
            notes = tinydb.getListString("notes");

            String formattedDate = getFormattedDate(myEventDay.getCalendar().getTime());

            if (dates.contains(formattedDate)) {
                int index = dates.indexOf(formattedDate);
                notes.set(index, note);
            }
            else {
                notes.add(note);
                dates.add(formattedDate);
            }

            tinydb.putListString("dates", dates);
            tinydb.putListString("notes", notes);

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
        else {
            intent.putExtra(DATE, getFormattedDateWord(eventDay.getCalendar().getTime()));
        }
        startActivity(intent);
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getFormattedDateWord(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public void addEvents() {
        TinyDB tinydb = new TinyDB(getContext());
        //tinydb.remove("dates");
        //tinydb.remove("notes");
        dates = tinydb.getListString("dates");
        notes = tinydb.getListString("notes");

        Date currentDate = Calendar.getInstance().getTime();

        for (int i = 0; i < dates.size(); i++) {
            Calendar calendar = Calendar.getInstance();

            String date = dates.get(i);
            String[] values = date.split(" ");
            int day = Integer.parseInt(values[0]);
            int month = Integer.parseInt(values[1]) - 1;
            int year = Integer.parseInt(values[2]);
            String note = notes.get(i);

            calendar.clone();
            calendar.set(year, month, day);
            try {
                mCalendarView.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }

            mEventDays.add(new MyEventDay(calendar, R.drawable.ic_message_black_48dp, note, Color.parseColor("#000000")));
            mCalendarView.setEvents(mEventDays);
        }

        Log.e("error", dates.toString());

        try {
            mCalendarView.setDate(currentDate);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }

}
