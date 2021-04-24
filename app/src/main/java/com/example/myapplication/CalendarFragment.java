package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CalendarFragment extends Fragment {

    public static final String TAG = "CalendarFragment";
    public static final String RESULT = "result";
    public static final String EVENT = "event";
    public static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();
    private FloatingActionButton fab;

    private Context context;
    private MyEventDay savedObject;
    private List<MyEventDay> listEvents = new ArrayList<>();
    private static final String PREF_MY_OBJECT = "pref_my_object";
    //private SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    private Gson gson = new GsonBuilder().create();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_view2, container, false);

        fab = view.findViewById(R.id.fab);
        mCalendarView = view.findViewById(R.id.calendarView);

        getMyObject();
        mEventDays.add(savedObject);

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
            setMyObject(myEventDay);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
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

    //maybe change this to arraylist?
    public MyEventDay getMyObject() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (savedObject == null) {
            String savedValue = prefs.getString(PREF_MY_OBJECT, "");
            if (savedValue.equals("")) {
                savedObject = null;
            } else {
                savedObject = gson.fromJson(savedValue, MyEventDay.class);
            }
        }

        return savedObject;
    }

    public void setMyObject(MyEventDay obj) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (obj == null) {
            prefs.edit().putString(PREF_MY_OBJECT, "").commit();
        } else {
            prefs.edit().putString(PREF_MY_OBJECT, gson.toJson(obj)).commit();
        }
        savedObject = obj;
    }
}
