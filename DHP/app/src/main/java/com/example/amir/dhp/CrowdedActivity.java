package com.example.amir.dhp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alexbeals on 2/25/16.
 */
public class CrowdedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crowded_activity);

        // Update the toolbar
        getSupportActionBar().setTitle("Crowd Levels");

        // Update all times, and initialize counter to count times
        updateTimes();

        // Start timer for update
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateTimes();
                    }
                });
            }
        }, 0, 60000);

        // Initialize crowded-levels (Foco)
        TextView [] crowdedFoco = new TextView [] {
                (TextView) findViewById(R.id.foco_crowd_1),
                (TextView) findViewById(R.id.foco_crowd_2),
                (TextView) findViewById(R.id.foco_crowd_3),
                (TextView) findViewById(R.id.foco_crowd_4),
                (TextView) findViewById(R.id.foco_crowd_5),
                (TextView) findViewById(R.id.foco_crowd_6),
                (TextView) findViewById(R.id.foco_crowd_7),
                (TextView) findViewById(R.id.foco_crowd_8),
                (TextView) findViewById(R.id.foco_crowd_9),
                (TextView) findViewById(R.id.foco_crowd_10)
        };

        int focoLevel = new Random().nextInt(11); // Number 0-10 to be filled (random)
        for (int i = 0; i < 10; i++) {
            if (i + focoLevel >= 10)
                crowdedFoco[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.focoCrowd));
            else
                crowdedFoco[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.focoCrowdEmpty));
        }

        // Initialize crowded-levels (Collis)
        TextView [] crowdedCollis = new TextView [] {
                (TextView) findViewById(R.id.collis_crowd_1),
                (TextView) findViewById(R.id.collis_crowd_2),
                (TextView) findViewById(R.id.collis_crowd_3),
                (TextView) findViewById(R.id.collis_crowd_4),
                (TextView) findViewById(R.id.collis_crowd_5),
                (TextView) findViewById(R.id.collis_crowd_6),
                (TextView) findViewById(R.id.collis_crowd_7),
                (TextView) findViewById(R.id.collis_crowd_8),
                (TextView) findViewById(R.id.collis_crowd_9),
                (TextView) findViewById(R.id.collis_crowd_10)
        };

        int collisLevel = new Random().nextInt(11); // Number 0-10 to be filled (random)
        for (int i = 0; i < 10; i++) {
            if (i + collisLevel >= 10)
                crowdedCollis[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.collisCrowd));
            else
                crowdedCollis[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.collisCrowdEmpty));
        }

        // Initialize crowded-levels (Hop)
        TextView [] crowdedHop = new TextView [] {
                (TextView) findViewById(R.id.hop_crowd_1),
                (TextView) findViewById(R.id.hop_crowd_2),
                (TextView) findViewById(R.id.hop_crowd_3),
                (TextView) findViewById(R.id.hop_crowd_4),
                (TextView) findViewById(R.id.hop_crowd_5),
                (TextView) findViewById(R.id.hop_crowd_6),
                (TextView) findViewById(R.id.hop_crowd_7),
                (TextView) findViewById(R.id.hop_crowd_8),
                (TextView) findViewById(R.id.hop_crowd_9),
                (TextView) findViewById(R.id.hop_crowd_10)
        };

        int hopLevel = new Random().nextInt(11); // Number 0-10 to be filled (random)
        for (int i = 0; i < 10; i++) {
            if (i + hopLevel >= 10)
                crowdedHop[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.hopCrowd));
            else
                crowdedHop[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.hopCrowdEmpty));
        }
    }

    private void updateTimes() {
        String [] focoMsgs = Util.getFocoTime();
        ((TextView) findViewById(R.id.foco_details)).setText(focoMsgs[1]);

        String [] collisMsgs = Util.getCollisTime();
        ((TextView) findViewById(R.id.collis_details)).setText(collisMsgs[1]);

        String [] hopMsgs = Util.getHopTime();
        ((TextView) findViewById(R.id.hop_details)).setText(hopMsgs[1]);
    }
}
