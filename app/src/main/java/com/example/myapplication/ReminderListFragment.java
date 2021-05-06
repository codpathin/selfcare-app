package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


public class ReminderListFragment extends Fragment {
    List<Reminder> reminders;
    RecyclerView rvReminders;
    ReminderAdapter reminderAdapter;
    Reminder newly;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reminderlist, container, false);
        Log.d("PAIN","Checking if getarguments null:"); //TODO: persist reminders added
        if ((getArguments())!= null) {
            newly = Parcels.unwrap(this.getArguments().getParcelable("reminder"));
        }
        else {
            Log.d("PAIN","was null");
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        rvReminders = view.findViewById(R.id.rvReminders);
        reminders = new ArrayList<>(); //TODO: prevent reminders being replaced
        if (newly !=null) {
            reminders.add(newly);
        }
        reminderAdapter = new ReminderAdapter(view.getContext(), reminders);
        rvReminders.setAdapter(reminderAdapter);
        rvReminders.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

}