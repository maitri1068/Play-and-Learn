package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class alphabets extends AppCompatActivity implements View.OnClickListener {

    private CardView alphabets,numbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);
        alphabets = findViewById(R.id.card_learn);
        numbers = findViewById(R.id.card_trace);
        alphabets.setOnClickListener(this);
        numbers.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.card_learn:
                i = new Intent(this,alphabet_learn.class);
                startActivity(i);
                break;
            case R.id.card_trace:
                i = new Intent(this, trace.class);
                startActivity(i);
                break;
        }}
}