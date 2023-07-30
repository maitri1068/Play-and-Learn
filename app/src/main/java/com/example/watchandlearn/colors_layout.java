package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class colors_layout extends AppCompatActivity implements View.OnClickListener {

    private CardView learn,game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_layout);
        learn = findViewById(R.id.card_learn);
        game = findViewById(R.id.card_game);
        learn.setOnClickListener(this);
        game.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.card_learn:
                i = new Intent(this,colors.class);
                startActivity(i);
                break;
            case R.id.card_game:
                i = new Intent(this, color_game.class);
                startActivity(i);
                break;
        }}
}