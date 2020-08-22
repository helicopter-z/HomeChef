package com.hackthe6ix.homechef.network;

import com.hackthe6ix.homechef.models.RecipeContent.RecipeInfo;
import com.hackthe6ix.homechef.models.RecipeSearch.RecipeList;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


// Retrofit turns your HTTP API into a Java interface
// Passes the end point or filename that will provide the JSON data
public interface GetRecipe {
    @GET("/v1/api/recipes")
    Call<RecipeList> searchForRecipe(
            @Query("_app_id") String id
            ,@Query("_app_key") String key
            ,@Query("q")String search
    );

    @GET
    Call<RecipeList> allowedIngredients(
            @Url String url
    );

    /* Implement for RecipeActivity - the recipe details */
    @GET("/v1/api/recipe/{recipe-id}?")
    Call<RecipeInfo> recipeDetail(
            @Path("recipe-id")String search
            ,@Query("_app_id") String id
            ,@Query("_app_key") String key


    );
}
