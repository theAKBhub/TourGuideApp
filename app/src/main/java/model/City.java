package model;

/**
 * Created by aditibhattacharya on 23/05/2017.
 */

public class City {
    private int mCityId;
    private String mCityName;
    private String mCityCountry;
    private String mCityLanguage;
    private String mCityAirport;
    private String mCityTransport;
    private String mCityShortDesc;
    private String mCityTimeZone;
    private String mCityWeatherZone;


    /**
     * Default constructor with all arguments
     * @param cityId
     * @param cityName
     * @param cityCountry
     * @param cityLanguage
     * @param cityAirport
     * @param cityTransport
     * @param cityShortDesc
     * @param cityTimeZone
     * @param cityWeatherZone
     */
    public City(int cityId, String cityName, String cityCountry, String cityLanguage,
                String cityAirport, String cityTransport, String cityShortDesc,
                String cityTimeZone, String cityWeatherZone) {
        mCityId = cityId;
        mCityName = cityName;
        mCityCountry = cityCountry;
        mCityLanguage = cityLanguage;
        mCityAirport = cityAirport;
        mCityTransport = cityTransport;
        mCityShortDesc = cityShortDesc;
        mCityTimeZone = cityTimeZone;
        mCityWeatherZone = cityWeatherZone;
    }

    /**
     * Default constructor with no arguments
     */
    public City() {

    }

    /**
     * Setter method for City ID
     * @param cityId
     */
    public void setCityId(int cityId) {
        mCityId = cityId;
    }

    /**
     * Getter method for City ID
     * @return city id
     */
    public int getCityId() {
        return mCityId;
    }

    /**
     * Setter method for City Name
     * @param cityName
     */
    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    /**
     * Getter method for City Name
     * @return city name
     */
    public String getCityName() {
        return mCityName;
    }

    /**
     * Setter method for Country
     * @param cityCountry
     */
    public void setCountry(String cityCountry) {
        mCityCountry = cityCountry;
    }

    /**
     * Getter method for Country
     * @return country
     */
    public String getCountry() {
        return mCityCountry;
    }

    /**
     * Setter method for Language
     * @param cityLanguage
     */
    public void setLanguage(String cityLanguage) {
        mCityLanguage = cityLanguage;
    }

    /**
     * Getter method for Language
     * @return language
     */
    public String getLanguage() {
        return mCityLanguage;
    }

    /**
     * Setter method for Airport
     * @param cityAirport
     */
    public void setAirport(String cityAirport) {
        mCityAirport = cityAirport;
    }

    /**
     * Getter method for Airport
     * @return airport
     */
    public String getAirport() {
        return mCityAirport;
    }

    /**
     * Setter method for Transport
     * @param cityTransport
     */
    public void setTransport(String cityTransport) {
        mCityTransport = cityTransport;
    }

    /**
     * Getter method for Transport
     * @return transport
     */
    public String getTransport() {
        return mCityTransport;
    }

    /**
     * Setter method for Short Description
     * @param cityShortDesc
     */
    public void setDescription(String cityShortDesc) {
        mCityShortDesc = cityShortDesc;
    }

    /**
     * Getter method for Short Description
     * @return short desc
     */
    public String getDescription() {
        return mCityShortDesc;
    }

    /**
     * Setter method for City Timezone
     * @param cityTimeZone
     */
    public void setCityTimeZone(String cityTimeZone) {
        mCityTimeZone = cityTimeZone;
    }

    /**
     * Getter method for City Timezone
     * @return time zone
     */
    public String getCityTimeZone() {
        return mCityTimeZone;
    }

    /**
     * Setter method for City Weather Zone
     * @param cityWeatherZone
     */
    public void setCityWeatherZone(String cityWeatherZone) {
        mCityWeatherZone = cityWeatherZone;
    }

    /**
     * Getter method for City Weather Zone
     * @return weather zone
     */
    public String getCityWeatherZone() {
        return mCityWeatherZone;
    }

}
