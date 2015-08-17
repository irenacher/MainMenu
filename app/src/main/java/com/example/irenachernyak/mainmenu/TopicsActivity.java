package com.example.irenachernyak.mainmenu;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by irenachernyak on 8/10/15.
 */
public class TopicsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        Topics currentTopics = null;
        int resourceId = 0;

        Intent activityThatCalled = getIntent();
        Bundle extras = activityThatCalled.getExtras();
        if(extras != null) {
            // get everything from extras and save them in SharedPreferences for the future when activity will be
            // displayed by navigating Up from the child activity
            currentTopics = (Topics)extras.getSerializable("Topics");
            resourceId = (int)activityThatCalled.getExtras().getInt("Image", R.drawable.artas_robot);

            try {
                JSONArray jsonArr = new JSONArray();
                for (String title : currentTopics.getTopics()) {
                    JSONObject pnObj = new JSONObject();
                    pnObj.put("topictitle", title);
                    jsonArr.put(pnObj);
                }
                SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
                String serializedtopics = jsonArr.toString();
                spEditor.putString("TopicTitles", serializedtopics);
                spEditor.putInt("ImageResId", resourceId);
                spEditor.commit();
            } catch(JSONException ex){
                ex.printStackTrace();
            }

        } else {
            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
            String sTitles = preferences.getString("TopicTitles", "");
            resourceId = preferences.getInt("ImageResId",R.drawable.artas_robot);
            if(!sTitles.equals("")){
                try {

                    JSONArray jArr = new JSONArray(sTitles);
                    String [] titles = new String[jArr.length()];
                    for (int i=0; i < jArr.length(); i++) {

                        JSONObject obj = jArr.getJSONObject(i);
                        String title = obj.getString("topictitle");
                        titles[i] = title;
                    }

                    currentTopics = new Topics(titles);

                }catch(JSONException ex){
                    ex.printStackTrace();
                }

            }
        }
        // now set topics in list
        if(currentTopics.getTopics() != null){
            ListAdapter listAdapter = new TopicsListAdapter(this, currentTopics.getTopics());
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

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

        // set image into ImageView
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
