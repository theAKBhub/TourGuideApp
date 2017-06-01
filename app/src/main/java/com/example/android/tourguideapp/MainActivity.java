package com.example.android.tourguideapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


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
}
