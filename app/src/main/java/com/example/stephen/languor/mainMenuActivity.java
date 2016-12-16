package com.example.stephen.languor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class mainMenuActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);

    }

    //when user presses "Settings" button
    public void settingsMenuOpen(View view) {
        Intent intent = new Intent(this, settingsMenuActivity.class);
        startActivity(intent);
    }

    //when user presses "Quiz" button
    public void quizMenuOpen(View view) {
        Intent intent = new Intent(this, quizMenuActivity.class);
        startActivity(intent);
    }

    //when user presses "Rewards" button
    public void achieveMenuOpen(View view) {
        Intent intent = new Intent(this, achieveMenuActivity.class);
        startActivity(intent);
    }

    //when user presses "Study" button
    public void learnMenuOpen(View view) {
        Intent intent = new Intent(this, learnMenuActivity.class);
        startActivity(intent);
    }
}
