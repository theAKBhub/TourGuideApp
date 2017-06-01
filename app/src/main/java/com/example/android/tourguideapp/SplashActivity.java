package com.example.android.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {

            /** Display splash screen with a timer */
            @Override
            public void run() {
                // This method executes once the timer is over
                startActivity(new Intent(SplashActivity.this, MainActivity.class));

                // Close splash activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
