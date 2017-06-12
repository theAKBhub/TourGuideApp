package com.example.android.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import helper.DatabaseHelper;
import model.Places;

/**
 * Created by aditibhattacharya on 07/06/2017.
 */

public class ToursFragment extends Fragment {

    private static final String LOG_TAG = "ToursFragment";
    private static final String PLACE_TYPE = "Tour";

    private View view;
    private String mSelectedCity;
    private ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate view object
        view = inflater.inflate(R.layout.fragment_sights, container, false);

        // Get Intent Extras
        if (getActivity().getIntent().getExtras() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            mSelectedCity = bundle.getString("city");
        }

        // Fetch City Attractions filtered by City Name and Place Type
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        List<Places> places = databaseHelper.readPlacesRecord(mSelectedCity, PLACE_TYPE);

        // Create ArrayList
        final ArrayList<Places> listPlaces = new ArrayList<Places>();

        for (Places pl : places) {
            listPlaces.add(new Places(pl.getPlaceId(), pl.getPlace(), pl.getPlaceShortDesc(), pl.getPlaceWebsite(), pl.getPlaceImage()));
        }

        PlacesAdapter adapter = new PlacesAdapter(getActivity(), listPlaces);
        mListView = (ListView) view.findViewById(R.id.list_sights);
        mListView.setAdapter(adapter);


        // Set a click listener to play the audio when the list item is clicked on
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final Places placeItem = listPlaces.get(position);

                // Invoke PlaceActivity
                Intent intent = new Intent(getContext(), PlaceActivity.class);
                intent.putExtra("placeID", placeItem.getPlaceId());
                startActivity(intent);
            }
        });

        // Scroll to top when floating action button clicked
        FloatingActionButton floatingButton = (FloatingActionButton) view.findViewById(R.id.button_scroll);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView.smoothScrollToPositionFromTop(0, 0);
            }
        });

        return view;
    }
}
