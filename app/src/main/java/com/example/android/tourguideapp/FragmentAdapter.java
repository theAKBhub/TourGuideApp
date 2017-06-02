package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "CITY", "SIGHTS" };
    private Context context;

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
        // Generate title based on item position
        return tabTitles[position];
    }

}
