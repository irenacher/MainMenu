package com.example.irenachernyak.mainmenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by irenachernyak on 8/13/15.
 */
public class HairLossDetailsActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairloss_detail_layout);

        Intent intent = getIntent();
        String topicfilename = intent.getStringExtra("TopicFileName");

        try
        {
            //Load the file from assets folder - don't forget to INCLUDE the extension
            String output = LoadFile(topicfilename, false);
//            TextView tv = (TextView)findViewById(R.id.details_textView);

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

//                    SpannableStringBuilder ssb = new SpannableStringBuilder( textFragment);
//                    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.find_physician);
//                    ssb.setSpan( new ImageSpan( this, image ), 0, 0, Spannable.SPAN_INCLUSIVE_INCLUSIVE );
//                    tv.setText(ssb, TextView.BufferType.SPANNABLE);

//                    tv.append(textFragment);
                }
            }
        }
        catch (IOException e)
        {
            //display an error toast message
            Toast toast = Toast.makeText(this, "File: not found!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //load file from apps res/raw folder or Assets folder
    public String LoadFile(String fileName, boolean loadFromRawFolder) throws IOException
    {
        //Create a InputStream to read the file into
        InputStream iS;

        //get the file as a stream
        iS = getResources().getAssets().open(fileName);


        //create a buffer that has the same size as the InputStream
        byte[] buffer = new byte[iS.available()];
        //read the text file as a stream, into the buffer
        iS.read(buffer);
        //create a output stream to write the buffer into
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        oS.write(buffer);
        //Close the Input and Output streams
        oS.close();
        iS.close();

        //return the output stream as a String
        return oS.toString();
    }

}
