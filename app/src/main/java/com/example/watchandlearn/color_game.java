package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class color_game extends AppCompatActivity  implements Animation.AnimationListener {

    ImageButton red,yellow,green,blue,orange,pink,purple,black,white;
    ImageView a1,a2,a3,a4,a5,a6,a7,a8,a9;
    ImageButton hint;
    ColorDrawable background ;
    int backgroundColor=0xFF000000;
    int i=0;
    TextToSpeech t1;
    private View contentView,anim_view;
LottieAnimationView anim;
    int flag=0;
    private static final long s_time=6000;
    private CountDownTimer count_timer;
    private long mtimeleft=s_time;
    Animation FadeIn, FadeOut;
    View colorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        contentView = findViewById(R.id.content);
       anim_view = findViewById(R.id.anim);
        anim_view.setVisibility(View.GONE);
        anim=findViewById(R.id.anim);
        red = findViewById(R.id.button_red);
        yellow = findViewById(R.id.button_yellow);
        green = findViewById(R.id.button_green);
        blue = findViewById(R.id.button_blue);
        pink = findViewById(R.id.button_pink);
        orange = findViewById(R.id.button_orange);
        purple = findViewById(R.id.button_purple);
        black = findViewById(R.id.button_black);
        white = findViewById(R.id.button_white);
        hint = findViewById(R.id.next);
        colorView = findViewById(R.id.color_view);

        FadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        FadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        FadeIn.setAnimationListener(this);
        FadeOut.setAnimationListener(this);

        a1 = findViewById(R.id.a1);
       a2 = findViewById(R.id.a2);
       a3 = findViewById(R.id.a3);
       a4 = findViewById(R.id.a4);
       a5 = findViewById(R.id.a5);
       a6 = findViewById(R.id.a6);
       a7 = findViewById(R.id.a7);
       a8 = findViewById(R.id.a8);
       a9 = findViewById(R.id.a9);
        red.setOnClickListener(this::onClick);
        yellow.setOnClickListener(this::onClick);
        green.setOnClickListener(this::onClick);
        black.setOnClickListener(this::onClick);
        blue.setOnClickListener(this::onClick);
        white.setOnClickListener(this::onClick);
        pink.setOnClickListener(this::onClick);
        purple.setOnClickListener(this::onClick);
        orange.setOnClickListener(this::onClick);
        t1=new TextToSpeech(getApplicationContext(), status -> {
            if(status != TextToSpeech.ERROR) {
                t1.setLanguage(Locale.UK);
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak("press the fish of colour you see in the box",TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        if(flag == 0){
            starttimer();
        }
    }

    private void starttimer(){
        count_timer=new CountDownTimer(mtimeleft,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mtimeleft=millisUntilFinished;
            }
            @Override
            public void onFinish() {
                if(flag == 0) {
                    if (backgroundColor == 0xFF000000)
                        a1.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFFFFF00)
                        a2.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFFF0000)
                        a3.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFF00FF00)
                        a4.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFFF00FF)
                        a5.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFFFFFFF)
                        a6.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFF0000FF)
                        a7.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFFFA500)
                        a8.setImageResource(R.drawable.arrow);
                    else if (backgroundColor == 0xFFA020F0)
                        a9.setImageResource(R.drawable.arrow);
                }
            }
        }.start();
    }
    private void resettimer(){
        mtimeleft=s_time;
    }
    public void arrow(ImageView i){
        flag=1;
        i.setImageDrawable(null);
        resettimer();
        starttimer();
        flag=0;
    }
    public void onClick(View v)
    {
        int button = v.getId();
        background = (ColorDrawable)colorView.getBackground();
        backgroundColor=  background.getColor();
        switch (button){

            case R.id.button_black :
            {
                if(backgroundColor==0xFF000000)
                {
                    showCorrect();
                    t1.speak("BLACK", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.Green));
                    colorView.invalidate();
                    backgroundColor=0xFF00FF00;
                  arrow(a1);
                }else {
                    showWrong();
                    arrow(a1);
                }
            }
            break;
            case R.id.button_white :
            {
                if(backgroundColor==0xFFFFFFFF) {
                    showCorrect();
                    t1.speak("WHITE", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.Yellow));
                    colorView.invalidate();
                    backgroundColor=0xFFFFFF00;
                    arrow(a6);
                }else{ showWrong();
                arrow(a6);}
            }
            break;
            case R.id.button_red :
            {
                if(backgroundColor==0xFFFF0000)  {
                    showCorrect();
                    t1.speak("RED", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.purple));
                    colorView.invalidate();
                    backgroundColor=0xFFA020F0;
                    arrow(a3);
                }else {showWrong();
                arrow(a3);}
            }
            break;
            case R.id.button_pink :
            {
                if(backgroundColor==0xFFFF00FF)  {
                    showCorrect();
                    t1.speak("PINK", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.black));
                    colorView.invalidate();
                    backgroundColor = 0xFF000000;
                    arrow(a5);
                }else{ showWrong();
                arrow(a5);}
            }
            break;
            case R.id.button_yellow :
            {
                if(backgroundColor==0xFFFFFF00)  {
                    showCorrect();
                    t1.speak("YELLOW", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.blue));
                    colorView.invalidate();
                    backgroundColor=0xFF0000FF;
                    arrow(a2);
                }else {showWrong();
                arrow(a2);}
            }
            break;
            case R.id.button_orange :
            {
                if(backgroundColor==0xFFFFA500)  {
                    showCorrect();
                    t1.speak("ORANGE", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.Pink));
                    colorView.invalidate();
                    arrow(a8);
                    backgroundColor=0xFFFF00FF;
                }else {showWrong();
                arrow(a8);}
            }
            break;
            case R.id.button_blue :
            {
                if(backgroundColor==0xFF0000FF)  {
                    showCorrect();
                    t1.speak("BLUE", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.red));
                    colorView.invalidate();
                    backgroundColor=0xFFFF0000;
                   arrow(a7);
                }else {showWrong();
                arrow(a7);}
            }
            break;
            case R.id.button_purple :
            {
                if(backgroundColor==0xFFA020F0)  {
                    showCorrect();
                    t1.speak("PURPLE", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.orange));
                    colorView.invalidate();
                    backgroundColor=0xFFFFA500;
                    arrow(a9);
                }else {showWrong();
               arrow(a9);}
            }
            break;
            case R.id.button_green:
            {
                if(backgroundColor==0xFF00FF00)  {
                    showCorrect();
                    t1.speak("GREEN", TextToSpeech.QUEUE_FLUSH, null);
                    colorView.setBackgroundColor(getResources().getColor(R.color.white));
                    colorView.invalidate();
                    backgroundColor=0xFFFFFFFF;
                    arrow(a4);

                }else {showWrong();
                arrow(a4);}
            }
            break;
        }}
    private void showWrong() {
        Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
        t1.speak("WRONG", TextToSpeech.QUEUE_FLUSH, null);
        anim.setAnimation(R.raw.wrong);
        crossfadein();
        crossfadeout();
    }
    private void showCorrect() {
        Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
        t1.speak("RIGHT", TextToSpeech.QUEUE_FLUSH, null);

        anim.setAnimation(R.raw.correct2);
        crossfadein();
        crossfadeout();
    }

    public void crossfadein(){
        anim_view.setVisibility(View.VISIBLE);
        anim_view.startAnimation(FadeIn);
        contentView.startAnimation(FadeOut);

    }
    public void crossfadeout(){
        contentView.setVisibility(View.VISIBLE);
        contentView.startAnimation(FadeIn);
        anim_view.startAnimation(FadeOut);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}