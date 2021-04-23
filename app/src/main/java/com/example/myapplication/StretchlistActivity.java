package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class StretchlistActivity extends AppCompatActivity {

    List<Stretch> stretches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretchlist);

        RecyclerView rvStretches = findViewById(R.id.rvStretches);
        stretches = new ArrayList<>();

        StretchAdapter stretchAdapter = new StretchAdapter(this, stretches);

        rvStretches.setAdapter(stretchAdapter);

        rvStretches.setLayoutManager(new LinearLayoutManager(this));

    }
}