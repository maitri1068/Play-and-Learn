package com.example.watchandlearn;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class alphabet_learn extends AppCompatActivity {
    TextToSpeech t1;
    TextView display_name;

    ImageButton next,prev,play;
    ImageView logoimg,rimg;
    Context context;
    DatabaseReference  getname,getlogoimg,getrimg,getrname,databaseReference;
    int i;
    String count;
    String message,r_name;
    String toSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_learn);
        logoimg=(ImageView)findViewById(R.id.logo_img);
        rimg=(ImageView)findViewById(R.id.r_img);
        context=this;
        next= (ImageButton) findViewById(R.id.next);
        play= (ImageButton) findViewById(R.id.play);
        prev= (ImageButton) findViewById(R.id.prev);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.setSpeechRate(0.7f);
                    t1.speak(" A for Apple ", TextToSpeech.QUEUE_FLUSH, null);
                    display_name=(TextView)findViewById(R.id.display);
                    display_name.setText("Apple");
                }
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();



        i = 0;
//        getImage = (DatabaseReference) databaseReference.child("alphabets").child("0").child("logo_img");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i<=25){
                    play();
                }
                else{
                    i=25;
                    finish();
                }
            }

        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }

        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i--;
                if(i >=0){
                    play();
                }
                else{
                    i=0;

                }
            }


        });

    }

    public void play() {
        getrname=(DatabaseReference)databaseReference.child("alphabets").child(String.valueOf(i)).child("r_name");
        getrimg=(DatabaseReference)databaseReference.child("alphabets").child(String.valueOf(i)).child("r_img");
        getname=(DatabaseReference)databaseReference.child("alphabets").child(String.valueOf(i)).child("id");
        getlogoimg = (DatabaseReference) databaseReference.child("alphabets").child(String.valueOf(i)).child("logo_img");
        getrname.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        r_name = dataSnapshot.getValue(String.class);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getname.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)

                    {

                        toSpeak = dataSnapshot.getValue(String.class);
//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        t1.speak(toSpeak+" for "+r_name, TextToSpeech.QUEUE_FLUSH, null);
                        display_name=(TextView)findViewById(R.id.display);
                        display_name.setText(r_name);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        getlogoimg.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                        Picasso.get().load(message).into(logoimg);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        getrimg.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)
                    {
                        message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                        Picasso.get().load(message).into(rimg);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });


    }
    public void logoimg(View v){
        getname=(DatabaseReference)databaseReference.child("alphabets").child(String.valueOf(i)).child("id");
        getname.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)

                    {

                        toSpeak = dataSnapshot.getValue(String.class);
//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        display_name=(TextView)findViewById(R.id.display);
                        display_name.setText(r_name);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }
    public void rimg(View v){
        getname=(DatabaseReference)databaseReference.child("alphabets").child(String.valueOf(i)).child("r_name");
        getname.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot)

                    {

                        r_name = dataSnapshot.getValue(String.class);
//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        t1.speak(r_name, TextToSpeech.QUEUE_FLUSH, null);
//                        display_name=(TextView)findViewById(R.id.display);
//                        display_name.setText(r_name);
                    }
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(alphabet_learn.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }
}