package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by irenachernyak on 10/15/15.
 */
public class MyListFragment extends ListFragment{

    int mCurCheckPosition = 0;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // An ArrayAdapter connects the array to our ListView
        // getActivity() returns a Context so we have the resources needed
        // We pass a default list item text view to put the data in and the
        // array
        String [] addresses = {"address #1", "address #1", "address #1", "address #1"};
        ArrayAdapter<String> connectArrayToListView = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                addresses);
        // Connect the ListView to our data
        setListAdapter(connectArrayToListView);

        // If the screen is rotated onSaveInstanceState() below will store the
        // hero most recently selected. Get the value attached to curChoice and
        // store it in mCurCheckPosition

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

    }

    // this method will be called by android virtual machine every time
    // when device orientation changes or the activity is killed for some reason
    // in order to preserve resources
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCurCheckPosition = position;

        Intent detailIntent = new Intent(getActivity(), PhysicianDetailsActivity.class);
        startActivity(detailIntent);
    }
}

