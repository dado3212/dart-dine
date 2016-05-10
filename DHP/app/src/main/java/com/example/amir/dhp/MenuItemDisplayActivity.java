package com.example.amir.dhp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alexbeals on 2/25/16.
 */
public class MenuItemDisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_display);

        Bundle extras = getIntent().getExtras();
        MenuItem item = extras.getParcelable("MenuItem");
        if (item == null)
            finish();

        // Set up toolbar
        getSupportActionBar().setTitle(item.name);

        // Set up all variables
        ((TextView) findViewById(R.id.menu_item_calories)).setText(item.calories + " cal");
        ((TextView) findViewById(R.id.menu_item_fat)).setText(item.totalFat + "g");
        ((TextView) findViewById(R.id.menu_item_sat_fat)).setText(item.saturatedFat + "g");
        ((TextView) findViewById(R.id.menu_item_sodium)).setText(item.sodium + "mg");
        ((TextView) findViewById(R.id.menu_item_protein)).setText(item.protein + "g");
        ((TextView) findViewById(R.id.menu_item_potassium)).setText(item.potassium + "mg");

        // Set up images
        if (item.vegan)
            ((ImageView) findViewById(R.id.vegan)).setVisibility(View.VISIBLE);
        if (item.kosher)
            ((ImageView) findViewById(R.id.kosher)).setVisibility(View.VISIBLE);
        if (item.glutenFree)
            ((ImageView) findViewById(R.id.gluten)).setVisibility(View.VISIBLE);
    }
}
