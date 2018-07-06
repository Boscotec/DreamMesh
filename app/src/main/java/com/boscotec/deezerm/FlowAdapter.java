package com.boscotec.deezerm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.ItemViewHolder> {

    private List<Genre> flowList;
    Context context;

    FlowAdapter(Context context, List<Genre> list) {
        this.context = context;
        this.flowList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.flow, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Genre genre = flowList.get(position);
        holder.background.setImageResource(genre.getImage());
    }

    @Override
    public int getItemCount() {
        return flowList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton play;
        ImageView background;

        public ItemViewHolder(View view) {
            super(view);
            background = view.findViewById(R.id.background);
            play = view.findViewById(R.id.play);
            play.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Play click", Toast.LENGTH_SHORT).show();
        }
    }
}