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
        EditText defaultValue = holder.cv.findViewById(R.id.ev_defaultValue);
        TextView amountText = holder.cv.findViewById(R.id.tv_foodAmount);

        name.setText(currentFood.getName());
        currentCalories.setText("");
        defaultValue.setText(Integer.toString(currentFood.getDefaultQuantity()));

        final Button addBtn = holder.cv.findViewById(R.id.btn_addFood);
        addBtn.setOnClickListener(view -> {
            amountText.setText(Integer.toString(getAmount(amountText)+1));
            currentCalories.setText(displayCaloriesWithGramm(defaultValue,amountText));
        });

        final Button rmBtn = holder.cv.findViewById(R.id.btn_rmFood);
        rmBtn.setOnClickListener(view -> {
            if(getAmount(amountText) > 0){

                amountText.setText(String.valueOf(getAmount(amountText)-1));
                if(getCalorieSum(defaultValue,amountText)>0) {
                    currentCalories.setText(displayCaloriesWithGramm(defaultValue,amountText));
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

    /**
     * Calculates total calories per selected food
     *
     * @param defaultValue input field for default calories
     * @param amountText text field with amount counter
     * @return multiplication product of default calories and amount
     */
    private int getCalorieSum(EditText defaultValue, TextView amountText){
        int sum = Integer.parseInt(defaultValue.getText().toString().trim()) * Integer.parseInt(amountText.getText().toString());
        return sum;
    }

    /**
     *  Get the current displayed amount
     *
      * @param amountText text field with amount counter
     * @return number within textview
     */
    private int getAmount(TextView amountText){
        return Integer.parseInt(amountText.getText().toString());
    }

    /**
     * Calculates calories and creates String to display on screen
     *
     * @param defaultValue input field for default calories
     * @param amountText text field with amount counter
     * @return formatted calorie text with g unit
     */
    private String displayCaloriesWithGramm(EditText defaultValue, TextView amountText){
        return String.format("%d g", getCalorieSum(defaultValue,amountText));
    }

}
