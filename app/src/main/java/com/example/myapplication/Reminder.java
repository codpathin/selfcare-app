package com.example.myapplication;

public class Reminder {

    private String name;
    private String date;
    private String time;
    private String notes;

    public Reminder(String name, String date, String time, String notes){
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getNotes(){
        return notes;
    }
}
