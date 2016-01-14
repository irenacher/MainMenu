package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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

    private static final int CAMERA_REQUEST = 1313;
    ImageView selfieImageView;
//    GridView gridview;

     ImageButton selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_norwood_layout);

        findViewById(R.id.scale_1_button).setTag(0);
        findViewById(R.id.scale_2_button).setTag(1);
        findViewById(R.id.scale_2a_button).setTag(2);
        findViewById(R.id.scale_3_button).setTag(3);
        findViewById(R.id.scale_3a_button).setTag(4);
        findViewById(R.id.scale_3vertex_button).setTag(5);
        findViewById(R.id.scale_4_button).setTag(6);
        findViewById(R.id.scale_4a_button).setTag(7);
        findViewById(R.id.scale_5_button).setTag(8);
        findViewById(R.id.scale_5a_button).setTag(9);
        findViewById(R.id.scale_6_button).setTag(10);
        findViewById(R.id.scale_7_button).setTag(11);

        TextView hintTV = (TextView)findViewById(R.id.norwood_hint_textView);
        hintTV.setText("Choose the image that matches your hair loss progression");
        selfieImageView = (ImageView)findViewById(R.id.selfie_imageView);

        // start camera to take picture
        OpenCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap bmp = (Bitmap)data.getExtras().get("data");
            selfieImageView.setImageBitmap(bmp);
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


    public void onNorwoodButtonClick(View view) {

        if(selected != null){
            selected.setSelected(false);
        }
        ((ImageButton)view).setSelected(true);
        selected = (ImageButton)view;
        int tag = (Integer)view.getTag();
        if(tag >=0) { // tag is index of description in array

            TextView hintTextView = (TextView)findViewById(R.id.norwood_hint_textView);
            hintTextView.setText("Based on your selection your Norwood Scale is" + " " + scales[tag]);

            TextView descriptionTV = (TextView)findViewById(R.id.norwood_details_textView);
            descriptionTV.setText(descriptions[tag]);
        }
    }

    public void onSelfieClick(View view) {
        OpenCamera();
    }

    private void OpenCamera()
    {
        PackageManager pm = getPackageManager();
        boolean hasBackCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        boolean hasFrontCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
        // check that camera exists
        if (!hasBackCamera && !hasFrontCamera) {
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
        } else {
            // camera exists so show camera preview to take picture, this way camera opens with Rear Camera by default!
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(intent, CAMERA_REQUEST);
        }
    }
}

