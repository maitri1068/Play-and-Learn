package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class animation extends AppCompatActivity {

    TextView text;
    LottieAnimationView anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        text =findViewById(R.id.text);
        anim = findViewById(R.id.anim_view);
        text.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(7) + 1,id=0;
        switch (randomInt) {
            case 1:
                id = R.raw.anim1;
                break;
            case 2:
                id = R.raw.anim2;
                break;
            case 3:
                id = R.raw.anim3;
                break;
            case 4:
                id = R.raw.anim4;
                break;
            case 5:
                id = R.raw.anim5;
                break;
            case 6:
                id = R.raw.anim6;
                break;

            case 7:
                id = R.raw.anim7;
                break;

        }
        anim.setAnimation(id);

        anim.animate().setStartDelay(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(animation.this,puzzle_layout.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },5000);
    }
}
