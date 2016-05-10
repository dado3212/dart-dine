package com.example.amir.dhp;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by amir on 2/23/16.
 */
public class FocoMenuActivity extends AppCompatActivity {
    private MenuListAdapter adapter;
    private ArrayList<MenuItem> menuItems;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foco_activity_menu);

        // Set up toolbar
        getSupportActionBar().setTitle("FoCo Menu");

        // Set up list adapter
        searchBar = (EditText) findViewById(R.id.foco_menu_search);

        menuItems = Util.getFocoMenu();

        adapter = new MenuListAdapter(this, menuItems);
        ListView listView = (ListView) findViewById(R.id.food_item_list);
        listView.setAdapter(adapter);

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
                Intent i = new Intent(FocoMenuActivity.this, MenuItemDisplayActivity.class);
                i.putExtra("MenuItem", adapter.getItem(position));
                startActivity(i);
            }
        });

        // Toggle buttons
        ((Button) findViewById(R.id.foco_menu_gluten)).getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        ((Button) findViewById(R.id.foco_menu_gluten)).setOnClickListener(new View.OnClickListener() {
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

        ((Button) findViewById(R.id.foco_menu_kosher)).getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        ((Button) findViewById(R.id.foco_menu_kosher)).setOnClickListener(new View.OnClickListener() {
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

        ((Button) findViewById(R.id.foco_menu_vegan)).getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        ((Button) findViewById(R.id.foco_menu_vegan)).setOnClickListener(new View.OnClickListener() {
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
    }

    private class MenuListAdapter extends ArrayAdapter<MenuItem> implements Filterable {
        private ArrayList<MenuItem> items;
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            MenuItem item = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.food_item_name);
            ImageView gluten = (ImageView) convertView.findViewById(R.id.gluten);
            ImageView kosher = (ImageView) convertView.findViewById(R.id.kosher);
            ImageView vegan = (ImageView) convertView.findViewById(R.id.vegan);
            // Populate the data into the template view using the data object
            name.setText(item.name);
            if (item.glutenFree)
                gluten.setVisibility(View.VISIBLE);
            else
                gluten.setVisibility(View.GONE);
            if (item.kosher)
                kosher.setVisibility(View.VISIBLE);
            else
                kosher.setVisibility(View.GONE);
            if (item.vegan)
                vegan.setVisibility(View.VISIBLE);
            else
                vegan.setVisibility(View.GONE);
            // Return the completed view to render on screen
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
