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

public class RemindarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;


    public RemindarAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_reminder, parent, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                Remindar remindar = (Remindar) listRecyclerItem.get(i);

                itemViewHolder.tvReminder.setText(remindar.getName());
                itemViewHolder.tvRNote.setText(remindar.getNotes());
                itemViewHolder.tvRTime.setText(remindar.getHour()+":"+remindar.getMin());
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView tvReminder;
        TextView tvRNote;
        TextView tvRTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvReminder = itemView.findViewById(R.id.tvReminder);
            tvRNote = itemView.findViewById(R.id.tvRNote);
            tvRTime = itemView.findViewById(R.id.tvRTime);
        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
