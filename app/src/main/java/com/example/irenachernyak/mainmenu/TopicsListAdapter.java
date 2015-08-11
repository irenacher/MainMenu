package com.example.irenachernyak.mainmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class TopicsListAdapter extends ArrayAdapter<String> {
    public TopicsListAdapter(Context context,  String[] objects) {
        super(context, R.layout.topic_row_layout, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.topic_row_layout, parent, false);
        String currentTopic = getItem(position);
        TextView tv = (TextView)theView.findViewById(R.id.textView_topic);
        tv.setText(currentTopic);

        ImageView arrowView = (ImageView)theView.findViewById(R.id.arrow_imageview);
        arrowView.setImageResource(R.drawable.arrow);
        return theView;
    }
}
