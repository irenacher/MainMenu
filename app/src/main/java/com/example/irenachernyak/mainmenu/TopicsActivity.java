package com.example.irenachernyak.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class TopicsActivity extends Activity{
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
        }

        int resourceId = (int)activityThatCalled.getExtras().getInt("Image", R.drawable.artas_robot);
        ImageView imageView = (ImageView)findViewById(R.id.topics_imageView);
//        String typename = getResources().getResourceTypeName(resourceId);
        imageView.setImageResource(resourceId);

    }
}
