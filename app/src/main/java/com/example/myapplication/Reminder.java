package com.example.myapplication;

public class Reminder {

    private String name;
    private String date;
    private int hour;
    private int min;
    private String notes;


    public Reminder(String name, String date, int hour, int min,  String notes){
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.min = min;
        this.notes = notes;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public int getHour(){
        return hour;
    }

    public int getMin(){
        return min;
    }

    public String getNotes(){
        return notes;
    }
}
