package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class birds_layout extends AppCompatActivity implements View.OnClickListener {

    private CardView alphabets,numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds_layout);
        alphabets = findViewById(R.id.card_learn);
        numbers = findViewById(R.id.card_puzzle);
        alphabets.setOnClickListener(this);
        numbers.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.card_learn:
                i = new Intent(this,birds.class);
                startActivity(i);
                break;
            case R.id.card_puzzle:
                i = new Intent(this, puzzle_layout.class);
                startActivity(i);
                break;
        }}
}