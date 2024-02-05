package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    DBhelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the SignUp button in the layout
        Button signUpButton = findViewById(R.id.signupButton);
        Button loginButton = findViewById(R.id.loginButton);

        etUsername = findViewById(R.id.usernameEditText);
        etPassword = findViewById(R.id.passwordEditText);

        // Set OnClickListener on the SignUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start SignUpActivity
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);

            }
        });

        dbHelp = new DBhelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelp.checkUser(etUsername.getText().toString(),etPassword.getText().toString());
                if(isLoggedId){
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"LOGIN FAILED!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}