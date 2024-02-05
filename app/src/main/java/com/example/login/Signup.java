package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText etUser,etPwd, etRepwd;
    Button btnRegister;
    private DBhelper niggaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUser = findViewById(R.id.username);
        etPwd = findViewById(R.id.password);
        etRepwd = findViewById(R.id.rePassword);
        btnRegister = findViewById(R.id.register);
        niggaHelper = new DBhelper(Signup.this);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String user,pwd,repwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                repwd = etRepwd.getText().toString();

                if(user.equals("") || pwd.equals("") || repwd.equals("")){
                    Toast.makeText(Signup.this,"Required to fill all the fields",Toast.LENGTH_LONG).show();
                }
                else{
                    if(pwd.equals(repwd)){
                        if(niggaHelper.checkUsername(user)){
                            Toast.makeText(Signup.this, "FAILED! USER " + user + " is Already Exist!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //game na mga boss
                        boolean registeredSuccess = niggaHelper.insertData(user,pwd);
                        if(registeredSuccess){
                            Toast.makeText(Signup.this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Signup.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Signup.this,"FAILED! SOMETHING WRONG",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Signup.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(Signup.this,"MALI PASSWORD MO PRE TRY MO TO ' " + pwd + " '",Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}