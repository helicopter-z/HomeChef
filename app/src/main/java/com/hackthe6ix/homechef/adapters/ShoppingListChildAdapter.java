package com.hackthe6ix.homechef.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hackthe6ix.homechef.R;

import java.util.List;

// Adapter for the Recipe individual ingredients
public class ShoppingListChildAdapter extends RecyclerView.Adapter<ShoppingListChildAdapter.IngredientViewHolder> {
    private List<String> ingredients;
    private AppCompatActivity activity;

    public ShoppingListChildAdapter(List<String> ingredients, AppCompatActivity activity) {
        this.ingredients = ingredients;
        this.activity = activity;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_shopping_list_child, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        if (ingredients.get(position).length() > 0) {
            holder.label.setText(ingredients.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView label;

        IngredientViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.textView_ingredient_list);
        }
    }
}
