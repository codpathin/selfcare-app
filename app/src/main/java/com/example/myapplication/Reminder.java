package com.example.myapplication;

import android.os.Parcelable;

import android.os.Parcel;

import static java.lang.Integer.valueOf;

public class Reminder implements Parcelable {

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

    //parcel
    public Reminder(Parcel in){
        String[] data = new String[5];
        in.readStringArray(data);
        this.name = data[0];
        this.date = data[1];
        this.hour = Integer.parseInt(data[2]);
        this.min = Integer.parseInt(data[3]);
        this.notes = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.date, Integer.toString(this.hour),Integer.toString(this.min),this.notes});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Reminder createFromParcel(Parcel in){
            return new Reminder(in);
        }

        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };
}
