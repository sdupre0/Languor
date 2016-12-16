package com.example.stephen.languor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class settingsMenuActivity extends AppCompatActivity {

    //widgets
    Button resetButt;
    //prefs
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);
        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);

        resetButt = (Button) findViewById(R.id.resetButt);

        //on click listener for reset button
        resetButt.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear().apply();

                Toast.makeText(settingsMenuActivity.this, R.string.reset_message,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
