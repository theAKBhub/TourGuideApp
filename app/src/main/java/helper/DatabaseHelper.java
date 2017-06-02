package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.City;

/**
 * Created by aditibhattacharya on 01/06/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbTourGuide";
    private static final String TABLE_CITIES = "tbl_cities";
    private static final String TABLE_PLACES = "tbl_places";

    // Common column name for Cities and Places
    private static final String KEY_CITY_ID = "city_id";

    // Cities table column names
    private static final String KEY_CITY = "city_name";
    private static final String KEY_CITY_COUNTRY = "city_country";
    private static final String KEY_CITY_LANGUAGE = "city_language";
    private static final String KEY_CITY_AIRPORT = "city_airport";
    private static final String KEY_CITY_TRANSPORT = "city_transport";
    private static final String KEY_CITY_SHORT_DESC = "city_short_desc";
    private static final String KEY_CITY_TIMEZONE = "city_time_zone";
    private static final String KEY_CITY_WEATHERZONE = "city_weather_zone";

    // Cities table create SQL
    private static final String CREATE_TABLE_CITIES = "CREATE TABLE "
            + TABLE_CITIES + "(" + KEY_CITY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CITY + " TEXT,"
            + KEY_CITY_COUNTRY + " TEXT,"
            + KEY_CITY_LANGUAGE + " TEXT,"
            + KEY_CITY_AIRPORT + " TEXT,"
            + KEY_CITY_TRANSPORT + " TEXT,"
            + KEY_CITY_SHORT_DESC + " TEXT,"
            + KEY_CITY_TIMEZONE + " TEXT,"
            + KEY_CITY_WEATHERZONE + " TEXT"
            + ")";


    /**
     * Default constructor
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITIES);
            // db.execSQL(CREATE_TABLE_PLACES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
            // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);

        // Create new tables
        onCreate(db);
    }


    // --------------------  Methods for Cities table  --------------------- //

    /**
     * This method inserts record into Cities table
     * @param city
     */
    public void createCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY_ID, city.getCityId()); // City Id
        values.put(KEY_CITY, city.getCityName()); // City Name
        values.put(KEY_CITY_COUNTRY, city.getCountry()); // Country
        values.put(KEY_CITY_LANGUAGE, city.getLanguage()); // Language
        values.put(KEY_CITY_AIRPORT, city.getAirport()); // Airport
        values.put(KEY_CITY_TRANSPORT, city.getTransport()); // Transport
        values.put(KEY_CITY_SHORT_DESC, city.getDescription()); // Description
        values.put(KEY_CITY_TIMEZONE, city.getCityTimeZone()); // Time Zone
        values.put(KEY_CITY_WEATHERZONE, city.getCityWeatherZone()); // Weather Zone

        db.insert(TABLE_CITIES, null, values); // Inserting Row
        db.close(); // Closing database connection
    }

    /**
     * Reads all records from Cities table
     * @return list of cities
     */
    public List<City> readCities() {
        List<City> cityList = new ArrayList<City>();

        String selectQuery = "SELECT  * FROM " + TABLE_CITIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityId(Integer.parseInt(cursor.getString(0)));
                city.setCityName(cursor.getString(1));
                city.setCountry(cursor.getString(2));
                city.setLanguage(cursor.getString(3));
                city.setAirport(cursor.getString(4));
                city.setTransport(cursor.getString(5));
                city.setDescription(cursor.getString(6));
                city.setCityTimeZone(cursor.getString(7));
                city.setCityWeatherZone(cursor.getString(8));
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        return cityList;
    }

    /**
     * Reads record from Cities table for a specific city
     * @return list of cities
     */
    public List<City> readCityRecord(String cityName) {
        List<City> cityList = new ArrayList<City>();

        String selectQuery = "SELECT  * FROM " + TABLE_CITIES + " WHERE " + KEY_CITY + " = '" + cityName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityId(Integer.parseInt(cursor.getString(0)));
                city.setCityName(cursor.getString(1));
                city.setCountry(cursor.getString(2));
                city.setLanguage(cursor.getString(3));
                city.setAirport(cursor.getString(4));
                city.setTransport(cursor.getString(5));
                city.setDescription(cursor.getString(6));
                city.setCityTimeZone(cursor.getString(7));
                city.setCityWeatherZone(cursor.getString(8));
                cityList.add(city);
            } while (cursor.moveToNext());
        }

        return cityList;
    }

    /**
     * This method deletes all rows in Cities table
     */
    public void deleteCities() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CITIES, null, null);
        db.close();
    }

    /**
     * This method gets the number of rows in Cities table
     * @return number of rows
     */
    public int getCitiesRowsCount() {
        int numRows = 0;

        String countQuery = "SELECT  * FROM " + TABLE_CITIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        numRows = cursor.getCount();
        cursor.close();

        // return count
        return numRows;
    }


    // --------------------  Methods for PLACES table  --------------------- //



}
