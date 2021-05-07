package com.example.myapplication;

public class Remindar {

    private String name;
    private String date;
    private int hour;
    private int min;
    private String notes;


    public Remindar(String name, String date, int hour, int min,  String notes){
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

    public void setName(String nName){
        name = nName;
    }

    public void setDate(String nDate){
        date = nDate;
    }

    public void setHour(int nHour){
        hour = nHour;
    }

    public void setMin(int nMin){
        min = nMin;
    }

    public void setNotes(String nNotes){
        notes = nNotes;
    }
}
