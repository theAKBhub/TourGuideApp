package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class TourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String selectedCity = "";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        // Get Intent Extras
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            selectedCity = bundle.getString("city");
        }

        // Change Actionbar Label based on selected city
        getSupportActionBar().setTitle(getSupportActionBar().getTitle() + " : " + selectedCity);

        // Set up Tab Layout and ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                TourActivity.this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}
