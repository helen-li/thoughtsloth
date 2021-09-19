package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginByDevice = findViewById(R.id.logintext);
        TextView loginGoogle = findViewById(R.id.googletext);
        TextView loginSkip = findViewById(R.id.skiplogin);
        EditText loginInput = findViewById(R.id.inputstring);

        loginByDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginInput.getText().toString(); //string inputted by user
                System.out.println(loginInput.getText().toString());
                //TODO whatever happens when you click login by device
            }
        });

        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("working");

                //TODO whatever happens when you sign in by google
            }
        });

        loginSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("working");
                Intent startIntent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(startIntent);
                //TODO skipping login, brings you to next Activity
                //startActivity(new Intent(MainActivity2.this, MainActivity2.class)); // 2nd arg to be replaced with new activityName.class
            }
        });
    }
}