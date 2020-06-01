package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private List<Food> dataset;
    private OnFoodCheckListener listener;

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public FoodListViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public FoodListAdapter(List<Food> myDataset, OnFoodCheckListener myListener) {
        dataset = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        FoodListViewHolder vh = new FoodListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodListViewHolder holder, final int position) {
        final Food currentFood = dataset.get(position);
        final CheckBox checkBox = holder.cv.findViewById(R.id.cb_food);
        TextView name = holder.cv.findViewById(R.id.tv_name);
        TextView details = holder.cv.findViewById(R.id.tv_details);

        name.setText(currentFood.getName());
        details.setText(currentFood.getDefaultQuantity() + "");

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) {
                    listener.onFoodChecked(currentFood);
                } else {
                    listener.onFoodUnchecked(currentFood);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
