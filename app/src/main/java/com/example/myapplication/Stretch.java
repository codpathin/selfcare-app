package com.example.myapplication;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Stretch {
    String name;
    String description;
    int display;

    public Stretch() {}

    public Stretch(String name, String description, int display) {
        this.name = name;
        this.description = description;
        this.display = display;
    }

    public static List<Stretch> createList(){
        List<Stretch> stretches = new ArrayList<>();

        //check the values directory to add new arrays with pictures
        stretches.add(new Stretch("Desk Stretch Routine", "These stretches can be done while sitting at your desk for with little" +
                "to no getting up/moving around. Great for just keeping the muscles in especially the neck from tensing up.", R.array.desk_stretch_routine));
        stretches.add(new Stretch("Yoga Before Bed", "Do this nightly to help ease and relax all your muscles before bed, this can help better" +
                "your sleep and will help you feel more refreshed/limber the next morning!", R.array.sleep_stretch_routine));
        stretches.add(new Stretch("Morning Stretches", "Do this after waking up and before getting entirely ready for the day. This is so you" +
                "can feel light on your feet and limber through the day. Helps get rid of that morning stiffness in your muscles, only takes a few minutes each morning!", R.array.morning_stretch_routine));
        return stretches;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDisplay(){
        return display;
    }


}


