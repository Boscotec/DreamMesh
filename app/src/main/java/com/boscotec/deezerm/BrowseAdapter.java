package com.boscotec.deezerm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.ItemViewHolder> {

    private List<Genre> genreList;
    Context context;

    BrowseAdapter(Context context, List<Genre> list) {
        this.context = context;
        this.genreList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        holder.title.setText(genre.getTitle());
        holder.background.setImageResource(genre.getImage());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView background;

        public ItemViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            background = view.findViewById(R.id.background);
        }
    }
}