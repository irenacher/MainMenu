package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by irenachernyak on 8/17/15.
 */
public class TakeSelfieActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1313;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_selfie_layout);

    }

     public void onEvaluateHairLoss(View view) {

        Intent intent = new Intent(TakeSelfieActivity.this, EvaluateNorwoodActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
