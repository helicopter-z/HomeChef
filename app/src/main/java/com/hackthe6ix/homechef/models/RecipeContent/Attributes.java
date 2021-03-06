package com.hackthe6ix.homechef.models.RecipeContent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attributes {
    @SerializedName("course")
    @Expose
    private List<String> course = null;

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }
}