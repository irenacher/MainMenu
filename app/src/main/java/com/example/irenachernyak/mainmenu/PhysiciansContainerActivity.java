package com.example.irenachernyak.mainmenu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by irenachernyak on 10/13/15.
 */
public class PhysiciansContainerActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // We use this fragment as a pointer to the visible one, so we can hide it easily.
//    private Fragment content = null;

    private SupportMapFragment mMapFragment;
    private MyListFragment mMyListFragment;
    private GoogleMap map;
    private int zoomLevel = 4;
    private LatLng defaultLatLng = new LatLng(39.233956, -77.484703);

    final static String ARG_FRAGMENT = "fragment";
    int isMapFragmentVisible = -1;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Log.v(TAG, "onCreate()");
        Log.v(TAG, "bundle " + bundle == null ? "true" : "false");

        setContentView(R.layout.physicians_container_layout);

        if(bundle == null){
            // First launch so add the fragments
            Log.d(TAG, "Setting up Fragments...");
            SegmentedToggleButton button = (SegmentedToggleButton)findViewById(R.id.option1);
            button.setChecked(true);
            setupFragments();
        } else {
            Log.d(TAG, "Bundle is Present");

            // Check to see if we're on not on a tablet
            mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMyListFragment = (MyListFragment) getSupportFragmentManager().findFragmentById(R.id.sample_list);

            // Check to see if the MapFragment is visible
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Log.d(TAG, "ARG_FRAGMENT is " + Integer.toString(bundle.getInt(ARG_FRAGMENT)));

            isMapFragmentVisible = bundle.getInt(ARG_FRAGMENT);
            if(isMapFragmentVisible == 1){
                ft.hide(mMyListFragment);
                ft.commit();
            } else {
                ft.hide(mMapFragment);
                ft.commit();
            }

        }
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(PhysiciansContainerActivity.this, "Query is:" + query, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method checks if the fragments for the Store List and the MapFragment exist
     * If they don't then it creates them and hides them to be called whenever they need to be used.
     */
    private void setupFragments() {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Even if activity is killed, possible that the fragment remains so find it
        // and we won't need to add it again
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(mMapFragment == null){
            Log.d(TAG, "Setting up Small Map Layout");
            mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        }
        try {
            map = mMapFragment.getMap();
            if (map!=null){
                map.getUiSettings().setZoomControlsEnabled(true);
                map.setMyLocationEnabled(true);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.getUiSettings().setZoomControlsEnabled(true);

                map.addMarker(new MarkerOptions().position(defaultLatLng).title("This is the title"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, zoomLevel));

            }

        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        ft.hide(mMapFragment);

        mMyListFragment = (MyListFragment) getSupportFragmentManager().findFragmentById(R.id.sample_list);
        if(mMyListFragment == null){
            mMyListFragment = (MyListFragment) getSupportFragmentManager().findFragmentById(R.id.sample_list);
        }
        ft.show(mMyListFragment);
        ft.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save what our last fragment position was for one-pane layout
        if(mMapFragment.isHidden()){
            outState.putInt(ARG_FRAGMENT, 0);
        } else {
            outState.putInt(ARG_FRAGMENT, 1);
        }


        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_search, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;

    }


    public void onToggleListClicked(View view) {
        if(mMapFragment != null && mMyListFragment != null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(mMapFragment);
            ft.show(mMyListFragment);
            ft.commit();
        }
    }

    public void onToggleMapClicked(View view) {
        if(mMapFragment != null && mMyListFragment != null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(mMyListFragment);
            ft.show(mMapFragment);
            ft.commit();
        }
    }


    @Override
    public void onPause() {
        if (map != null){
            map.setMyLocationEnabled(false);
//            map.setTrafficEnabled(false);
        }
        super.onPause();
    }

}
