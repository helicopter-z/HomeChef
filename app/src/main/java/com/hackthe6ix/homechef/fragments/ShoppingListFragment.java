package com.foodapp.android.foodapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hackthe6ix.homechef.R;
import com.hackthe6ix.homechef.adapters.ShoppingListParentAdapter;
import com.hackthe6ix.homechef.models.ShoppingList.ShoppingListResults;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListFragment extends AppCompatActivity {
    final List<ShoppingListResults> resultList = new ArrayList<>();
    ShoppingListParentAdapter adapter;
    RecyclerView shoppingListRecyclerView;
    ProgressBar loadBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ShoppingListParentAdapter(resultList, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        //resultsTextView = (TextView) rootView.findViewById(R.id.results_text);
        loadBar = (ProgressBar) rootView.findViewById(R.id.progressBar_load);


        shoppingListRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_shoppingList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        shoppingListRecyclerView.setLayoutManager(linearLayout);


        loadBar.setVisibility(View.VISIBLE);
        // Parse through database and pass data to adapter
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Favourite");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                resultList.clear();
                if (e == null) {
                    for (ParseObject object : objects) {
                        List<String> tempList = object.getList("ingredientList");
                        if (tempList.size() == 0) {
                            continue;
                        }
                        ShoppingListResults result = new ShoppingListResults(object.getString("recipePhoto"), object.getString("recipeName"), object.getString("recipeId"), tempList);
                        resultList.add(result);
                    }
                }
                loadBar.setVisibility(View.INVISIBLE);
                shoppingListRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //noResults(resultsTextView, resultList);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Parse through database and pass data to adapter
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Favourite");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                resultList.clear();
                if (e == null) {
                    for (ParseObject object : objects) {
                        List<String> tempList = object.getList("ingredientList");
                        if (tempList.size() == 0) {
                            continue;
                        }
                        ShoppingListResults result = new ShoppingListResults(object.getString("recipePhoto"), object.getString("recipeName"), object.getString("recipeId"), tempList);
                        resultList.add(result);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


}
