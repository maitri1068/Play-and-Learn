package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.danny_jiang.tracinglibrary.view.TracingLetterView;

public class trace extends AppCompatActivity {

    private TracingLetterView letterView;
    private TracingLetterView letterView1;
    ImageButton next,prev,retry;
    int i =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        retry = findViewById(R.id.retry);

        letterView = findViewById(R.id.letter);
        letterView.setLetterChar(i);
        letterView1 = findViewById(R.id.letter_lower);
        letterView1.setLetterChar(i+26);
        next.setOnClickListener(view -> {
            i++;
            if(i>26)
            { i=1;}
            //view.requestLayout();
            letterView.clearTracingPaths();
            letterView1.clearTracingPaths();
            letterView.setLetterChar(i);
            letterView1.setLetterChar(i+26);

            letterView.invalidate();
            letterView1.invalidate();



        });
        prev.setOnClickListener(view -> {

            i--;
            if(i<1)
            { i=26;}
           // view.requestLayout();


            letterView.setLetterChar(i);
            letterView1.setLetterChar(i+26);
            letterView.clearTracingPaths();
            letterView1.clearTracingPaths();
            letterView.invalidate();
            letterView1.invalidate();



        });
        retry.setOnClickListener(view -> {
           // view.requestLayout();
            letterView.clearTracingPaths();
            letterView1.clearTracingPaths();
            letterView.setLetterChar(i);
            letterView1.setLetterChar(i+26);

            letterView.invalidate();
            letterView1.invalidate();

        });
        letterView.setPointColor(Color.BLUE);
        letterView.setInstructMode(true);
        letterView1.setPointColor(Color.BLUE);
        letterView1.setInstructMode(true);

        letterView.setTracingListener(new TracingLetterView.TracingListener() {
            @Override
            public void onFinish() {
                Toast.makeText(trace.this,
                        "tracing finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTracing(float x, float y) {
                Log.e("ABC", "tracing x : " + x + " y : " + y);
            }
        });
        letterView1.setTracingListener(new TracingLetterView.TracingListener() {
            @Override
            public void onFinish() {
                Toast.makeText(trace.this,
                        "tracing finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTracing(float x, float y) {
                Log.e("ABC", "tracing x : " + x + " y : " + y);
            }
        });

    }


}