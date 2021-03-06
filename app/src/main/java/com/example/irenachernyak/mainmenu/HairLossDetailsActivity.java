package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by irenachernyak on 8/13/15.
 */
public class HairLossDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairloss_detail_layout);

        Intent intent = getIntent();
        String topicfilename = intent.getStringExtra("TopicFileName");
        String title = intent.getStringExtra("Title");
        this.setTitle(title);

        try
        {
            //Load the file from assets folder - don't forget to INCLUDE the extension
            String output = LoadFile(topicfilename, false);

            LinearLayout detailsLayout = (LinearLayout)findViewById(R.id.details_linear_layout);
            int pos = -1;
            // parse text string
            String [] imageStartFragments = output.split("<");

            int index = 0;

            for(int i = 0; i < imageStartFragments.length; i++){
                if(!imageStartFragments[i].equals("")){
                pos = imageStartFragments[i].indexOf(">");
                    if(pos != -1) {
                        String imageFileName = imageStartFragments[i].substring(0, pos);

                        String formatted = imageFileName.toLowerCase().replace('-', '_');
                        int dotpos = formatted.indexOf('.');
                        formatted = formatted.substring(0, dotpos);

                        int imageResId = getResources().getIdentifier(formatted, "drawable", getPackageName());
                        ImageView iv = new ImageView(this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
                        iv.setLayoutParams(layoutParams);
                        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        iv.setImageResource(imageResId);
                        detailsLayout.addView(iv, index);

                        String textFragment = imageStartFragments[i].substring(pos + 1);

                        TextView tv = new TextView(this);
                        tv.setText(textFragment);
                        detailsLayout.addView(tv, ++index);
                        index++;
                    }

                }
            }
        }
        catch (IOException e)
        {
            //display an error toast message
            Toast toast = Toast.makeText(this, "File: not found!", Toast.LENGTH_LONG);
            toast.show();
        }

        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE );
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        } catch(Exception ex){
            Log.e("DisplayMessageActivity", ex.getMessage());
        }

    }

    //load file from apps res/raw folder or Assets folder
    public String LoadFile(String fileName, boolean loadFromRawFolder) throws IOException
    {
        String content = "";
        try{
            content = Utils.LoadFile(getResources(), fileName, loadFromRawFolder);
        } catch(IOException ex) {
            throw ex;
        }

        return content;
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
