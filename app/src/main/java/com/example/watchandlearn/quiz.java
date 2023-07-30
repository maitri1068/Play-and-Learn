package com.example.watchandlearn;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Random;

public class quiz extends AppCompatActivity {
    ImageButton a,b,c,d,hint;
    TextToSpeech t1,t2;
    String ans;
    CardView que;
    DatabaseReference getcorrectans,getop_a_name,getop_b_name,getop_c_name,getop_d_name,getop_a_img,getop_b_img,getop_c_img,getop_d_img,getque;
    int minn=0,maxx=9;
    int randomNum;
    Random rand;
    String message;
    int i,flag=0;
    static int j=0;
    String a_name,b_name,c_name,d_name,ques;
    Context context;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        a=(ImageButton)findViewById(R.id.b1);
        b=(ImageButton)findViewById(R.id.b2);
        c=(ImageButton)findViewById(R.id.b3);
        d=(ImageButton)findViewById(R.id.b4);
        que=(CardView) findViewById(R.id.que);
        hint =(ImageButton)findViewById(R.id.hint) ;
        Random rand = new Random();
        randomNum = rand.nextInt((maxx-minn)+1)+minn;

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();
        t2=new TextToSpeech(getApplicationContext(), status -> {
            if(status != TextToSpeech.ERROR) {
                t2.setLanguage(Locale.UK);
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2.speak("press the boy to hear question and then select answer",TextToSpeech.QUEUE_FLUSH, null);
             t2.setSpeechRate(0.7f);
            }
        });
        i = randomNum;
        play();
    }
    public void play(){
//        if(i <=9){
//
//        }else{
//            randomNum = rand.nextInt((maxx-minn)+1)+minn;
//            i=randomNum;
//        }
        que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getque = (DatabaseReference) databaseReference.child("quiz").child(String.valueOf(i)).child("que");
                getque.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)

                            {
                                ques = dataSnapshot.getValue(String.class);
                                t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int status) {
                                        if(status != TextToSpeech.ERROR) {
                                            t1.setLanguage(Locale.UK);
                                            t1.speak(ques, TextToSpeech.QUEUE_FLUSH, null);
                                            flag=1;
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(quiz.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

            }
        });
        getcorrectans=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("correct_ans");

        getop_a_img=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_a_img");
        getop_b_img=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_b_img");
        getop_c_img=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_c_img");
        getop_d_img=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_d_img");
        getop_a_name=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_a_name");
        getop_b_name=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_b_name");
        getop_c_name=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_c_name");
        getop_d_name=(DatabaseReference)databaseReference.child("quiz").child(String.valueOf(i)).child("op_d_name");



        getcorrectans.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)

                    {
                        ans = dataSnapshot.getValue(String.class);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_a_img.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
                        Picasso.get().load(message).into(a);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_b_img.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
                        Picasso.get().load(message).into(b);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_c_img.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
                        Picasso.get().load(message).into(c);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_d_img.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
                        Picasso.get().load(message).into(d);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_a_name.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        a_name = dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_b_name.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        b_name= dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_c_name.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        c_name = dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getop_d_name.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        d_name = dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(quiz.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
    public void correctans(){

        if(i==9)
        {i=0;}else i++;
        Toast.makeText(this,"true ans",Toast.LENGTH_SHORT).show();
        j=j+2;
        count++;
        if(count == 9){
            Intent i = new Intent(quiz.this,score.class);
            i.putExtra("score",String.valueOf(j));
            startActivity(i);

        }

    }
    public void wrongans(){
        Toast.makeText(this,"wrong ans",Toast.LENGTH_SHORT).show();
        j=j-1;
    }
    public void rmbg(){
        a.setBackgroundColor(Color.parseColor("white"));
        b.setBackgroundColor(Color.parseColor("white"));
        c.setBackgroundColor(Color.parseColor("white"));
        d.setBackgroundColor(Color.parseColor("white"));
    }
    public void delay(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                rmbg();
                play();
            }
        };
        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable,500);
    }
    public void opA(View view){
        if(flag == 1){
            rmbg();
            if(a_name.equals(ans)){
                correctans();
                flag=0;
                a.setBackgroundColor(Color.parseColor("green"));
                delay();
            }else{
                wrongans();
                a.setBackgroundColor(Color.parseColor("red"));
            }

        }

    }
    public void opB(View view){
        if(flag == 1){
            rmbg();
            if(b_name.equals(ans)){
                correctans();
                b.setBackgroundColor(Color.parseColor("green"));
                delay();
//                play();
            }else{
                wrongans();
                b.setBackgroundColor(Color.parseColor("red"));
            }

        }

    }
    public void opC(View view){
        if(flag == 1){
            rmbg();
            if(c_name.equals(ans)){
                correctans();
                c.setBackgroundColor(Color.parseColor("green"));
                delay();
//                play();
            }else{
                wrongans();
                c.setBackgroundColor(Color.parseColor("red"));
            }

        }
    }
    public void opD(View view){
        if(flag == 1){
            rmbg();
            if(d_name.equals(ans)){
                correctans();
                d.setBackgroundColor(Color.parseColor("green"));
                delay();

            }else{
                wrongans();
                d.setBackgroundColor(Color.parseColor("red"));
            }

        }
    }
}