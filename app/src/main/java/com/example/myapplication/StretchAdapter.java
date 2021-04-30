package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.List;

public class StretchAdapter  extends RecyclerView.Adapter<StretchAdapter.ViewHolder> {

    Context context;
    List<Stretch> stretches;

    public StretchAdapter(Context context, List<Stretch> stretches){
        this.context = context;
        this.stretches = stretches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View StretchView = LayoutInflater.from(context).inflate(R.layout.item_stretch, parent, false);
        return new ViewHolder(StretchView);
    }

    @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stretch stretch = stretches.get(position);
        holder.bind(stretch);
    }


    @Override
    public int getItemCount() {
        return stretches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(Stretch Stretch){
            tvName.setText(Stretch.getName());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, StretchDetailActvity.class);
                    i.putExtra("name", Stretch.getName());
                    i.putExtra("stretch", Parcels.wrap(Stretch));
                    context.startActivity(i);
                }
            });
        }
    }
}
