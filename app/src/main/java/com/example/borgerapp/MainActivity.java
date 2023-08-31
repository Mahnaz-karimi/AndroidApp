package com.example.borgerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.borgerapp.Database.Databaser_SignUp;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButtom, signupButton;

    Button addButton;
    Databaser_SignUp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginButtom = findViewById((R.id.loginButton));
        signupButton = findViewById(R.id.signupButton);

        db = new Databaser_SignUp(this);


        loginButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "ALL fields Required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = db.checkUsernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(MainActivity.this, "login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup_Activity.class);
                startActivity(intent);
            }
        });

    }
}