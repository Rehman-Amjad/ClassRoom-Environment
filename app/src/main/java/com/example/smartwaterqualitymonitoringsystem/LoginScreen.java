package com.example.smartwaterqualitymonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LoginScreen extends AppCompatActivity {

    TextInputEditText ed_email,ed_pass;
    Button btn_help,btn_login;
    String username = "admin";
    String password = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        initView();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String checkUsername = ed_email.getText().toString().trim();
                String checkPassword = ed_pass.getText().toString().trim();

                if (username.equals(checkUsername))
                {
                    if (checkPassword.equals(password))
                    {
                        startActivity(new Intent(LoginScreen.this,DashboardScreen.class));
                        finish();
                    }
                    else
                    {
                        ed_pass.setError("invalid password!");
                        ed_pass.requestFocus();
                    }
                }
                else
                {
                    ed_email.setError("invalid username!");
                    ed_email.requestFocus();
                }

            }
        });

    }

    private void initView()
    {
        ed_email=findViewById(R.id.ed_email);
        ed_pass=findViewById(R.id.ed_pass);
        btn_login=findViewById(R.id.btn_login);
        btn_help=findViewById(R.id.btn_help);
    }
}