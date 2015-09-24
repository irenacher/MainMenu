package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by irenachernyak on 9/2/15.
 */
public class RequestPhotosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_photos_layout);
    }

    public void onShowRequestForm(View view) {
        Intent intent = new Intent(this, RequestFormActivity.class);
        startActivity(intent);
    }
}
