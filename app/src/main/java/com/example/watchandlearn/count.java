package com.example.watchandlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class count extends AppCompatActivity implements Animation.AnimationListener {

    TextToSpeech t1;
    ImageButton next,one,two,three,four,five,six,seven,eight,nine;
    ImageView a1,a2,a3,a4,a5,a6,a7,a8,a9;
    ImageView img;
    Context context;
    DatabaseReference getImage,getId;
    String message;
    String toSpeak="SEVEN";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private View contentView,anim_view;
    LottieAnimationView anim;
    int flag=0;
    private static final long s_time=6000;
    private CountDownTimer count_timer;
    private long mtimeleft=s_time;
    Animation FadeIn, FadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        next = findViewById(R.id.next);
        one = findViewById(R.id.button_one);
        two = findViewById(R.id.button_two);
        three = findViewById(R.id.button_three);
        four = findViewById(R.id.button_four);
        five = findViewById(R.id.button_five);
        six = findViewById(R.id.button_six);
        seven = findViewById(R.id.button_seven);
        eight = findViewById(R.id.button_eight);
        nine = findViewById(R.id.button_nine);
        img = findViewById(R.id.imageview);
        contentView= findViewById(R.id.content);
        anim_view=findViewById(R.id.anim);
        anim=findViewById(R.id.anim);


        FadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        FadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        FadeIn.setAnimationListener(this);
        FadeOut.setAnimationListener(this);

        one.setOnClickListener(this::onClick);
        two.setOnClickListener(this::onClick);
        three.setOnClickListener(this::onClick);
        four.setOnClickListener(this::onClick);
        five.setOnClickListener(this::onClick);
        six.setOnClickListener(this::onClick);
        seven.setOnClickListener(this::onClick);
        eight.setOnClickListener(this::onClick);
        nine.setOnClickListener(this::onClick);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);

                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.speak("Count objects in box and press that number",TextToSpeech.QUEUE_FLUSH, null);
            }
        });

         firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference = firebaseDatabase.getReference();
        if(flag == 0){
            starttimer();
        }


    }
    public void changeimage ( int i){
        getId = (DatabaseReference) databaseReference.child("object").child(String.valueOf(i)).child("name");
        getImage = (DatabaseReference) databaseReference.child("object").child(String.valueOf(i)).child("image");
        getId.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot) {

                        toSpeak = dataSnapshot.getValue(String.class);
                        //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError) {
                        Toast.makeText(count.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot) {
                        message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                        Picasso.get().load(message).into(img);
                    }

                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError) {
                        Toast.makeText(count.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
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
                    if (toSpeak.equals("ONE"))
                        a1.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("TWO"))
                        a2.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("THREE"))
                        a3.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("FOUR"))
                        a4.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("FIVE"))
                        a5.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("SIX"))
                        a6.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("SEVEN"))
                        a7.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("EIGHT"))
                        a8.setImageResource(R.drawable.arrow);
                    else if (toSpeak.equals("NINE"))
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

    public void onClick(View v) {
        int button = v.getId();
        switch (button){
            case R.id.button_one:
                if(toSpeak.equals("ONE")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    toSpeak="FOUR";
                    changeimage(3);

                }else showWrong();
                arrow(a1);
                break;
            case R.id.button_two:
                if(toSpeak.equals("TWO")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(0);
                    toSpeak="ONE";
                }else showWrong();
                arrow(a2);
                break;
            case R.id.button_three:
                if(toSpeak.equals("THREE")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(5);
                    toSpeak="SIX";

                }else showWrong();
                arrow(a3);
                break;
            case R.id.button_four:
                if(toSpeak.equals("FOUR")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(2);
                    toSpeak="THREE";
                }else showWrong();
                arrow(a4);
                break;
            case R.id.button_five:
                if(toSpeak.equals("FIVE")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(8);
                    toSpeak="NINE";
                }else showWrong();
                arrow(a5);
                break;
            case R.id.button_six:
                if(toSpeak.equals("SIX")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(6);
                    toSpeak="SEVEN";
                }else showWrong();
                arrow(a6);
                break;
            case R.id.button_seven:
                if(toSpeak.equals("SEVEN")) {
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(1);
                    toSpeak="TWO";
                }else showWrong();
                arrow(a7);
                break;
            case R.id.button_eight:
                if(toSpeak.equals("EIGHT")){
                    showCorrect();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(4);
                    toSpeak="FIVE";
                }else showWrong();
                arrow(a8);
                break;
            case R.id.button_nine:
                if(toSpeak.equals("NINE")) {
                    showCorrect();

                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    changeimage(7);
                    toSpeak="EIGHT";
                }else showWrong();
                arrow(a9);
                break;

        }
    }
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