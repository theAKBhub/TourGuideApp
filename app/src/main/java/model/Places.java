package model;

/**
 * Created by aditibhattacharya on 23/05/2017.
 */

public class Places {
    private int mPlaceId;
    private String mCityName;
    private String mPlaceName;
    private String mPlaceType;
    private String mPlaceShortDesc;
    private String mPlaceDesc;
    private String mPlaceWebsite;
    private int mPlaceBookmark;
    private String mPlaceImage;

    /**
     * Default constructor with no arguments
     */
    public Places() {

    }

    /**
     * Default constructor with all arguments
     * @param placeId
     * @param cityName
     * @param placeName
     * @param placeType
     * @param placeShortDesc
     * @param placeDesc
     * @param placeWebsite
     * @param placeBookmark
     * @param placeImage
     */
    public Places(int placeId, String cityName, String placeName, String placeType, String placeShortDesc,
                  String placeDesc, String placeWebsite, int placeBookmark, String placeImage) {
        mPlaceId = placeId;
        mCityName = cityName;
        mPlaceName = placeName;
        mPlaceType = placeType;
        mPlaceShortDesc = placeShortDesc;
        mPlaceDesc = placeDesc;
        mPlaceWebsite = placeWebsite;
        mPlaceBookmark = placeBookmark;
        mPlaceImage = placeImage;
    }

    /**
     * Default constructor
     * @param placeId
     * @param placeName
     * @param placeDesc
     * @param placeWebsite
     * @param placeBookmark
     * @param placeImage
     */
    public Places(int placeId, String placeName, String placeDesc,
                  String placeWebsite, int placeBookmark, String placeImage) {
        mPlaceId = placeId;
        mPlaceName = placeName;
        mPlaceDesc = placeDesc;
        mPlaceWebsite = placeWebsite;
        mPlaceBookmark = placeBookmark;
        mPlaceImage = placeImage;
    }

    /**
     * Default constructor
     * @param placeId
     * @param placeName
     * @param placeShortDesc
     * @param placeWebsite
     * @param placeImage
     */
    public Places(int placeId, String placeName, String placeShortDesc,
                  String placeWebsite, String placeImage) {
        mPlaceId = placeId;
        mPlaceName = placeName;
        mPlaceDesc = placeShortDesc;
        mPlaceWebsite = placeWebsite;
        mPlaceImage = placeImage;
    }

    /**
     * Setter method for Place ID
     * @param placeId
     */
    public void setPlaceId(int placeId) {
        mPlaceId = placeId;
    }

    /**
     * Getter method for Place ID
     * @return place id
     */
    public int getPlaceId() {
        return mPlaceId;
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
     * @return cityName
     */
    public String getCityName() {
        return mCityName;
    }

    /**
     * Setter method for Place Name
     * @param placeName
     */
    public void setPlace(String placeName) {
        mPlaceName = placeName;
    }

    /**
     * Getter method for Place Name
     * @return place name
     */
    public String getPlace() {
        return mPlaceName;
    }

    /**
     * Setter method for Place Type
     * @param placeType
     */
    public void setPlaceType(String placeType) {
        mPlaceType = placeType;
    }

    /**
     * Getter method for Place Type
     * @return place type
     */
    public String getPlaceType() {
        return mPlaceType;
    }

    /**
     * Setter method for Place Short Description
     * @param placeShortDesc
     */
    public void setPlaceShortDesc(String placeShortDesc) {
        mPlaceShortDesc = placeShortDesc;
    }

    /**
     * Getter method for Place Short Description
     * @return place short desc
     */
    public String getPlaceShortDesc() {
        return mPlaceShortDesc;
    }

    /**
     * Setter method for Place Description
     * @param placeDesc
     */
    public void setPlaceDesc(String placeDesc) {
        mPlaceDesc = placeDesc;
    }

    /**
     * Getter method for Place Description
     * @return place desc
     */
    public String getPlaceDesc() {
        return mPlaceDesc;
    }

    /**
     * Setter method for Place Bookmark
     * @param placeBookmark
     */
    public void setPlaceBookmark(int placeBookmark) {
        mPlaceBookmark = placeBookmark;
    }

    /**
     * Getter method for Place Bookmark
     * @return bookmark
     */
    public int getPlaceBookmark() {
        return mPlaceBookmark;
    }

    /**
     * Setter method for Place Image
     * @param placeImage
     */
    public void setPlaceImage(String placeImage) {
        mPlaceImage = placeImage;
    }

    /**
     * Getter method for Place Image
     * @return place image
     */
    public String getPlaceImage() {
        return mPlaceImage;
    }

    /**
     * Getter method for Place Website
     * @return website
     */
    public String getPlaceWebsite() {
        return mPlaceWebsite;
    }

    /**
     * Setter method for Place Website
     * @param placeWebsite
     */
    public void setPlaceWebsite(String placeWebsite) {
        mPlaceWebsite = placeWebsite;
    }
}
