package com.example.stephen.languor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class learnMenuActivity extends AppCompatActivity {

    //constant values
    private static final String EXTRA_CATEGORY = "category";

    //widgets
    TextView numCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_menu);
        DBHandler db = new DBHandler(this); //initialize DB

        //set number of cards in each text view
        numCards = (TextView) findViewById(R.id.learnCat1TV);
        numCards.setText(valueOf(db.getWordCountByCat("pronoun")));

        numCards = (TextView) findViewById(R.id.learnCat2TV);
        numCards.setText(valueOf(db.getWordCountByCat("article")));

        numCards = (TextView) findViewById(R.id.learnCat3TV);
        numCards.setText(valueOf(db.getWordCountByCat("conjunction")));

        numCards = (TextView) findViewById(R.id.learnCat4TV);
        numCards.setText(valueOf(db.getWordCountByCat("preposition")));

        numCards = (TextView) findViewById(R.id.learnCat5TV);
        numCards.setText(valueOf(db.getWordCountByCat("verb")));

        numCards = (TextView) findViewById(R.id.learnCat6TV);
        numCards.setText(valueOf(db.getWordCountByCat("question")));

        numCards = (TextView) findViewById(R.id.learnCat7TV);
        numCards.setText(valueOf(db.getWordCountByCat("adjective")));

        numCards = (TextView) findViewById(R.id.learnCat8TV);
        numCards.setText(valueOf(db.getWordCountByCat("number")));

        numCards = (TextView) findViewById(R.id.learnCat9TV);
        numCards.setText(valueOf(db.getWordCountByCat("people")));

        numCards = (TextView) findViewById(R.id.learnCat10TV);
        numCards.setText(valueOf(db.getWordCountByCat("animal")));

        numCards = (TextView) findViewById(R.id.learnCat11TV);
        numCards.setText(valueOf(db.getWordCountByCat("time")));

        numCards = (TextView) findViewById(R.id.learnCat12TV);
        numCards.setText(valueOf(db.getWordCountByCat("adverb")));
    }

    //when user selects a card category
    public void learnOpen(View view) {
        //create intent
        Intent intent = new Intent(this, learnActivity.class);

        //what category was selected?
        switch (view.getId()) {
            case R.id.learnCat1Butt:
                intent.putExtra(EXTRA_CATEGORY, "pronoun");
                break;
            case R.id.learnCat2Butt:
                intent.putExtra(EXTRA_CATEGORY, "article");
                break;
            case R.id.learnCat3Butt:
                intent.putExtra(EXTRA_CATEGORY, "conjunction");
                break;
            case R.id.learnCat4Butt:
                intent.putExtra(EXTRA_CATEGORY, "preposition");
                break;
            case R.id.learnCat5Butt:
                intent.putExtra(EXTRA_CATEGORY, "verb");
                break;
            case R.id.learnCat6Butt:
                intent.putExtra(EXTRA_CATEGORY, "question");
                break;
            case R.id.learnCat7Butt:
                intent.putExtra(EXTRA_CATEGORY, "adjective");
                break;
            case R.id.learnCat8Butt:
                intent.putExtra(EXTRA_CATEGORY, "number");
                break;
            case R.id.learnCat9Butt:
                intent.putExtra(EXTRA_CATEGORY, "people");
                break;
            case R.id.learnCat10Butt:
                intent.putExtra(EXTRA_CATEGORY, "animal");
                break;
            case R.id.learnCat11Butt:
                intent.putExtra(EXTRA_CATEGORY, "time");
                break;
            case R.id.learnCat12Butt:
                intent.putExtra(EXTRA_CATEGORY, "adverb");
                break;
        }
        //ENGAGE
        startActivity(intent);
    }
}
