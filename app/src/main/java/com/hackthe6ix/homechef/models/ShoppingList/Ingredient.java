package com.hackthe6ix.homechef.models.ShoppingList;

public class Ingredient {
    private String ingredientName;
    private boolean isSelected = false;
    public Ingredient(String ingredientName, boolean isSelected){
        this.ingredientName = ingredientName;
        this.isSelected = isSelected;
    }

    public String getIngredientName(){
        return ingredientName;
    }

    public boolean getIsSelected(){
        return isSelected;
    }

}