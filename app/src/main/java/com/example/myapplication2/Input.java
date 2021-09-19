package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Input extends AppCompatActivity {

    Button save;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        input = (EditText) findViewById(R.id.input);
        save = (Button) findViewById(R.id.save_input);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputValue = input.getText().toString();
                if(inputValue.isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter your thoughts!", Toast.LENGTH_SHORT).show();

                else {
                    System.out.println(inputValue);
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Checkboxes.class);
                startActivity(startIntent);
            }
        });
    }
}