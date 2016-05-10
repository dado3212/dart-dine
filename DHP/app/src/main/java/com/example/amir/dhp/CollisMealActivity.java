package com.example.amir.dhp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by amir on 2/23/16.
 */
public class CollisMealActivity extends AppCompatActivity {
    private MenuListAdapter adapter;
    private ArrayList<MenuItem> menuItems;

    private TextView totalItems;
    private TextView calories;
    private TextView fat;
    private TextView satFat;
    private TextView protein;
    private TextView sodium;
    private TextView potassium;

    private EditText mealName;
    private EditText searchBar;

    private Button gluten;
    private Button kosher;
    private Button vegan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collis_activity_meal);

        // Set up toolbar
        getSupportActionBar().setTitle("Collis Meal Creation");

        // Set up all TextViews
        totalItems = (TextView) findViewById(R.id.collis_meal_items_count);
        calories = (TextView) findViewById(R.id.collis_meal_calories);
        fat = (TextView) findViewById(R.id.collis_meal_total_fat);
        satFat = (TextView) findViewById(R.id.collis_meal_sat_fat);
        protein = (TextView) findViewById(R.id.collis_meal_protein);
        sodium = (TextView) findViewById(R.id.collis_meal_sodium);
        potassium = (TextView) findViewById(R.id.collis_meal_potassium);

        mealName = (EditText) findViewById(R.id.collis_meal_name);

        searchBar = (EditText) findViewById(R.id.collis_meal_search);

        vegan = ((Button) findViewById(R.id.collis_meal_vegan));
        kosher = ((Button) findViewById(R.id.collis_meal_kosher));
        gluten = ((Button) findViewById(R.id.collis_meal_gluten));

        // Set up list adapter
        menuItems = Util.getCollisMenu();
        ListView listView = (ListView) findViewById(R.id.food_item_list);

        // Check if loading
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("name") && getIntent().getExtras().containsKey("items")) {
            String name = getIntent().getExtras().getString("name");
            int [] items = getIntent().getExtras().getIntArray("items");

            mealName.setText(name);
            for (int i = 0; i < items.length; i++) {
                menuItems.get(items[i]).selected = true;
            }

            adapter = new MenuListAdapter(this, menuItems);

            listView.setAdapter(adapter);

            updateMealDetails();
        } else {
            adapter = new MenuListAdapter(this, menuItems);
            listView.setAdapter(adapter);
        }

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.getMainItem(position).selected ^= true;
                adapter.getFilter().filter(searchBar.getText().toString());

                if (adapter.getItem(position).selected) {
                    view.setBackgroundColor(0x332B9927);
                } else {
                    view.setBackgroundColor(0x00000000);
                }

                updateMealDetails();
            }
        });

        // Toggle buttons
        gluten.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        gluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.glutenOnly ^= true;
                adapter.getFilter().filter(searchBar.getText().toString());
                if (adapter.glutenOnly)
                    v.getBackground().setColorFilter(0x332B9927, PorterDuff.Mode.MULTIPLY);
                else
                    v.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
            }
        });

        kosher.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        kosher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.kosherOnly ^= true;
                adapter.getFilter().filter(searchBar.getText().toString());
                if (adapter.kosherOnly)
                    v.getBackground().setColorFilter(0x332B9927, PorterDuff.Mode.MULTIPLY);
                else
                    v.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
            }
        });

        vegan.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        vegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.veganOnly ^= true;
                adapter.getFilter().filter(searchBar.getText().toString());
                if (adapter.veganOnly)
                    v.getBackground().setColorFilter(0x332B9927, PorterDuff.Mode.MULTIPLY);
                else
                    v.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
            }
        });

        // Generate a meal buttons (currently random, and cheat)
        ((Button) findViewById(R.id.collis_2000)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset any searching / selecting
                adapter.unselectAll();
                adapter.getFilter().filter("");
                // Get count of available options
                int count = adapter.getCount();

                // Generate 3 random indices, select them, and fudge the numbers
                ArrayList<Integer> indexes = Util.pickOptions(count);

                for (Integer index : indexes) {
                    adapter.getMainItem(index).selected = true;
                }

                adapter.getFilter().filter("");
                updateMealDetails();
                calories.setText("Calories: 2000 cal");
            }
        });

        ((Button) findViewById(R.id.collis_2500)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset any searching / selecting
                adapter.unselectAll();
                adapter.getFilter().filter("");
                // Get count of available options
                int count = adapter.getCount();

                // Generate 3 random indices, select them, and fudge the numbers
                ArrayList<Integer> indexes = Util.pickOptions(count);

                for (Integer index : indexes) {
                    adapter.getMainItem(index).selected = true;
                }

                adapter.getFilter().filter("");
                updateMealDetails();
                calories.setText("Calories: 2500");
            }
        });

        ((Button) findViewById(R.id.collis_3000)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset any searching / selecting
                adapter.unselectAll();
                adapter.getFilter().filter("");
                // Get count of available options
                int count = adapter.getCount();

                // Generate 3 random indices, select them, and fudge the numbers
                ArrayList<Integer> indexes = Util.pickOptions(count);

                for (Integer index : indexes) {
                    adapter.getMainItem(index).selected = true;
                }

                adapter.getFilter().filter("");
                updateMealDetails();
                calories.setText("Calories: 3000");
            }
        });
    }

    private void updateMealDetails() {
        int count = 0;
        int calories = 0;
        int fat = 0;
        int satFat = 0;
        int protein = 0;
        int sodium = 0;
        int potassium = 0;

        for (MenuItem item : adapter.items) {
            if (item.selected) {
                count += 1;
                calories += item.calories;
                fat += item.totalFat;
                satFat += item.saturatedFat;
                protein += item.protein;
                sodium += item.sodium;
                potassium += item.potassium;
            }
        }

        this.totalItems.setText("Total Items: " + count);
        this.calories.setText("Calories: " + calories + " cal");
        this.fat.setText("Total Fat: " + fat + "g");
        this.satFat.setText("Saturated Fat: " + satFat + "g");
        this.protein.setText("Protein: " + protein + "g");
        this.sodium.setText("Sodium: " + sodium + "mg");
        this.potassium.setText("Potassium: " + potassium + "mg");
    }

    public void collisSaveMealClicked(View view) {
        String name = mealName.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "Name your meal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Meal '" + name + "' has been saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Full reset
    public void collisClearMealClicked(View view) {
        adapter.unselectAll();
        adapter.glutenOnly = false;
        adapter.kosherOnly = false;
        adapter.veganOnly = false;

        gluten.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        kosher.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        vegan.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);

        adapter.getFilter().filter("");
    }

    private class MenuListAdapter extends ArrayAdapter<MenuItem> implements Filterable {
        public ArrayList<MenuItem> items;
        private ArrayList<MenuItem> filtered;

        public boolean glutenOnly = false;
        public boolean kosherOnly = false;
        public boolean veganOnly = false;

        public MenuListAdapter(Context context, ArrayList<MenuItem> items) {
            super(context, 0, items);
            this.items = items;
            this.filtered = items;
        }

        @Override
        public int getCount() {
            return filtered == null ? 0 : filtered.size();
        }

        @Override
        public MenuItem getItem(int position) {
            return filtered == null ? null : filtered.get(position);
        }

        public MenuItem getMainItem(int position) {
            return items.get(items.indexOf(filtered.get(position)));
        }

        public void unselectAll() {
            for (MenuItem item : items)
                item.selected = false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MenuItem item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
            }
            // Name change
            TextView name = (TextView) convertView.findViewById(R.id.food_item_name);
            name.setText(item.name);

            // Color change
            if (item.selected) {
                convertView.setBackgroundColor(0x332B9927);
            } else {
                convertView.setBackgroundColor(0x00000000);
            }

            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    ArrayList<MenuItem> filter = new ArrayList<>();
                    for (MenuItem item : items) {
                        if (item.name.toLowerCase().contains(constraint.toString().toLowerCase())) { // text match
                            if ((item.glutenFree || !glutenOnly) && (item.kosher || !kosherOnly) && (item.vegan || !veganOnly)) {
                                filter.add(item);
                            }
                        }
                    }
                    filtered = filter;
                    filterResults.values = filter;
                    filterResults.count = filter.size();
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    notifyDataSetChanged();
                }
            };
        }
    }
}
