package com.example.irenachernyak.mainmenu;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by irenachernyak on 9/2/15.
 */
public class RequestPhotosActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_FRONT = 1414;
    private static final int CAMERA_REQUEST_LEFT = 1415;
    private static final int CAMERA_REQUEST_RIGHT = 1416;
    private static final int CAMERA_REQUEST_BACK = 1417;
    private static final int CAMERA_REQUEST_TOP = 1418;

    private static final String IMAGENAME_FRONT = "photo_front.png";
    private static final String IMAGENAME_LEFT = "photo_left.png";
    private static final String IMAGENAME_RIGHT = "photo_right.png";
    private static final String IMAGENAME_BACK = "photo_back.png";
    private static final String IMAGENAME_TOP = "photo_top.png";

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_photos_layout);

          LoadImages();
    }

    public void onShowRequestForm(View view) {
        Intent intent = new Intent(this, RequestFormActivity.class);
        startActivity(intent);
    }

    private void LoadImages() {
        ImageView iv_front = (ImageView)findViewById(R.id.imageViewFront);
        ImageView iv_left = (ImageView)findViewById(R.id.imageViewLeft);
        ImageView iv_right = (ImageView)findViewById(R.id.imageViewRight);
        ImageView iv_back = (ImageView)findViewById(R.id.imageViewBack);
        ImageView iv_top = (ImageView)findViewById(R.id.imageViewTop);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        String dirpath = cw.getDir("photos", Context.MODE_PRIVATE).getPath();

        loadImageFromStorage(dirpath,IMAGENAME_FRONT, iv_front );
        loadImageFromStorage(dirpath,IMAGENAME_LEFT, iv_left );
        loadImageFromStorage(dirpath,IMAGENAME_RIGHT, iv_right );
        loadImageFromStorage(dirpath,IMAGENAME_BACK, iv_back );
        loadImageFromStorage(dirpath,IMAGENAME_TOP, iv_top );

    }

    public void takePhoto(View view) {
        int id = view.getId();
        int camera_request = CAMERA_REQUEST_FRONT;
        switch(id){
            case R.id.imageViewFront:
                camera_request = CAMERA_REQUEST_FRONT;
                break;
            case R.id.imageViewLeft:
                camera_request = CAMERA_REQUEST_LEFT;
                 break;
            case R.id.imageViewRight:
                camera_request = CAMERA_REQUEST_RIGHT;
                 break;
            case R.id.imageViewBack:
                camera_request = CAMERA_REQUEST_BACK;
                break;
            case R.id.imageViewTop:
                camera_request = CAMERA_REQUEST_TOP;
                 break;
        }

        OpenCamera(camera_request);
    }

    private void OpenCamera(int request)
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
            startActivityForResult(intent, request);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK){
            Bitmap bmp = (Bitmap)data.getExtras().get("data");
            ImageView iv = null;
            String filename = "";

            switch(requestCode){
                case CAMERA_REQUEST_FRONT:
                    iv = (ImageView)findViewById(R.id.imageViewFront);
                    filename = IMAGENAME_FRONT;
                    break;
                case CAMERA_REQUEST_LEFT:
                    iv = (ImageView)findViewById(R.id.imageViewLeft);
                    filename = IMAGENAME_LEFT;
                    break;
                case CAMERA_REQUEST_RIGHT:
                    iv = (ImageView)findViewById(R.id.imageViewRight);
                    filename = IMAGENAME_RIGHT;
                    break;
                case CAMERA_REQUEST_BACK:
                    iv = (ImageView)findViewById(R.id.imageViewBack);
                    filename = IMAGENAME_BACK;
                    break;
                case CAMERA_REQUEST_TOP:
                    iv = (ImageView)findViewById(R.id.imageViewTop);
                    filename = IMAGENAME_TOP;
                    break;
            }
            if(iv != null) {
                iv.setImageBitmap(bmp);

                // save bitmap as imagefile
                String path = saveToInternalSorage(bmp, filename);
            }
        }
    }

    protected String saveToInternalSorage(Bitmap bitmapImage, String filename){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/photos
        File directory = cw.getDir("photos", Context.MODE_PRIVATE);
        // Create file with specified filename
        File filePath=new File(directory,filename);

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(filePath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path, String filename, ImageView iv)
    {

        try {
            File f=new File(path, filename);
            if(f.exists()) {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                iv.setImageBitmap(b);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
