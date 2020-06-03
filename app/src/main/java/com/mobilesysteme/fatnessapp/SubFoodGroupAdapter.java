package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;

public class SubFoodGroupAdapter extends RecyclerView.Adapter<SubFoodGroupAdapter.SubFoodGroupViewHolder> {
    private List<FoodGroup> dataset;
    private OnFoodGroupClickListener listener;

    public static class SubFoodGroupViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public SubFoodGroupViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public SubFoodGroupAdapter(List<FoodGroup> myDataset,  OnFoodGroupClickListener myListener) {
        dataset = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public SubFoodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        SubFoodGroupAdapter.SubFoodGroupViewHolder vh = new SubFoodGroupAdapter.SubFoodGroupViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull SubFoodGroupViewHolder holder, final int position) {
        TextView name = holder.cv.findViewById(R.id.tv_name);
        name.setText(dataset.get(position).getName());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(dataset.get(position), position, false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
