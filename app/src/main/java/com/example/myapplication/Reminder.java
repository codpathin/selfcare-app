package com.example.myapplication;

public class Reminder {

    private String name;
    private int day;
    private int hour;
    private int min;
    private String notes;


    public Reminder(String name, int day, int hour, int min,  String notes){
        this.name = name;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.notes = notes;
    }

    public String getName(){
        return name;
    }

    public int getDate(){
        return day;
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
