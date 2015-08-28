package com.example.irenachernyak.mainmenu;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by irenachernyak on 8/10/15.
 */
public class TopicsActivity extends AppCompatActivity{

    private String _infotype = "";
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
            resourceId = extras.getInt("Image", R.drawable.artas_robot);
            _infotype = extras.getString("InfoType");

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
                spEditor.putString("InfoType", _infotype);
                spEditor.commit();
            } catch(JSONException ex){
                ex.printStackTrace();
            }

        } else {
            SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
            String sTitles = preferences.getString("TopicTitles", "");
            resourceId = preferences.getInt("ImageResId",R.drawable.artas_robot);
            _infotype = preferences.getString("InfoType", getString(R.string.hair_loss_info_type));
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

            if(_infotype.equals(getString(R.string.hair_loss_info_type))) {
                this.setTitle(getString(R.string.hair_loss_topics_title));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        //start DetailsActivity passing title and filename
                        Intent intent = new Intent(TopicsActivity.this, HairLossDetailsActivity.class);
                        String topicFileName = "";
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
                        String title = (String)parent.getItemAtPosition(position);
                        intent.putExtra("Title", title);
                        startActivity(intent);
                    }
                });
            }

            if(_infotype.equals(getString(R.string.artas_info_type))){
                this.setTitle(getString(R.string.artas_info_topics_title));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        //start DetailsActivity passing title
                        Intent intent = new Intent(TopicsActivity.this, PageViewActivity.class);
                        String title = (String) parent.getItemAtPosition(position);
                        intent.putExtra("Title", title);
                        startActivity(intent);
                    }
                });
            }

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
