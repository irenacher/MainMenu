package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by irenachernyak on 8/17/15.
 */
public class TakeSelfieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_selfie_layout);
    }

    public void onEvaluateHairLoss(View view) {

        Intent intent = new Intent(TakeSelfieActivity.this, EvaluateNorwoodActivity.class);
        startActivity(intent);
    }
}
