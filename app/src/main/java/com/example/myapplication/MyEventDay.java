package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

public class MyEventDay extends EventDay implements Parcelable {
    private String mNote;

    public MyEventDay(Calendar day, int imageResource, String note, int labelColor) {
        super(day, imageResource, labelColor);
        mNote = note;
    }

    public MyEventDay(Calendar day, int imageResource) {
        super(day, imageResource);
    }
    public void setNote(String note) {
        mNote = note;
    }
    public  String getNote() {
        return mNote;
    }
    private MyEventDay(Parcel in) {
        super((Calendar) in.readSerializable(), in.readInt(), in.readInt());
        mNote = in.readString();
    }
    public static final Creator<MyEventDay> CREATOR = new Creator<MyEventDay>() {
        @Override
        public MyEventDay createFromParcel(Parcel in) {
            return new MyEventDay(in);
        }
        @Override
        public MyEventDay[] newArray(int size) {
            return new MyEventDay[size];
        }
    };
    @SuppressLint("RestrictedApi")
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(getCalendar());
        parcel.writeInt((Integer) getImageDrawable());
        parcel.writeInt(getLabelColor());
        parcel.writeString(mNote);
    }
    @Override
    public int describeContents() {
        return 0;
    }



}