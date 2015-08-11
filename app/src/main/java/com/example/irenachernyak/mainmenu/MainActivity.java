package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onUnderstandHairLossClick(View view) {

        String [] topics = {"Overview","Male Baldness", "Female Baldness", "What's Next?"};
        Intent intent = new Intent(this, TopicsActivity.class);
        Topics topicsarray = new Topics(topics);
        intent.putExtra("Topics", topicsarray);
        int resId = R.drawable.overview01;
        intent.putExtra("Image", R.drawable.overview01);
        startActivity(intent);
    }

    public void onLearnArtasClick(View view) {
        String [] topics = {"ARTAS Overview","ARTAS Before and After Photos", "ARTAS Video Library"};
        Intent intent = new Intent(this, TopicsActivity.class);
        Topics topicsarray = new Topics(topics);
        intent.putExtra("Topics", topicsarray);
        int resId = R.drawable.artas_robot;
//        int resID = getResources().getIdentifier("artas_robot" , "drawable", getPackageName());
        intent.putExtra("Image", R.drawable.artas_robot);
        startActivity(intent);
    }
}
