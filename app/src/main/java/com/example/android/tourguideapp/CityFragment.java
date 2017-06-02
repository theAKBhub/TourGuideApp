package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aditibhattacharya on 01/06/2017.
 */

public class CityFragment extends Fragment {

    private View view;
    private String mSelectedCity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get Intent Extras
        if (getActivity().getIntent().getExtras() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            mSelectedCity = bundle.getString("city");
        }

        // Inflate view object
        view = inflater.inflate(R.layout.fragment_city, container, false);

        TextView test = (TextView) view.findViewById(R.id.test);
        test.setText(mSelectedCity);

        return view;
    }

}
