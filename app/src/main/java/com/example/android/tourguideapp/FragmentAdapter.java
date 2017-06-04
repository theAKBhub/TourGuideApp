package com.example.android.tourguideapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "CITY", "SIGHTS" };
    private Context context;

    private int[] imageResId = {
            R.drawable.ic_location_city_black_24dp,
            R.drawable.ic_local_activity_black_24dp
    };


    public FragmentAdapter (FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position) {
            case 0:
                fragment = new CityFragment();
                break;
            case 1:
                fragment =  new SightsFragment();
                break;
         /*   case 2:
                fragment =  new ToursFragment();
                break;
            case 3:
                fragment =  new HotelsFragment();
                break;
            case 4:
                fragment =  new RestaurantsFragment();
                break; */
            default:
                fragment = new CityFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // Return image resources and ttitles for tab icons
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());

        SpannableString spannableString = new SpannableString("   " + tabTitles[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);

        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
