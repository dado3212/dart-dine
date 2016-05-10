package com.example.amir.dhp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Retrieve image views, and link listeners
        ImageView collis = (ImageView) findViewById(R.id.collis);
        final Intent collisIntent = new Intent(this, CollisMainActivity.class);
        collis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(collisIntent);
            }
        });

        ImageView foco = (ImageView) findViewById(R.id.foco);
        final Intent focoIntent = new Intent(this, FocoMainActivity.class);
        foco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(focoIntent);
            }
        });

        ImageView hop = (ImageView) findViewById(R.id.hop);
        final Intent hopIntent = new Intent(this, HopMainActivity.class);
        hop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(hopIntent);
            }
        });

        // Set on click listener to launch
        ((Button) findViewById(R.id.crowd_levels)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CrowdedActivity.class);
                startActivity(i);
            }
        });
    }
}
