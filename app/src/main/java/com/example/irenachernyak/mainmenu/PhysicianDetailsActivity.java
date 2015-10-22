package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by irenachernyak on 10/21/15.
 */
public class PhysicianDetailsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physician_details_layout);


    }

    public void onCallDoctorClicked(View view) {
        Toast.makeText(this,"Phone button clicked", Toast.LENGTH_SHORT).show();
    }
}
