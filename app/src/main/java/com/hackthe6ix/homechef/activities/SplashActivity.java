package com.hackthe6ix.homechef.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(AccountActivity.getUserName(SplashActivity.this).length() == 0)
        {
            // call Login Activity - user not yet logged in
            Intent i = new Intent(SplashActivity.this, LogInActivity.class);
            startActivity(i);
        }
        else
        {
            // call IngredientSearch Activity - user previously logged in
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        }
        finish();
    }
}