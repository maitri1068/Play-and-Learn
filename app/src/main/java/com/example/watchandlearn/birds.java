package com.example.watchandlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class birds extends AppCompatActivity {

    TextToSpeech t1;
    TextView display_name;
    ImageButton next,prev,play;
    ImageView img;
    Context context;
    DatabaseReference  getImage,getId;
    int i;
    String count;
    String message;
    String toSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds);
        img=(ImageView)findViewById(R.id.logoimg);
        context=this;
        next= (ImageButton) findViewById(R.id.next);
        play= (ImageButton) findViewById(R.id.play);
        prev= (ImageButton) findViewById(R.id.prev);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.speak("Parrot", TextToSpeech.QUEUE_FLUSH, null);
                    display_name=(TextView)findViewById(R.id.name);
                    display_name.setText("Parrot");
                }
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();


        i = 0;
//        getImage = (DatabaseReference) databaseReference.child("alphabets").child("0").child("logo_img");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i > 10){
                    i=0;
                }
                getId=(DatabaseReference)databaseReference.child("birds").child(String.valueOf(i)).child("name");
                getImage = (DatabaseReference) databaseReference.child("birds").child(String.valueOf(i)).child("logo_img");
                getId.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)

                            {

                                toSpeak = dataSnapshot.getValue(String.class);
//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                                display_name=(TextView)findViewById(R.id.name);
                                display_name.setText(toSpeak);
                            }
                            @Override
                            public void onCancelled(

                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(birds.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                getImage.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                                Picasso.get().load(message).into(img);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(birds.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });


            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getId=(DatabaseReference)databaseReference.child("birds").child(String.valueOf(i)).child("name");
                getId.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)

                            {

                                toSpeak = dataSnapshot.getValue(String.class);
//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(birds.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i--;
                if(i < 0){
                    i=10;
                }
                getId=(DatabaseReference)databaseReference.child("birds").child(String.valueOf(i)).child("name");
                getImage = (DatabaseReference) databaseReference.child("birds").child(String.valueOf(i)).child("logo_img");
                getId.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                String toSpeak = dataSnapshot.getValue(String.class);

//                                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                                display_name=(TextView)findViewById(R.id.name);
                                display_name.setText(toSpeak);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {}
                        });
                getImage.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                message = dataSnapshot.getValue(String.class);
                                Picasso.get().load(message).into(img);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {}
                        });

            }
        });




    }
}