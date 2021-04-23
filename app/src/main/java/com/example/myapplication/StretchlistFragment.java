package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class StretchlistFragment extends Fragment {

    List<Stretch> stretches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stretchlist, container, false);



        RecyclerView rvStretches = getView().findViewById(R.id.rvStretches);
        stretches = new ArrayList<>();

        //Create


        return view;
    }
}