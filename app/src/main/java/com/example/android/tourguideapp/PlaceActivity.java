package com.example.android.tourguideapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan.LeadingMarginSpan2;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import helper.DatabaseHelper;
import model.Places;

import static com.example.android.tourguideapp.R.id.toolbar;

public class PlaceActivity extends AppCompatActivity {

    private static final String LOG_TAG = PlaceActivity.class.getSimpleName();
    private static final int IMAGE_RIGHT_MARGIN = 50;
    private static final int NUM_FLOATING_LINES = 10;

    /** All UI components */
    private TextView mTextViewPlaceDesc;
    private ImageView mImageViewPlace;
    private Button mButtonWebsite;

    /** Various identifiers */
    private Typeface mCustomFont;
    private Typeface mCustomFontBold;
    private int mPlaceId;
    private String mCity;
    private String mPlaceName;
    private String mPlaceDesc;
    private String mPlaceImage;
    private String mPlaceWebsite;
    private int mResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        // Set custom toolbar
        Toolbar toolBar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get Place ID from Intent Extras
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mPlaceId = bundle.getInt("placeID");
        }

        // Initialize UI components
        mTextViewPlaceDesc = (TextView) findViewById(R.id.text_place_desc);
        mImageViewPlace = (ImageView) findViewById(R.id.image_place);
        mButtonWebsite = (Button) findViewById(R.id.button_web);

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getAssets(), "fonts/opensans_regular.ttf");
        mCustomFontBold = Typeface.createFromAsset(getAssets(), "fonts/opensans_bold.ttf");
        setCustomTypeface();

        // Fetch data from table
        fetchPlacesData();

        // Set ActionBar label
        setActionBarTitle();

        // Update UI Elements
        updateUi();

        // Invoke website upon button click
        mButtonWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mPlaceWebsite));
                startActivity(intent);
            }
        });

    }

    /**
     * This method sets custom font for all views
     */
    public void setCustomTypeface() {
        mTextViewPlaceDesc.setTypeface(mCustomFont);
        mButtonWebsite.setTypeface(mCustomFontBold);
    }

    /**
     * This method fetches data from PLACES table for the specific Place ID
     */
    public void fetchPlacesData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Places> places = databaseHelper.readPlacesData(mPlaceId);

        for (Places pl : places) {
            mCity = pl.getCityName().trim();
            mPlaceName = pl.getPlace().trim();
            mPlaceDesc =  pl.getPlaceDesc().trim();
            mPlaceImage = pl.getPlaceImage().trim();
            mPlaceWebsite = pl.getPlaceWebsite().trim();
        }
    }

    /**
     * This method sets Action Bar Title
     */
    public void setActionBarTitle() {
        getSupportActionBar().setTitle(mCity + " : " + mPlaceName);
    }

    /**
     * This method populates all View elements using data fetched from PLACES table
     */
    public void updateUi() {

        // Display Place Image
        displayPlaceImage();

        // Display Place Description
        displayPlaceDesc();
    }

    /**
     * This method displays image of the place
     */
    public void displayPlaceImage() {
        mResourceId = getResources().getIdentifier(mPlaceImage, "drawable", getPackageName());
        mImageViewPlace.setImageResource(mResourceId);
    }

    /**
     * This method displays description of the place
     */
    public void displayPlaceDesc() {

        // Get image as drawable object
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), mResourceId);

        // Calculate left margin of TextView that will float around ImageView
        int leftMargin = drawable.getIntrinsicWidth() + IMAGE_RIGHT_MARGIN;

        SpannableString spannableString = new SpannableString(mPlaceDesc);
        spannableString.setSpan(new Margin(NUM_FLOATING_LINES, leftMargin), 0, spannableString.length(), 0);

        mTextViewPlaceDesc.setText(spannableString);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Class that handles floating of TextView around ImageView
     */
    class Margin implements LeadingMarginSpan2
    {
        private int margin;
        private int lines;

        Margin(int lines, int margin) {
            this.margin = margin;
            this.lines = lines;
        }

        @Override
        public void drawLeadingMargin(Canvas arg0, Paint arg1, int arg2,
                                      int arg3, int arg4, int arg5, int arg6, CharSequence arg7,
                                      int arg8, int arg9, boolean arg10, Layout arg11) {
            // TODO Auto-generated method stub
        }

        @Override
        public int getLeadingMargin(boolean arg0) {
            if (arg0) {
                return margin;
            } else {
                return 0;
            }
        }

        @Override
        public int getLeadingMarginLineCount() {
            return lines;
        }

    }
}
