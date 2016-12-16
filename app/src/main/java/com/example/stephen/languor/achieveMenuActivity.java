package com.example.stephen.languor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class achieveMenuActivity extends AppCompatActivity {

    //pref file
    SharedPreferences sharedPref;
    //widgets
    LinearLayout achieveStudy, achieveQLv1, achieveQLv2, achieveQLv3, achieveQLv4;
    TextView achieveStudyDesc, achieveQLv1Desc, achieveQLv2Desc, achieveQLv3Desc, achieveQLv4Desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_menu);
        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);

        //alllllllll the widgets
        //layouts
        achieveStudy = (LinearLayout) findViewById(R.id.achieveStudyLL);
        achieveQLv1 = (LinearLayout) findViewById(R.id.achieve1stQLvlLL);
        achieveQLv2 = (LinearLayout) findViewById(R.id.achieve2ndQLvlLL);
        achieveQLv3 = (LinearLayout) findViewById(R.id.achieve3rdQLvlLL);
        achieveQLv4 = (LinearLayout) findViewById(R.id.achieve4thQLvlLL);
        //text
        achieveStudyDesc = (TextView) findViewById(R.id.achieveStudyDesc);
        achieveQLv1Desc = (TextView) findViewById(R.id.achieve1stQLvlDesc);
        achieveQLv2Desc = (TextView) findViewById(R.id.achieve2ndQLvlDesc);
        achieveQLv3Desc = (TextView) findViewById(R.id.achieve3rdQLvlDesc);
        achieveQLv4Desc = (TextView) findViewById(R.id.achieve4thQLvlDesc);

        //check for all the cheevos the user already has and edit the Views accordingly
        if (sharedPref.getBoolean(getString(R.string.saved_achieve_studious), false)) {
            achieveStudy.setAlpha(1.0f);
            achieveStudyDesc.setText(getString(R.string.achieve_study_desc));
        }
        if (sharedPref.getBoolean(getString(R.string.saved_comp_1), false)) {
            achieveQLv1.setAlpha(1.0f);
            achieveQLv1Desc.setText(getString(R.string.achieve1_desc));
        }
        if (sharedPref.getBoolean(getString(R.string.saved_comp_2), false)) {
            achieveQLv1.setAlpha(1.0f);
            achieveQLv1Desc.setText(getString(R.string.achieve2_desc));
        }
        if (sharedPref.getBoolean(getString(R.string.saved_comp_3), false)) {
            achieveQLv1.setAlpha(1.0f);
            achieveQLv1Desc.setText(getString(R.string.achieve3_desc));
        }
        if (sharedPref.getBoolean(getString(R.string.saved_comp_4), false)) {
            achieveQLv1.setAlpha(1.0f);
            achieveQLv1Desc.setText(getString(R.string.achieve4_desc));
        }
    }
}
