package com.example.stephen.languor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import static java.lang.String.valueOf;

public class quizMenuActivity extends AppCompatActivity {

    //tbh not sure but we did this for the 4800 lab so SURE WHY NOT
    private static final int REQUEST_CODE_SETTINGS = 0;
    //these constants can't be accessed in quizActivity to pull extras so why bother??
    public static final String EXTRA_LEVEL = "level";
    public static final String EXTRA_SCORE = "score";
    public static final String EXTRA_MAX = "max_score";
    int currScore; //current score of selected level
    int maxScore; //max score
    //View stuff
    TextView currScoreTV;
    TextView maxScoreTV;
    //ImageView levelIcon;

    //prefs man!
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        DBHandler db = new DBHandler(this); //initialize DB
        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);

        //load saved and max scores
        //level 1
        TextView levelOneScore = (TextView) findViewById(R.id.quizLv1ScoreTV);
        currScore = sharedPref.getInt(getString(R.string.saved_score_1), 0);
        levelOneScore.setText(valueOf(currScore)); //saved score
        maxScoreTV = (TextView) findViewById(R.id.quizLvl1MaxScoreTV);
        maxScore = db.getQuestCountByLvl(1);
        maxScoreTV.setText(valueOf(maxScore)); //max score
        //level 2
        TextView levelTwoScore = (TextView) findViewById(R.id.quizLv2ScoreTV);
        currScore = sharedPref.getInt(getString(R.string.saved_score_2), 0);
        levelTwoScore.setText(valueOf(currScore));
        maxScoreTV = (TextView) findViewById(R.id.quizLvl2MaxScoreTV);
        maxScore = db.getQuestCountByLvl(2);
        maxScoreTV.setText(valueOf(maxScore));
        //level 3
        TextView levelThreeScore = (TextView) findViewById(R.id.quizLv3ScoreTV);
        currScore = sharedPref.getInt(getString(R.string.saved_score_3), 0);
        levelThreeScore.setText(valueOf(currScore));
        maxScoreTV = (TextView) findViewById(R.id.quizLvl3MaxScoreTV);
        maxScore = db.getQuestCountByLvl(3);
        maxScoreTV.setText(valueOf(maxScore));
        //level 4
        TextView levelFourScore = (TextView) findViewById(R.id.quizLv4ScoreTV);
        currScore = sharedPref.getInt(getString(R.string.saved_score_4), 0);
        levelFourScore.setText(valueOf(currScore));
        maxScoreTV = (TextView) findViewById(R.id.quizLvl4MaxScoreTV);
        maxScore = db.getQuestCountByLvl(4);
        maxScoreTV.setText(valueOf(maxScore));
    }

    //when user selects a quiz
    public void quizOpen(View view) {
        //create intent to zoom off to quizActivity
        Intent intent = new Intent(this, quizActivity.class);
        Bundle b = new Bundle();
        //what level was selected?
        switch(view.getId()) {
            case R.id.quizLv1Butt:
                //pull current data for level 1
                currScoreTV = (TextView) findViewById(R.id.quizLv1ScoreTV);
                b.putInt(EXTRA_LEVEL, 1); //pass chosen level into intent
                b.putInt(EXTRA_SCORE, currScore); //also pass current score
                b.putInt(EXTRA_MAX, maxScore); //also pass max score
                break;
            case R.id.quizLv2Butt:
                //same for level 2
                currScoreTV = (TextView) findViewById(R.id.quizLv2ScoreTV);
                b.putInt(EXTRA_LEVEL, 2); //pass chosen level into intent
                b.putInt(EXTRA_SCORE, currScore); //also pass current score
                b.putInt(EXTRA_MAX, maxScore); //also pass max score
                break;
            case R.id.quizLv3Butt:
                //level 3
                currScoreTV = (TextView) findViewById(R.id.quizLv3ScoreTV);
                b.putInt(EXTRA_LEVEL, 3); //pass chosen level into intent
                b.putInt(EXTRA_SCORE, currScore); //also pass current score
                b.putInt(EXTRA_MAX, maxScore); //also pass max score
                break;
            case R.id.quizLv4Butt:
                //level 4
                currScoreTV = (TextView) findViewById(R.id.quizLv4ScoreTV);
                b.putInt(EXTRA_LEVEL, 4); //pass chosen level into intent
                b.putInt(EXTRA_SCORE, currScore); //also pass current score
                b.putInt(EXTRA_MAX, maxScore); //also pass max score
                break;
        }
        //and we're a go
        intent.putExtras(b);
        startActivityForResult(intent, REQUEST_CODE_SETTINGS);
    }

    //when returning from quiz, check if user finished or quit
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            //user quit, they win nothing, they lose, good day sir
            return;
        }
        if (requestCode == REQUEST_CODE_SETTINGS) {
            if (data == null) //what the heck happened man you broke it
                return;
            update(data); //if everything works right it'll get here and ENGAGE UPDATE
        }
    }

    //change player score if necessary
    private void update(Intent i) {
        //get returned score from intent
        int score = i.getIntExtra(quizMenuActivity.EXTRA_SCORE, 0);
        //if user's score is higher than their last saved score, overwrite
        if (score > currScore) {
            //currScoreTV.setText(valueOf(score));
            //save score
            SharedPreferences.Editor editor = sharedPref.edit();
            switch (i.getIntExtra(quizMenuActivity.EXTRA_LEVEL, 0)) {
                case 0:
                    //yeah, rip
                    break;
                case 1:
                    //set completed boolean to true in saved prefs if not already
                    if (i.getBooleanExtra(quizActivity.EXTRA_FIRST_100, false)) {
                        editor.putBoolean(getString(R.string.saved_comp_1), true);
                    }
                    //set textview to score
                    currScoreTV.setText(valueOf(score));
                    //save the score
                    editor.putInt(getString(R.string.saved_score_1), score);
                    break;
                case 2:
                    //same as above but for 2
                    if (i.getBooleanExtra(quizActivity.EXTRA_FIRST_100, false)) {
                        editor.putBoolean(getString(R.string.saved_comp_2), true);
                    }
                    currScoreTV.setText(valueOf(score));
                    editor.putInt(getString(R.string.saved_score_2), score);
                    break;
                case 3:
                    //level 3
                    if (i.getBooleanExtra(quizActivity.EXTRA_FIRST_100, false)) {
                        editor.putBoolean(getString(R.string.saved_comp_3), true);
                    }
                    currScoreTV.setText(valueOf(score));
                    editor.putInt(getString(R.string.saved_score_3), score);
                    break;
                case 4:
                    //level 4
                    if (i.getBooleanExtra(quizActivity.EXTRA_FIRST_100, false)) {
                        editor.putBoolean(getString(R.string.saved_comp_4), true);
                    }
                    currScoreTV.setText(valueOf(score));
                    editor.putInt(getString(R.string.saved_score_4), score);
                    break;
            }
            editor.apply();
        }
    }
}
