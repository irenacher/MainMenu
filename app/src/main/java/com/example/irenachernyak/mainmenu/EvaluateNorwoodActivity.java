package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by irenachernyak on 8/17/15.
 */
public class EvaluateNorwoodActivity extends AppCompatActivity{


    private static final String [] scales = {"1", "2", "2a", "3", "3a", "3v", "4", "4a", "5", "5a", "6", "7"};
    private static final String [] descriptions = {
            "You have no visible signs of hair loss at this time.",
            "You have progressed from an adolescent hairline to a normal adult hairline. No treatment is indicated at this time.",
            "You are in the initial stages of recession on the forehead.",
            "You have recession at the temples that may respond to medical therapy alone if early, and to both medical and surgical treatments if more advanced.",
            "You have slight hair loss above the temples, and at the forehead.",
            "You have slight hair loss above the temples, at the forehead and at the crown of the head.",
            "You have more noticeable loss on the crown, above the temples and/or frontal areas.",
            "You have more noticeable loss above the temples and/or frontal areas.",
            "You have significant levels of hair loss now occurring on the top of the vertex and crown.",
            "You have significant levels of hair loss now occurring on the top of the vertex and mid-scalp.",
            "You have significant hair loss leaving a single large bald area on the front and top of the scalp. The hair on the sides of the scalp remains relatively high.",
            "You have extensive hair loss with only a wreath of hair remaining on the back and sides. "
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_norwood_layout);

        ImageButton ib0 = (ImageButton)findViewById(R.id.scale_1_button);
        ImageButton ib1 = (ImageButton)findViewById(R.id.scale_2_button);
        ImageButton ib2 = (ImageButton)findViewById(R.id.scale_2a_button);
        ImageButton ib3 = (ImageButton)findViewById(R.id.scale_3_button);
        ImageButton ib4 = (ImageButton)findViewById(R.id.scale_3a_button);
        ImageButton ib5 = (ImageButton)findViewById(R.id.scale_3vertex_button);
        ImageButton ib6 = (ImageButton)findViewById(R.id.scale_4_button);
        ImageButton ib7 = (ImageButton)findViewById(R.id.scale_4a_button);
        ImageButton ib8 = (ImageButton)findViewById(R.id.scale_5_button);
        ImageButton ib9 = (ImageButton)findViewById(R.id.scale_5a_button);
        ImageButton ib10 = (ImageButton)findViewById(R.id.scale_6_button);
        ImageButton ib11 = (ImageButton)findViewById(R.id.scale_7_button);

        ib0.setTag(0);
        ib1.setTag(1);
        ib2.setTag(2);
        ib3.setTag(3);
        ib4.setTag(4);
        ib5.setTag(5);
        ib6.setTag(6);
        ib7.setTag(7);
        ib8.setTag(8);
        ib9.setTag(9);
        ib10.setTag(10);
        ib11.setTag(11);

        TextView hintTV = (TextView)findViewById(R.id.norwood_hint_textView);
        hintTV.setText("Choose the image that matches your hair loss progression");
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


    public void onNorwoodButtonClick(View view) {

        int tag = (Integer)view.getTag();
        if(tag >=0) { // tag is index of description in array

            TextView hintTextView = (TextView)findViewById(R.id.norwood_hint_textView);
            hintTextView.setText("Based on your selection your Norwood Scale is" + " " + scales[tag]);

            TextView descriptionTV = (TextView)findViewById(R.id.norwood_details_textView);
            descriptionTV.setText(descriptions[tag]);
        }
    }



}
