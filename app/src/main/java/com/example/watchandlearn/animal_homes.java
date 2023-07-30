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

public class animal_homes extends AppCompatActivity {

    TextToSpeech t1;
    TextView display_name;
    ImageButton next,prev,play;
    ImageView logoimg,homeimg;
    Context context;
    DatabaseReference getname,getlogoimg,gethomeimg,gethomename;
    int i;
    String count;
    String message,home_name;
    String toSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_homes);
        logoimg=(ImageView)findViewById(R.id.logo_img);
        homeimg=(ImageView)findViewById(R.id.home_img);
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
                    t1.speak("Dog lives in kennel", TextToSpeech.QUEUE_FLUSH, null);
                    display_name=(TextView)findViewById(R.id.display);
                    display_name.setText("Kennel");
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
                if(i > 6){
                    i=0;
                }
                gethomename=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("home_name");
                gethomeimg=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("home_img");
                getname=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("name");
                getlogoimg = (DatabaseReference) databaseReference.child("animals_home").child(String.valueOf(i)).child("logo_img");
                gethomename.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                home_name = dataSnapshot.getValue(String.class);
//                                Toast.makeText(MainActivity.this,
//                                                home_name,
//                                                Toast.LENGTH_SHORT)
//                                        .show();
//                                CircularArray<Object> Picasso;
//                                Picasso.get().load(message).into(img);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                                t1.speak(toSpeak+"lives in "+home_name, TextToSpeech.QUEUE_FLUSH, null);
                                display_name=(TextView)findViewById(R.id.display);
                                display_name.setText(home_name);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                                Toast.makeText(animal_homes.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

                gethomeimg.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                                Picasso.get().load(message).into(homeimg);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                gethomename=databaseReference.child("animals_home").child(String.valueOf(i)).child("home_name");
                getname=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("name");
                gethomename.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                home_name = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
//                                Picasso.get().load(message).into(img);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                                t1.speak(toSpeak+" lives in "+home_name, TextToSpeech.QUEUE_FLUSH, null);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                    i=6;
                }
                gethomename=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("home_name");
                gethomeimg=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("home_img");
                getname=(DatabaseReference)databaseReference.child("animals_home").child(String.valueOf(i)).child("name");
                getlogoimg = (DatabaseReference) databaseReference.child("animals" +
                        "_home").child(String.valueOf(i)).child("logo_img");
                gethomename.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                home_name = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
//                                Picasso.get().load(message).into(img);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                                t1.speak(toSpeak+"lives in "+home_name, TextToSpeech.QUEUE_FLUSH, null);
                                display_name=(TextView)findViewById(R.id.display);
                                display_name.setText(home_name);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
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
                                Toast.makeText(animal_homes.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

                gethomeimg.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(
                                    @NonNull DataSnapshot dataSnapshot)
                            {
                                message = dataSnapshot.getValue(String.class);
//                                CircularArray<Object> Picasso;
                                Picasso.get().load(message).into(homeimg);
                            }
                            @Override
                            public void onCancelled(
                                    @NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(animal_homes.this,
                                                "Error Loading Image",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });


            }
        });




    }
}