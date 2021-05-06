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


public class StretchListFragment extends Fragment {

    List<Stretch> stretches;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stretchlist, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        RecyclerView rvStretches = view.findViewById(R.id.rvStretches);

        stretches = new ArrayList<>();
        stretches.addAll(Stretch.createList());


        StretchAdapter stretchAdapter = new StretchAdapter(view.getContext(), stretches);

        rvStretches.setAdapter(stretchAdapter);

        rvStretches.setLayoutManager(new LinearLayoutManager(view.getContext()));

        super.onViewCreated(view, savedInstanceState);

    }


}