package com.hackthe6ix.homechef.models.RecipeSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionRestrictions {
    @SerializedName("Calories")
    @Expose
    private Calories calories;

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }
}
