package com.hackthe6ix.homechef.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hackthe6ix.homechef.R;
import com.hackthe6ix.homechef.adapter.FavouriteAdapter;
import com.hackthe6ix.homechef.models.FavoriteRecipes.Results;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

;

public class FavoriteFragment extends Fragment {
    private BottomNavigationView navigationBar;
    private ArrayList<String> recipeList;
    private TextView resultsTextView;
    RecyclerView favouriteRecyclerView;
    FavouriteAdapter adapter;
    ProgressBar loadBar;

    final List<Results> resultList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FavouriteAdapter(resultList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);


        loadBar = (ProgressBar) rootView.findViewById(R.id.progressBar_load);

        favouriteRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_favourite);
        LinearLayoutManager linearlayout = new LinearLayoutManager(getActivity());
        favouriteRecyclerView.setLayoutManager(linearlayout);

        return rootView;
    }

    public void getFavouritesFromParse(){
        loadBar.setVisibility(View.VISIBLE);
        favouriteRecyclerView.setVisibility(View.INVISIBLE);
        resultsTextView = (TextView) getView().findViewById(R.id.results_text);
        // Parse through database and pass data to adapter
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Favourite");
        query.orderByAscending("createdAt");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                resultList.clear();
                if (e == null) {
                    for (ParseObject object : objects) {
                        Results result = new Results(object.getString("recipePhoto"), object.getString("recipeName"), object.getString("recipeId"));
                        resultList.add(result);
                    }
                }
                loadBar.setVisibility(View.INVISIBLE);
                favouriteRecyclerView.setVisibility(View.VISIBLE);
                favouriteRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                noResults(resultsTextView, resultList);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            getFavouritesFromParse();
        }
    }

    // displays noResults if no favourites
    @SuppressLint("SetTextI18n")
    public static void noResults(TextView resultsText, List<Results> list) {
        if (list.isEmpty()) {
            resultsText.setText("No Results");
        }else{
            resultsText.setText(" ");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getFavouritesFromParse();
    }
}