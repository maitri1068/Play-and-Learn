package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class score extends AppCompatActivity {
    ImageView star1, star2, star3;
    TextView score;
    String s;
    int s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        score = findViewById(R.id.score);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            s = extras.getString("score");

        }
        s1 = Integer.parseInt(s);
        s1 = s1 / 2;
        score.setText(s1);
        if (s1 <= 4) {
            star1.setVisibility(View.VISIBLE);
        } else if (s1 > 4 && s1 <= 8) {
            star1.setVisibility(View.VISIBLE);
            star2.setVisibility(View.VISIBLE);
        } else if (s1 > 8 && s1 <= 10) {
            star1.setVisibility(View.VISIBLE);
            star2.setVisibility(View.VISIBLE);
            star3.setVisibility(View.VISIBLE);
        }

    }

}