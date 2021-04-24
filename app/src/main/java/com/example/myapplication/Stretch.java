package com.example.myapplication;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Stretch {
    String name;
    String description;
    String display;

    public Stretch() {}

    public Stretch(String name, String description, String display) {
        this.name = name;
        this.description = description;
        this.display = display;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getdisplay(){
        return display;
    }
}


