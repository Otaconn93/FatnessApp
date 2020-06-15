package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import java.util.ArrayList;
import java.util.Map;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private Map<Food, Integer> dataset;

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public FoodListViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public FoodListAdapter(Map<Food, Integer> myDataset) {
        dataset = myDataset;
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
        final Food currentFood = new ArrayList<>(dataset.keySet()).get(position);
        TextView name = holder.cv.findViewById(R.id.tv_name);
        TextView currentCalories = holder.cv.findViewById(R.id.tv_details);
        EditText defaultValue = holder.cv.findViewById(R.id.defaultValue);
        TextView amountText = holder.cv.findViewById(R.id.amount);

        name.setText(currentFood.getName());
        currentCalories.setText("");
        defaultValue.setText(Integer.toString(currentFood.getDefaultQuantity()));

        final Button addBtn = holder.cv.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> {
            amountText.setText(Integer.toString(Integer.parseInt(amountText.getText().toString())+1));
            currentCalories.setText(Integer.parseInt(defaultValue.getText().toString().trim()) * Integer.parseInt(amountText.getText().toString()) + " g");
        });

        final Button rmBtn = holder.cv.findViewById(R.id.rmBtn);
        rmBtn.setOnClickListener(view -> {
            if(Integer.parseInt(amountText.getText().toString()) > 0){
                amountText.setText(Integer.toString(Integer.parseInt(amountText.getText().toString())-1));
                int caloriesSum = Integer.parseInt(defaultValue.getText().toString().trim()) * Integer.parseInt(amountText.getText().toString());
                if(caloriesSum>0) {
                    currentCalories.setText(caloriesSum+" g");
                }else{
                    currentCalories.setText("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
