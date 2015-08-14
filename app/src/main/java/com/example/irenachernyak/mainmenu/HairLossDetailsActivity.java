package com.example.irenachernyak.mainmenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
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
            TextView tv = (TextView)findViewById(R.id.details_textView);
            int pos = -1;
            // parse text file  and put only text into TextView
            String [] imageStartFragments = output.split("<");
            for(int i = 0; i < imageStartFragments.length; i++){
                pos = imageStartFragments[i].indexOf(">");
                if(pos != -1) {
                    String imageFileName = imageStartFragments[i].substring(0, pos);
                    String textFragment = imageStartFragments[i].substring(pos+1);
                    tv.append(textFragment);
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
