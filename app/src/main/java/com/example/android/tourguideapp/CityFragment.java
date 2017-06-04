package com.example.android.tourguideapp;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import helper.DatabaseHelper;
import model.City;

/**
 * Created by aditibhattacharya on 01/06/2017.
 */

public class CityFragment extends Fragment {

    private String LOG_TAG = "CityFragment";

    private View view;
    private String mSelectedCity;
    private Typeface mCustomFont;

    // Variables for City
    private String mCountry;
    private String mLanguage;
    private String mAirport;
    private String mTransport;
    private String mAboutCity;
    private int mCityImage;
    private String mCityTimeZone;
    private String mCityWeatherZone;

    // UI identifiers
    private ImageView mImageViewCity;
    private TextView mTextViewCountry;
    private TextView mTextViewLanguage;
    private TextView mTextViewAirport;
    private TextView mTextViewTransport;
    private TextView mTextViewWeather;
    private TextClock mTextClockTime;
    private TextView mTextViewAbout;

    // OpenWeather Variables
    private String WEATHER_REQUEST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&units=%s&appid=%s";
    private String WEATHER_METRICS = "metric";
    private String WEATHER_LIST_ARRAY = "list";
    private String WEATHER_DATETIME = "dt";
    private String WEATHER_TEMP_OBJECT = "temp";
    private String WEATHER_DAY_TEMP = "day";
    private String WEATHER_WTHR_OBJECT = "weather";
    private String WEATHER_CONDITION = "main";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get Intent Extras
        if (getActivity().getIntent().getExtras() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            mSelectedCity = bundle.getString("city");
        }

        // Extract City Details from Database
        extractCityDetails();

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/opensans_regular.ttf");

        // Inflate view object
        view = inflater.inflate(R.layout.fragment_city, container, false);

        // Initialize UI objects
        mImageViewCity = (ImageView) view.findViewById(R.id.image_city);
        mTextViewCountry = (TextView) view.findViewById(R.id.text_country);
        mTextViewLanguage = (TextView) view.findViewById(R.id.text_language);
        mTextViewAirport = (TextView) view.findViewById(R.id.text_airport);
        mTextViewTransport = (TextView) view.findViewById(R.id.text_transport);
        mTextViewWeather = (TextView) view.findViewById(R.id.text_panel_weather);
        mTextClockTime = (TextClock) view.findViewById(R.id.textclock_localtime);
        mTextViewAbout = (TextView) view.findViewById(R.id.text_panel_about);

        // Invoke {@link AsyncTask} to perform the network request
        WeatherAsyncTask task = new WeatherAsyncTask();
        task.execute();

        // Display city data
        displayCityRecord();

        return view;
    }

    /**
     * This method retrieves data from City table for specified city
     */
    public void extractCityDetails() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        List<City> cityRecord = databaseHelper.readCityRecord(mSelectedCity);

        for (City ct : cityRecord) {
            mCountry = ct.getCountry().trim();
            mLanguage = ct.getLanguage().trim();
            mAirport = ct.getAirport().trim();
            mTransport = ct.getTransport().trim();
            mAboutCity = ct.getDescription().trim();
            mCityImage = ct.getCityImage();
            mCityTimeZone = ct.getCityTimeZone().trim();
            mCityWeatherZone = ct.getCityWeatherZone().trim();
        }
    }

    private class WeatherAsyncTask extends AsyncTask<URL, Void, Weather> {
        @Override
        protected Weather doInBackground(URL... urls) {
            // Create URL objects
            String urlOpenWeather = String.format(WEATHER_REQUEST_URL, mCityWeatherZone, WEATHER_METRICS, getString(R.string.openweather_appid));
            URL url = createUrl(urlOpenWeather);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            Weather weather = extractFeatureFromJson(jsonResponse);

            // Return the {@link Weather} object as the result fo the {@link WeatherAsyncTask}
            return weather;
        }

        /**
         * Update the screen with the given weather (which was the result of the
         * {@link WeatherAsyncTask}).
         */
        @Override
        protected void onPostExecute(Weather weather) {
            if (weather == null) {
                return;
            }
            displayWeather(weather);
        }

        /**
         * Returns new URL object from the given string URL.
         * @param stringURL
         * @return url
         */
        private URL createUrl(String stringURL) {
            URL url = null;

            try {
                url = new URL(stringURL);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, getString(R.string.err_create_url), exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given OpenWeather URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link Weather} object by parsing out information
         */
        private Weather extractFeatureFromJson(String weatherJSON) {

            long [] dateTime = new long[5];
            double [] temperature = new double[5];
            String [] condition = new String[5];

            try {
                JSONObject baseJsonResponse = new JSONObject(weatherJSON);
                JSONArray weatherArray = baseJsonResponse.getJSONArray(WEATHER_LIST_ARRAY);

                // If there are results in the list array
                if (weatherArray.length() > 0) {

                    // Extract 5 day data
                    for (int i = 0; i < 5; i++) {
                        JSONObject dayForecast = weatherArray.getJSONObject(i);
                        dateTime[i] = dayForecast.getLong(WEATHER_DATETIME);
                        JSONObject temp = dayForecast.getJSONObject(WEATHER_TEMP_OBJECT);
                        temperature[i] = temp.getDouble(WEATHER_DAY_TEMP);
                        JSONArray wthr = dayForecast.getJSONArray(WEATHER_WTHR_OBJECT);
                        for (int j = 0; j< wthr.length(); j++) {
                            JSONObject recWeather = wthr.getJSONObject(j);
                            condition[i] = recWeather.getString(WEATHER_CONDITION);
                        }
                    }

                    // Create a new {@link Event} object
                    return new Weather(dateTime, temperature, condition);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, getString(R.string.err_json_parsing), e);
            }
            return null;
        }
    }

    /**
     * Calls methods for displaying individual view on City screen
     */
    public void displayCityRecord() {
        setCustomTypeface();
        displayCityImage();
        displayCityFacts();
        displayCityDesc();
        displayLocalTime();
    }

    /**
     * This method sets custom font for all views
     */
    public void setCustomTypeface() {
        mTextViewCountry.setTypeface(mCustomFont);
        mTextViewLanguage.setTypeface(mCustomFont);
        mTextViewAirport.setTypeface(mCustomFont);
        mTextViewTransport.setTypeface(mCustomFont);
        mTextViewWeather.setTypeface(mCustomFont);
        mTextClockTime.setTypeface(mCustomFont);
        mTextViewAbout.setTypeface(mCustomFont);
    }

    /**
     * This method displays photo of the city
     */
    public void displayCityImage() {
        mImageViewCity.setImageResource(mCityImage);
    }

    /**
     * This method displays the key facts of the selected city
     */
    public void displayCityFacts() {
        mTextViewCountry.setText(mCountry);
        mTextViewLanguage.setText(mLanguage);
        mTextViewAirport.setText(mAirport);
        mTextViewTransport.setText(mTransport);
    }

    /**
     * This method displays short description of the city
     */
    public void displayCityDesc() {
        mTextViewAbout.setText(mAboutCity);
    }

    /**
     * This method displays local time at the selected city
     */
    public void displayLocalTime() {
        mTextClockTime.setTimeZone(mCityTimeZone);
    }

    /**
     * This method displays 5day Weather forecast
     * @param weather
     */
    public void displayWeather(Weather weather) {
        String date = "";
        String temp = "";
        String weatherDesc = "";
        String spacer = " ";
        String newline = "\n";
        long timeInMilliseconds = 0;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM");

        StringBuilder sbDate = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            // Get display date
            timeInMilliseconds = weather.mTime[i] * 1000L;
            Date dateObject = new Date(timeInMilliseconds);
            date = dateFormatter.format(dateObject);

            // Get display temperature
            temp = String.format("%.2f", weather.mTemperature[i])+ "°C";

            // Get display weather condition
            weatherDesc = weather.mWeatherCondition[i];

            // Append to StringBuilder object
            sbDate.append(date)
                    .append(spacer).append(spacer)
                    .append(temp)
                    .append(spacer).append(spacer).append(spacer)
                    .append(weatherDesc);

            // Append newline except for last element
            if (i < 4) {
                sbDate.append(newline);
            }
        }

        mTextViewWeather.setText(sbDate.toString());
    }
}
