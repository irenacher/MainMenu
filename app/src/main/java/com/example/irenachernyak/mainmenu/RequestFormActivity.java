package com.example.irenachernyak.mainmenu;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by irenachernyak on 9/23/15.
 */
public class RequestFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    private static final String IMAGENAME_FRONT = "photo_front.png";
    private static final String IMAGENAME_LEFT = "photo_left.png";
    private static final String IMAGENAME_RIGHT = "photo_right.png";
    private static final String IMAGENAME_BACK = "photo_back.png";
    private static final String IMAGENAME_TOP = "photo_top.png";

    EditText firstnameEdittext;

    boolean _sendingEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_form_layout);

        addItemsToSpinners();
        findViewById(R.id.mainLayout).requestFocus();
        firstnameEdittext = (EditText)findViewById((R.id.editFirstNameText));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(_sendingEmail){
            Toast.makeText(RequestFormActivity.this, "Finished sending ...", Toast.LENGTH_SHORT).show();
            _sendingEmail = false;

            View v = getCurrentFocus();
            if(v != null){
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                findViewById(R.id.mainLayout).requestFocus();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!isTouched(v, ev) && !isTouched(firstnameEdittext, ev)) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isTouched(View view,MotionEvent ev )
    {
        Rect outRect = new Rect();
        view.getGlobalVisibleRect(outRect);
        return outRect.contains((int)ev.getRawX(), (int)ev.getRawY());
    }

    public void addItemsToSpinners()
    {
        Spinner ethnicity_spinner = (Spinner)findViewById(R.id.ethnicity_spinner);
        Spinner gender_spinner = (Spinner)findViewById(R.id.gender_spinner);
        Spinner haircolor_spinner = (Spinner)findViewById(R.id.hcolor_spinner);
        Spinner hairtype_spinner = (Spinner)findViewById(R.id.htype_spinner);

        String [] options = this.getResources().getStringArray(R.array.ethnicity_names);
        ArrayAdapter<String> ethnicity_spinner_adapter = new MySpinnerAdapter(this, R.layout.spinner_row_layout, options);
//        ethnicity_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ethnicity_spinner.setAdapter(ethnicity_spinner_adapter);

        options = this.getResources().getStringArray(R.array.gender_names);
        ArrayAdapter<String> gender_spinner_adapter = new MySpinnerAdapter(this, R.layout.spinner_row_layout, options);
//        gender_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(gender_spinner_adapter);

        options = this.getResources().getStringArray(R.array.hair_colors);
        ArrayAdapter<String> haircolor_spinner_adapter = new MySpinnerAdapter(this, R.layout.spinner_row_layout, options);
//        haircolor_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        haircolor_spinner.setAdapter(haircolor_spinner_adapter);

        options = this.getResources().getStringArray(R.array.hair_types);
        ArrayAdapter<String> hairtype_spinner_adapter = new MySpinnerAdapter(this, R.layout.spinner_row_layout, options);
//        hairtype_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairtype_spinner.setAdapter(hairtype_spinner_adapter);

        ethnicity_spinner.setOnItemSelectedListener(this);
        gender_spinner.setOnItemSelectedListener(this);
        haircolor_spinner.setOnItemSelectedListener(this);
        hairtype_spinner.setOnItemSelectedListener(this);
    }

    //========= OnItemSelectedListener implementation =================
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemSelected= parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), "Selected: " + itemSelected, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onSubmitRequest(View view) {

        try {
            SendEmail();
        }
        catch(Exception ex) {
            String msg = ex.getMessage();
            Toast.makeText(RequestFormActivity.this, msg, Toast.LENGTH_SHORT).show();
        }


    }

    public void SendEmail()
    {
        _sendingEmail = true;
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        String aEmailList[] = {"irenac@restorationrobotics.com"};
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "ARTAS Consultation");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My message");

        // add attachment
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        String dirpath = cw.getDir("photos", Context.MODE_PRIVATE).getPath();
        File f=new File(dirpath, IMAGENAME_FRONT);
        Uri uri = null;
        File externalFile = null;
        try {
            if (f.exists()) {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                externalFile = saveImageToExternalStorage(b);
            }
        }catch(Exception e){
                e.printStackTrace();
        }

        if (externalFile != null) {
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(externalFile));
        }


        try {
            startActivity(Intent.createChooser(emailIntent, "Send E-Mail"));
//            Toast.makeText(RequestFormActivity.this, "Finished sending ...", Toast.LENGTH_SHORT).show();
//            _sendingEmail = false;
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RequestFormActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    protected File saveImageToExternalStorage(Bitmap b)
    {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            File directoryFile = new File(Environment.getExternalStorageDirectory(), "");
            directoryFile.mkdirs();

            //create file
            File outFile = new File(Environment.getExternalStorageDirectory(), IMAGENAME_FRONT);

            FileOutputStream output = null;
            try {
                output = new FileOutputStream(outFile);
                b.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();
                return outFile;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
//            /**
//             * Get Path
//             */
//
//            Uri selectedImageURI = data.getData();
//            attachmentFile = getRealPathFromURI(selectedImageURI);
//
//
////            String[] filePathColumn = { MediaStore.Images.Media.DATA };
////
////            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
////            cursor.moveToFirst();
////            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////            attachmentFile = cursor.getString(columnIndex);
//
//            if(attachmentFile != null) {
//                Log.i("Attachment Path:", attachmentFile);
//                URI = Uri.parse("file://" + attachmentFile);
//            } else {
//                Log.i("Error", "Attachment file is null");
//            }
////            cursor.close();
//
//            // now send email with this attachment
//            SendEmail();
//        }
//    }

//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        String[] filePathColumn = { MediaStore.Images.ImageColumns.DATA };
//        Cursor cursor = getContentResolver().query(contentURI, filePathColumn, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }

//     public void SelectPhotoFromGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, PICK_FROM_GALLERY);
//    }

}
