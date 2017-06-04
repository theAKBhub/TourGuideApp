package com.example.android.tourguideapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import helper.DatabaseHelper;
import model.City;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** All UI components */
    private TextView mTextLondon;
    private TextView mTextParis;
    private TextView mTextRome;
    private TextView mTextNewYork;

    /** Various identifiers */
    private Typeface mCustomFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        // Initialize UI components
        mTextLondon = (TextView) findViewById(R.id.text_select_london);
        mTextParis = (TextView) findViewById(R.id.text_select_paris);
        mTextRome = (TextView) findViewById(R.id.text_select_rome);
        mTextNewYork = (TextView) findViewById(R.id.text_select_newyork);

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getAssets(), "fonts/opensans_semibold.ttf");
        setCustomTypeface();

        // Set OnClickListeners on clickable TextViews
        mTextLondon.setOnClickListener(this);
        mTextParis.setOnClickListener(this);
        mTextRome.setOnClickListener(this);
        mTextNewYork.setOnClickListener(this);

        // Create and populate tables if they do not exist
        insertCitiesRecords();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method sets custom font for all views
     */
    public void setCustomTypeface() {
        mTextLondon.setTypeface(mCustomFont);
        mTextParis.setTypeface(mCustomFont);
        mTextRome.setTypeface(mCustomFont);
        mTextNewYork.setTypeface(mCustomFont);
    }

    /**
     * This method invokes methods for clicked items
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_select_london:
                openTourActivity(getString(R.string.label_london));
                break;
            case R.id.text_select_paris:
                openTourActivity(getString(R.string.label_paris));
                break;
            case R.id.text_select_rome:
                openTourActivity(getString(R.string.label_rome));
                break;
            case R.id.text_select_newyork:
                openTourActivity(getString(R.string.label_newyork));
                break;
            default:
                openTourActivity(getString(R.string.label_london));
                break;
        }
    }

    /**
     * This method invokes TourActivity with content for selected city
     * @param selectedCity
     */
    public void openTourActivity(String selectedCity) {
        Intent intent = new Intent(MainActivity.this, TourActivity.class);
        intent.putExtra("city", selectedCity);
        startActivity(intent);
    }

    /**
     * This method creates and populates Cities table
     */
    public void insertCitiesRecords() {
        int [] imageResources = {R.drawable.photo_london, R.drawable.photo_rome, R.drawable.photo_paris, R.drawable.photo_newyork};
        String [] record = new String[7];
        String [] records = getResources().getStringArray(R.array.cities_array);
        int rowCount = 0;

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
//  db.deleteCities();
        rowCount = db.getCitiesRowsCount();

        if (rowCount == 0) {
            for (int i = 0; i < records.length; i++) {
                record = records[i].split("\\|");

                db.createCity(new City(Integer.parseInt(record[0]), record[1], record[2],
                        record[3], record[4], record[5], record[6], record[7], record[8],
                        imageResources[i]
                ));
            }
        }

    }
}
