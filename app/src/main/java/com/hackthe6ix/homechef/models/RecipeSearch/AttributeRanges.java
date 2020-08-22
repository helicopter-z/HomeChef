package com.hackthe6ix.homechef.models.RecipeSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttributeRanges {
    @SerializedName("flavor-piquant")
    @Expose
    private SpicyLevel spicyLevel;

    public SpicyLevel getFlavorPiquant() {
        return spicyLevel;
    }

    public void setFlavorPiquant(SpicyLevel spicyLevel) {
        this.spicyLevel = spicyLevel;
    }
}
