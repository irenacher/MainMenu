package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by irenachernyak on 9/23/15.
 */
public class RequestFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_form_layout);

        addItemsToSpinners();
        findViewById(R.id.mainLayout).requestFocus();
    }

    public void addItemsToSpinners()
    {
        Spinner ethnicity_spinner = (Spinner)findViewById(R.id.ethnicity_spinner);
        Spinner gender_spinner = (Spinner)findViewById(R.id.gender_spinner);
        Spinner haircolor_spinner = (Spinner)findViewById(R.id.hcolor_spinner);
        Spinner hairtype_spinner = (Spinner)findViewById(R.id.htype_spinner);

        ArrayAdapter<CharSequence> ethnicity_spinner_adapter = ArrayAdapter.createFromResource(this, R.array.ethnicity_names, android.R.layout.simple_spinner_item);
        ethnicity_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ethnicity_spinner.setAdapter(ethnicity_spinner_adapter);

        ArrayAdapter<CharSequence> gender_spinner_adapter = ArrayAdapter.createFromResource(this, R.array.gender_names, android.R.layout.simple_spinner_item);
        gender_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(gender_spinner_adapter);

        ArrayAdapter<CharSequence> haircolor_spinner_adapter = ArrayAdapter.createFromResource(this, R.array.hair_colors, android.R.layout.simple_spinner_item);
        haircolor_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        haircolor_spinner.setAdapter(haircolor_spinner_adapter);

        ArrayAdapter<CharSequence> hairtype_spinner_adapter = ArrayAdapter.createFromResource(this, R.array.hair_types, android.R.layout.simple_spinner_item);
        hairtype_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
    }
}
