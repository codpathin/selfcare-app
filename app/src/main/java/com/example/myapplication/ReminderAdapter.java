package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    Context context;
    List<Reminder> reminders;


    public ReminderAdapter(Context context, List<Reminder> reminders) {
        this.context = context;
        this.reminders = reminders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //bind values based on pos of element
        //get data @ pos
        Reminder reminder = reminders.get(position);
        //bind tweet w/ viewholder
        holder.bind(reminder);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //for reach row, inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_reminder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        reminders.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Reminder> allReminders) {
        reminders.addAll(allReminders);
        notifyDataSetChanged();
    }

    //define viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView tvReminder;
        TextView tvRNote;
        TextView tvRTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvReminder = itemView.findViewById(R.id.tvReminder);
            tvRNote = itemView.findViewById(R.id.tvRNote);
            tvRTime = itemView.findViewById(R.id.tvRTime);
        }

        public void bind(Reminder reminder) {
            tvReminder.setText(reminder.getName());
            tvRNote.setText(reminder.getNotes());
            tvRTime.setText(reminder.getHour()+":"+reminder.getMin());
        }
    }
}
