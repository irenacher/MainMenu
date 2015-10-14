package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by irenachernyak on 10/13/15.
 */
public class PhysiciansContainerActivity extends AppCompatActivity {

    private LatLng defaultLatLng = new LatLng(39.233956, -77.484703);
    private GoogleMap map;
    private int zoomLevel = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physicians_container_layout);


        try {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map!=null){
                map.getUiSettings().setZoomControlsEnabled(true);
//                map.setTrafficEnabled(true);
                map.setMyLocationEnabled(true);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.getUiSettings().setZoomControlsEnabled(true);

                map.addMarker(new MarkerOptions().position(defaultLatLng).title("This is the title"));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, zoomLevel));


//                map.setOnInfoWindowClickListener(this);


            }


        }catch (NullPointerException e) {
            e.printStackTrace();
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
