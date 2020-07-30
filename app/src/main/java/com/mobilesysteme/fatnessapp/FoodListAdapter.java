package com.mobilesysteme.fatnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;

import java.util.List;
import java.util.Locale;

/**
 * Adapter for the cards in FoodListActivity. Describes the functionality every single card.
 *
 * @author Kevin Bücher
 *
 */
public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {

    private List<Food> foods;
    private OnFoodAddListener listener;
    private final Context context;

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public FoodListViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public FoodListAdapter(List<Food> myDataset, OnFoodAddListener listener, Context context) {
        this.foods = myDataset;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new FoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodListViewHolder holder, final int position) {

        final Food currentFood = foods.get(position);
        final TextView currentGrams = holder.getCardView().findViewById(R.id.tv_details);
        final EditText defaultValue = holder.getCardView().findViewById(R.id.ev_defaultValue);
        final TextView amountText = holder.getCardView().findViewById(R.id.tv_foodAmount);

        ((TextView)holder.getCardView().findViewById(R.id.tv_name)).setText(currentFood.getName());
        currentGrams.setText("");
        defaultValue.setText(String.format(Locale.GERMANY, "%d", currentFood.getDefaultQuantity()));

        defaultValue.addTextChangedListener(new DefaultValueTextWatcher(currentFood, listener, context, defaultValue));

        initButtonAddFood(holder, currentFood, currentGrams, defaultValue, amountText);

        initButtonRemoveFood(holder, currentFood, currentGrams, defaultValue, amountText);
    }

    /**
     * gives the AddButton the functionality to increase the quantity of Food
     */
    private void initButtonAddFood(@NonNull FoodListViewHolder holder, Food currentFood, TextView currentGrams, EditText defaultValue, TextView amountText) {

        holder.getCardView().findViewById(R.id.btn_addFood).setOnClickListener(view -> {
            amountText.setText(String.format(Locale.GERMANY, "%d", getAmount(amountText)+1));
            currentGrams.setText(displayUnitWithGramm(defaultValue,amountText));
            listener.addFood(currentFood,getUnitSum(defaultValue,amountText));
        });
    }

    /**
     * gives the RemoveButton the functionality to decrease the quantity of Food
     */
    private void initButtonRemoveFood(@NonNull FoodListViewHolder holder, Food currentFood, TextView currentGrams, EditText defaultValue, TextView amountText) {

        holder.getCardView().findViewById(R.id.btn_rmFood).setOnClickListener(view -> {
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
        return foods.size();
    }

    /**
     * Calculates total grams per selected food
     *
     * @param defaultValue input field for default grams
     * @param amountText text field with amount counter
     * @return multiplication product of default calories and amount
     */
    private int getUnitSum(EditText defaultValue, TextView amountText){
        return Integer.parseInt(defaultValue.getText().toString().trim()) * Integer.parseInt(amountText.getText().toString());
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
        return String.format(Locale.GERMANY, "%d g", getUnitSum(defaultValue,amountText));
    }
}
