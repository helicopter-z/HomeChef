package com.hackthe6ix.homechef.models.RecipeContent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Flavors {
    @SerializedName("course")
    @Expose
    private List<String> flavors = null;
}
