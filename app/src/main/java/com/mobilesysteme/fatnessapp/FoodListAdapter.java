package com.mobilesysteme.fatnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;

import java.util.List;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private List<Food> dataset;
    private OnFoodAddListener listener;
    private final Context context;

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public FoodListViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public FoodListAdapter(List<Food> myDataset, OnFoodAddListener listener, Context context) {
        dataset = myDataset;
        this.listener = listener;
        this.context = context;
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
        TextView name = holder.cv.findViewById(R.id.tv_name);
        TextView currentGrams = holder.cv.findViewById(R.id.tv_details);
        EditText defaultValue = holder.cv.findViewById(R.id.ev_defaultValue);
        TextView amountText = holder.cv.findViewById(R.id.tv_foodAmount);

        name.setText(currentFood.getName());
        currentGrams.setText("");
        defaultValue.setText(Integer.toString(currentFood.getDefaultQuantity()));

        defaultValue.addTextChangedListener(new DefaultValueTextWatcher(currentFood, listener, context, defaultValue));

        final Button addBtn = holder.cv.findViewById(R.id.btn_addFood);
        addBtn.setOnClickListener(view -> {
            amountText.setText(Integer.toString(getAmount(amountText)+1));
            currentGrams.setText(displayUnitWithGramm(defaultValue,amountText));
            listener.addFood(currentFood,getUnitSum(defaultValue,amountText));
        });

        final Button rmBtn = holder.cv.findViewById(R.id.btn_rmFood);
        rmBtn.setOnClickListener(view -> {
            if(getAmount(amountText) > 0){

                amountText.setText(String.valueOf(getAmount(amountText)-1));
                if(getUnitSum(defaultValue,amountText)>0) {
                    currentGrams.setText(displayUnitWithGramm(defaultValue,amountText));
                    listener.addFood(currentFood,getUnitSum(defaultValue,amountText));
                }else{
                    currentGrams.setText("");
                    listener.rmFood(currentFood);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     * Calculates total grams per selected food
     *
     * @param defaultValue input field for default grams
     * @param amountText text field with amount counter
     * @return multiplication product of default calories and amount
     */
    private int getUnitSum(EditText defaultValue, TextView amountText){
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
    private String displayUnitWithGramm(EditText defaultValue, TextView amountText){
        return String.format("%d g", getUnitSum(defaultValue,amountText));
    }
}
