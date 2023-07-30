package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class numbers_layout extends AppCompatActivity implements View.OnClickListener {

    private CardView learn,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_layout);
        learn = findViewById(R.id.card_learn);
        count = findViewById(R.id.card_count);
        learn.setOnClickListener(this);
        count.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()) {
            case R.id.card_learn:
                i = new Intent(this,numbers.class);
                startActivity(i);
                break;
            case R.id.card_count:
                i = new Intent(this, count.class);
                startActivity(i);
                break;
        }}
}