package com.example.amir.dhp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by amir on 2/23/16.
 */
public class FocoMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foco_activity_main);

        // Update the toolbar
        getSupportActionBar().setTitle("FoCo");

        updateTime();

        // Start timer for update
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateTime();
                    }
                });
            }
        }, 0, 60000);

        // Initialize crowded-levels
        TextView [] crowded = new TextView [] {
                (TextView) findViewById(R.id.crowd_1),
                (TextView) findViewById(R.id.crowd_2),
                (TextView) findViewById(R.id.crowd_3),
                (TextView) findViewById(R.id.crowd_4),
                (TextView) findViewById(R.id.crowd_5),
                (TextView) findViewById(R.id.crowd_6),
                (TextView) findViewById(R.id.crowd_7),
                (TextView) findViewById(R.id.crowd_8),
                (TextView) findViewById(R.id.crowd_9),
                (TextView) findViewById(R.id.crowd_10)
        };

        int level = new Random().nextInt(11); // Number 0-10 to be filled (random)
        for (int i = 0; i < 10; i++) {
            if (i + level >= 10)
                crowded[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.focoCrowd));
            else
                crowded[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.focoCrowdEmpty));
        }
    }

    public void focoSearchMenuClicked(View view) {
        Intent i = new Intent(this, FocoMenuActivity.class);
        startActivity(i);
    }

    public void focoNewMealClicked(View view) {
        Intent i = new Intent(this, FocoMealActivity.class);
        startActivity(i);
    }

    public void focoLoadMealClicked(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Foco Meals");
        final String[] types = {"Vegan Meal", "Pizza", "Gym Special"};
        final ArrayList<int []> meals = new ArrayList<>();
        meals.add(new int[] {0, 2, 4});
        meals.add(new int[] {13, 14, 15, 16});
        meals.add(new int[] {17, 20, 21});

        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                Intent i = new Intent(FocoMainActivity.this, FocoMealActivity.class);
                i.putExtra("name", types[which]);
                i.putExtra("items", meals.get(which));
                startActivity(i);
            }

        });

        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        b.show();
    }

    private void updateTime() {
        String [] msgs = Util.getFocoTime();

        ((TextView) findViewById(R.id.foco_main_time_label)).setText(msgs[0]);
        ((TextView) findViewById(R.id.foco_main_time)).setText(msgs[1]);
    }
}
