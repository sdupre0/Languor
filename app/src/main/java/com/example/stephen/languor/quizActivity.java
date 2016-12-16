package com.example.stephen.languor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static android.graphics.Color.*;
import static android.os.SystemClock.sleep;
import static java.lang.String.valueOf;

public class quizActivity extends AppCompatActivity {

    int numCorr; //keep track of correct answers
    final long ANSWER_TIME = 1000; //time in ms to wait before moving to next question
    public static final String EXTRA_FIRST_100 = "first_complete";
    //Widgets
    View quizView;
    Bundle b;
    TextView quizQuestion;
    Button quizOpt1;
    Button quizOpt2;
    Button quizOpt3;
    Button quizOpt4;
    Button quitButt;
    Question question;
    //List and iterator
    List<Question> questList;
    ListIterator<Question> nextQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        DBHandler db = new DBHandler(this); //initialize DB
        int value; //passed in level int to determine loaded questions
        numCorr = 0; //user has not correctly answered any questions yet

        b = getIntent().getExtras();
        //retrieve level and score extras from intent and determine question list by level
        if (b != null) {
            value = b.getInt(quizMenuActivity.EXTRA_LEVEL);
            questList = db.getQuestsByLvl(value);
        }
        else
        Toast.makeText(getApplication().getBaseContext(),
                "Level select error", Toast.LENGTH_SHORT).show(); //error msg

        //initialize objects for option buttons and question text
        quizView = findViewById(R.id.activity_quiz);
        quizQuestion = (TextView) findViewById(R.id.quizQuestTV);
        quizOpt1 = (Button) findViewById(R.id.quizOpt1Butt);
        quizOpt2 = (Button) findViewById(R.id.quizOpt2Butt);
        quizOpt3 = (Button) findViewById(R.id.quizOpt3Butt);
        quizOpt4 = (Button) findViewById(R.id.quizOpt4Butt);
        quitButt = (Button) findViewById(R.id.quizBackButt);

        //determine question list by level selected
        /*switch (value) {
            case 0:
                //problem area -- should never happen if level extra worked
                break;
            case 1:
                //level 1 q's
                questList = db.getQuestsByLvl(1);
                break;
        }*/

        //randomize order of questions
        long seed = System.nanoTime();
        Collections.shuffle(questList, new Random(seed));

        //load first question in list and initialize iterator
        question = questList.get(0);
        nextQuest = questList.listIterator(1);

        //load question text and options into TextView and Buttons
        quizCont();

        quitButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return to quiz menu with result not OK so it doesn't update score
                Intent intent = new Intent(quizActivity.this, quizMenuActivity.class);
                setResult(RESULT_CANCELED, intent);
                startActivity(intent);
            }
        });
    }

    //when user selects option button
    public void optSelect(View view) {
        switch (view.getId()) {
            case R.id.quizOpt1Butt:
                if (question.getQuestOpt1().isCorrect()) {
                    //right answer feedback
                    Toast.makeText(quizActivity.this, R.string.right, Toast.LENGTH_SHORT).show();
                    numCorr++; //+1 correct answer
                }
                else {
                    //wrong answer feedback
                    Toast.makeText(quizActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.quizOpt2Butt:
                if (question.getQuestOpt2().isCorrect()) {
                    //right answer feedback
                    Toast.makeText(quizActivity.this, R.string.right, Toast.LENGTH_SHORT).show();
                    numCorr++; //+1 correct answer
                }
                else {
                    //wrong answer feedback
                    Toast.makeText(quizActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.quizOpt3Butt:
                if (question.getQuestOpt3().isCorrect()) {
                    //right answer feedback
                    Toast.makeText(quizActivity.this, R.string.right, Toast.LENGTH_SHORT).show();
                    numCorr++; //+1 correct answer
                }
                else {
                    //wrong answer feedback
                    Toast.makeText(quizActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.quizOpt4Butt:
                if (question.getQuestOpt4().isCorrect()) {
                    //right answer feedback
                    Toast.makeText(quizActivity.this, R.string.right, Toast.LENGTH_SHORT).show();
                    numCorr++; //+1 correct answer
                }
                else {
                    //wrong answer feedback
                    Toast.makeText(quizActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                }
                break;
        }

        //TODO: look into invalidation to force UI updating BEFORE moving to next question
        //sleep(ANSWER_TIME); //give the user time to see the result

        //check for more questions in list
        if (nextQuest.hasNext()) {

            question = nextQuest.next(); //list iterates and returns next element
            quizCont(); //call function that sets TV and Buttons to next question
        }
        else {
            quizFinish(b); //if no more questions, quiz finishes
        }
    }

    //function to call after initialization and after every OnClick answer check
    //loads question text and option text into TV and buttons
    public void quizCont() {
        quizQuestion.setText(question.getQuestQuest());
        quizOpt1.setText(question.getQuestOpt1().getQuestOptText());
        quizOpt2.setText(question.getQuestOpt2().getQuestOptText());
        quizOpt3.setText(question.getQuestOpt3().getQuestOptText());
        quizOpt4.setText(question.getQuestOpt4().getQuestOptText());
    }

    //function to call when question list is exhausted
    //causes summary Layout to become visible and display score and button
    // to return to quiz menu
    public void quizFinish(Bundle b) {
        //show quiz finish overlay
        RelativeLayout overlay = (RelativeLayout) findViewById(R.id.quizEndOverlay);
        overlay.setVisibility(View.VISIBLE);

        //initialize text fields and button
        TextView maxScore = (TextView) findViewById(R.id.scoreConstTV);
        TextView score = (TextView) findViewById(R.id.scoreVariableTV);

        //set score max and what the user actually got
        maxScore.setText(valueOf(b.getInt(quizMenuActivity.EXTRA_MAX)));
        score.setText(valueOf(numCorr));

        //give achievement notice and set extra if first time getting 100%
        if (b.getInt(quizMenuActivity.EXTRA_SCORE) != b.getInt(quizMenuActivity.EXTRA_MAX)) {
            if (numCorr == b.getInt(quizMenuActivity.EXTRA_MAX)) {
                Toast.makeText(quizActivity.this, getString(R.string.achieve_unlock_toast),
                        Toast.LENGTH_SHORT).show();
                b.putBoolean(EXTRA_FIRST_100, true);
            }
        }
    }

    //actually go back to quiz menu
    public void toMenu(View view) {
        b.putInt(quizMenuActivity.EXTRA_SCORE, numCorr); //also pass user score into intent
        Intent intent = new Intent(quizActivity.this, quizMenuActivity.class);
        intent.putExtras(b);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        setResult(RESULT_OK, intent); //tells OnActivityReturn to update score
        finish();
    }
}
