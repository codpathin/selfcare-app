package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class StretchlistActivity extends Fragment {

    List<Stretch> stretches;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stretchlist, container, false);

        RecyclerView rvStretches = view.findViewById(R.id.rvStretches);
        stretches = new ArrayList<>();

        StretchAdapter stretchAdapter = new StretchAdapter(view.getContext(), stretches);

        rvStretches.setAdapter(stretchAdapter);

        rvStretches.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretchlist);

        RecyclerView rvStretches = findViewById(R.id.rvStretches);
        stretches = new ArrayList<>();

        StretchAdapter stretchAdapter = new StretchAdapter(this, stretches);

        rvStretches.setAdapter(stretchAdapter);

        rvStretches.setLayoutManager(new LinearLayoutManager(this));

    }*/
}