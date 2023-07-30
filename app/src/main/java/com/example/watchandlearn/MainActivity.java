package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView alphabets,numbers,animals,birds,shapes,colors,quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        alphabets = findViewById(R.id.card_alphabet);
        numbers = findViewById(R.id.card_number);
        animals = findViewById(R.id.card_animals);
        birds = findViewById(R.id.card_birds);
        shapes = findViewById(R.id.card_shapes);
        colors = findViewById(R.id.card_colors);
        quiz = findViewById(R.id.card_quiz);

        alphabets.setOnClickListener(this);
        numbers.setOnClickListener(this);
        animals.setOnClickListener(this);
        birds.setOnClickListener(this);
        shapes.setOnClickListener(this);
        colors.setOnClickListener(this);
        quiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()){
            case R.id.card_alphabet:
                i = new Intent(this, alphabets.class);
                startActivity(i);
                break;
            case R.id.card_number:
                i = new Intent(this, numbers_layout.class);
                startActivity(i);
                break;
            case R.id.card_animals:
                i = new Intent(this, animals_layout.class);
                startActivity(i);
                break;
            case R.id.card_birds:
                i = new Intent(this, birds_layout.class);
                startActivity(i);
                break;
            case R.id.card_shapes:
                i = new Intent(this, shapes.class);
                startActivity(i);
                break;
            case R.id.card_colors:
                i = new Intent(this, colors_layout.class);
                startActivity(i);
                break;
            case R.id.card_quiz:
                i = new Intent(this, quiz.class);
                startActivity(i);
                break;
        }
    }
}