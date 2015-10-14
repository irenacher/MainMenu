package com.example.irenachernyak.mainmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by irenachernyak on 9/28/15.
 */
public class MySpinnerAdapter extends ArrayAdapter<String> {

    private String[] objects;
    private Context context;

    public MySpinnerAdapter(Context context, int resourceId,
                            String[] objects) {
        super(context, resourceId, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(  Context.LAYOUT_INFLATER_SERVICE );
        View row=inflater.inflate(R.layout.spinner_row_layout, parent, false);
        TextView label=(TextView)row.findViewById(R.id.spinner_item);
        label.setText(getItem(position));

//        if (position == 0) {//Special style for dropdown header
//            label.setTextColor(context.getResources().getColor(R.color.text_hint_color));
//        }

        return row;
    }

}
