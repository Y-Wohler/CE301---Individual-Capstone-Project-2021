package com.example.andriodapp;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message.equals("Ro") || message.equals("ro") || message.equals("") || message.equals("Roberta")) {
            message = "HELLO Gorgeous Elf Princess!";
        }else {
            message = "Not even close to as beautiful as Ro.";
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}