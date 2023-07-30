package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class animals_layout extends AppCompatActivity implements View.OnClickListener {

    private CardView learn,homes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_layout);
        learn = findViewById(R.id.card_learn);
        homes = findViewById(R.id.card_homes);
        learn.setOnClickListener(this);
        homes.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.card_learn:
                i = new Intent(this,animals.class);
                startActivity(i);
                break;
            case R.id.card_homes:
                i = new Intent(this, animal_homes.class);
                startActivity(i);
                break;
        }}
}