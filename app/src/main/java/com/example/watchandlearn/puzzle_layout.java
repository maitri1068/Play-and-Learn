package com.example.watchandlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;

public class puzzle_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_layout);
        AssetManager am = getAssets();
        try {
            final String[] files  = am.list("img");

            GridView grid = findViewById(R.id.grid);
            grid.setAdapter(new ImageAdapter(this));
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), puzzle.class);
                    intent.putExtra("assetName", files[i % files.length]);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
        }
    }

}
