package com.example.irenachernyak.mainmenu;


import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by irenachernyak on 8/10/15.
 */
public class TopicsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        Intent activityThatCalled = getIntent();
        Topics currentTopics = (Topics)activityThatCalled.getExtras().getSerializable("Topics");
        if(currentTopics.getTopics() != null){
            ListAdapter listAdapter = new TopicsListAdapter(this, currentTopics.getTopics());
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
//                    Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_SHORT).show();

                    //start DetailsActivity passing position
                    Intent intent = new Intent(TopicsActivity.this, HairLossDetailsActivity.class);
                    String topicFileName = ""; //= (String)parent.getItemAtPosition(position);
                    switch (position) {
                        case 0:
                            topicFileName = "Overview.txt";
                            break;
                        case 1:
                            topicFileName = "MaleBaldness.txt";
                            break;
                        case 2:
                            topicFileName = "FemaleBaldness.txt";
                            break;
                        case 3:
                            topicFileName = "HowFar.txt";
                            break;
                    }
                    intent.putExtra("TopicFileName", topicFileName);
                    startActivity(intent);
                }
            });

        }

        int resourceId = (int)activityThatCalled.getExtras().getInt("Image", R.drawable.artas_robot);
        ImageView imageView = (ImageView)findViewById(R.id.topics_imageView);
        imageView.setImageResource(resourceId);

        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        } catch(Exception ex){
            Log.e("DisplayMessageActivity", ex.getMessage());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
