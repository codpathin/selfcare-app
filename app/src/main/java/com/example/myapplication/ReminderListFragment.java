package com.example.myapplication;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class ReminderListFragment extends Fragment {
    List<Reminder> reminders;
    RecyclerView rvReminders;
    ReminderAdapter reminderAdapter;
    Reminder newly;
    Bundle bundle;

    public static final String TAG = "ReminderListFragment";

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reminderlist, container, false);
        /*Log.d("PAIN","Checking if getarguments null:"); //TODO: persist reminders added
        if ((getArguments())!= null) {
            newly = Parcels.unwrap(this.getArguments().getParcelable("reminder"));
        }
        else {
            Log.d("PAIN","was null");
        }
        */

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvReminders);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RemindarAdapter(view.getContext(), viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        /*
        MainActivity activity = (MainActivity) getActivity();
        rvReminders = view.findViewById(R.id.rvReminders);
        reminders = new ArrayList<>(); //TODO: prevent reminders being replaced
        if (newly !=null) {
            reminders.add(newly);
        }
        reminderAdapter = new ReminderAdapter(view.getContext(), reminders);
        rvReminders.setAdapter(reminderAdapter);
        rvReminders.setLayoutManager(new LinearLayoutManager(view.getContext()));
        */

    }

    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String name = itemObj.getString("name");
                String date = itemObj.getString("date");
                String notes = itemObj.getString("notes");
                int min = itemObj.getInt("min");
                int hour = itemObj.getInt("hour");

                Remindar remindar = new Remindar(name, date, hour, min, notes);
                viewItems.add(remindar);
            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJson: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.remindar);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
        }
}

