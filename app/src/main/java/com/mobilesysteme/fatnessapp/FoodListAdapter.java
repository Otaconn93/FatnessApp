package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private List<Food> dataset;

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public FoodListViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public FoodListAdapter(List<Food> myDataset) {
        dataset = myDataset;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        FoodListViewHolder vh = new FoodListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodListViewHolder holder, final int position) {
        TextView name = holder.cv.findViewById(R.id.tv_name);
        name.setText(dataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
