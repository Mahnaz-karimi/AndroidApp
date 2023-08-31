package com.example.borgerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.borgerapp.Database.Databaser_SignUp;

public class Signup_Activity extends AppCompatActivity {

    Databaser_SignUp db;
    EditText username, password, repassword;
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new Databaser_SignUp(this);

        username = findViewById(R.id.username_txt);
        password = findViewById(R.id.password_txt);
        repassword = findViewById(R.id.confirmpassword_txt);

        signup= findViewById(R.id.button_signup);
        signin= findViewById((R.id.button_signin));

        signup();
        signin();
    }
    private void signup() {
        signup.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                String user= username.getText().toString();
                String password= repassword.getText().toString();
                String repass= repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repass))
                    Toast.makeText(Signup_Activity.this, "ALL fields Required", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(repass)){
                        Boolean checkuser = db.checkUsername(user);
                        if (!checkuser){
                            Boolean insert = db.insertdata(user, password);
                            if(insert) {
                                Toast.makeText(Signup_Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Signup_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup_Activity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup_Activity.this, "Passwrods are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void signin() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

}