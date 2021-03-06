package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.fragments.CalendarFragment;
import com.example.myapplication.fragments.ComposeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity implements ComposeFragment.OnDataPass {
    final FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inits app with calendar fragment
        fragmentManager.beginTransaction().replace(R.id.flContainer, new CalendarFragment()).commit();
        /*
        Calendar calendar = Calendar.getInstance();

        //For testing different days
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int lastDay = settings.getInt("day", 0);

        if (lastDay != currentDay) {
            //checks if this is first time the user opens app for the day
            //also sends the day-month-year to the sharedpreferences, so moodactivity can get the data
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            editor.putInt("month", calendar.get(Calendar.MONTH));
            editor.putInt("year", calendar.get(Calendar.YEAR));
            editor.commit();

            startActivity(new Intent(MainActivity.this, MoodActivity.class));
        }*/


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        fragment = new CalendarFragment();
                        break;
                    case R.id.action_reminderlist:
                        fragment = new ReminderListFragment();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_stretches:
                        fragment = new StretchListFragment();
                        break;
                    default: return true;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDataPass(Reminder r) { //todo: pass properly
        Bundle bundle = new Bundle();
        bundle.putParcelable("reminder",Parcels.wrap(r)); //todo: figure out why time binds to notes
        ReminderListFragment frag = new ReminderListFragment();
        frag.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContainer,frag).commit();
    }
}