package com.example.android.tourguideapp;

/**
 * Created by aditibhattacharya on 03/06/2017.
 */

public class Weather {

    /** Time of Weather which holds the date (in milliseconds) */
    public final long[] mTime = new long[5];

    /** Temperature on that date */
    public final double[] mTemperature = new double[5];

    /** Weather Condition */
    public final String[] mWeatherCondition = new String[5];

    /**
     * Constructs a new {@link Weather}
     * @param time
     * @param temperature
     * @param weatherCondition
     */
    public Weather (long time[], double temperature[], String weatherCondition[]) {
        for (int i=0; i < 5; i++) {
            mTime[i] = time[i];
            mTemperature[i] = temperature[i];
            mWeatherCondition[i] = weatherCondition[i];
        }
    }

}
