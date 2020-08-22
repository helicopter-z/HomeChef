package com.hackthe6ix.homechef.activities;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hackthe6ix.homechef.R;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    TextView changeSignUpModeTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    RelativeLayout backgroundRelativeLayout;

    //keyboard gone once click enter
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            //signUp(view);
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        return false;
    }

    public void SignUpClick(View view){
        Intent i = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    public void loginClick(View view){
        login(view);
    }


    @Override
    public void onClick(View view) {
        // close keyboard if click somewhere else
        if (view.getId() == R.id.backgroundLinearLayout){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    public void login(View view){


        if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this,"A username and password are required", Toast.LENGTH_SHORT).show();
        }else{
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user!=null){
                        Log.i("SignUp", "LogIn successful");

                        // Stores information that user already logged in
                        AccountActivity.setUserName(getApplicationContext(),user.getUsername());
                        Intent i = new Intent(LogInActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }else{
                        Toast.makeText(LogInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backgroundRelativeLayout = (RelativeLayout) findViewById(R.id.backgroundRelativeLayout);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        passwordEditText.setOnKeyListener(this);
        backgroundRelativeLayout.setOnClickListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


}
