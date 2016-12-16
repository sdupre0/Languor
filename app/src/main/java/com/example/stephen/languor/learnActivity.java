package com.example.stephen.languor;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class learnActivity extends AppCompatActivity {

    //constant strings
    public static final String WORD = "word";
    public static final String INFO = "info";
    //fragments
    CardFrontFragment cardFront;                // <-- it's Minnesota!
    CardBackFragment cardBack;
    //widgets
    FrameLayout card;
    Button prevButt, nextButt;
    //list and iterator
    List<Word> wordList;
    ListIterator<Word> nextWord;
    //others
    Word word;
    boolean showingBack;
    String category;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        DBHandler db = new DBHandler(this); //initialize DB
        category = "";
        sharedPref = getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);
        showingBack = false;

        //user has opened some flashcards, earns "Studious" achievement if hasn't already
        if (!sharedPref.getBoolean(getString(R.string.saved_achieve_studious), false)) {
            editor = sharedPref.edit();
            editor.putBoolean(getString(R.string.saved_achieve_studious), true);
            editor.apply();
        }

        //initialize widgets
        card = (FrameLayout) findViewById(R.id.container);
        prevButt = (Button) findViewById(R.id.prevCardButt);
        nextButt = (Button) findViewById(R.id.nextCardButt);

        //pull category extra from intent and build word list based on it
        if (getIntent().getStringExtra("category") != null) {
            category = getIntent().getStringExtra("category");
            wordList = db.getWordsByCat(category);
        }
        else
            Toast.makeText(getApplication().getBaseContext(),
                    "Card category select error", Toast.LENGTH_SHORT).show(); //error msg

        //randomize order of cards
        long seed = System.nanoTime();
        Collections.shuffle(wordList, new Random(seed));

        //load first word and initialize iterator
        word = wordList.get(0);
        nextWord = wordList.listIterator(1);

        //update card fragments
        cardUpdate();

        //next and previous card button listeners
        prevButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = nextWord.previous();
                cardUpdate();
            }
        });

        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = nextWord.next();
                cardUpdate();
            }
        });

        //swipe listener for flipping card
        card.setOnTouchListener(new OnSwipeTouchListener(learnActivity.this) {
            public void onSwipeLeft() {
                flipCard(0);
            }
            public void onSwipeRight() {
                flipCard(1);
            }
        });
    }

    private void cardUpdate() {
        if (!showingBack) {
            //fill frame layout with front card layout
            if (cardFront == null) {
                cardFront = CardFrontFragment.setFrontWord(word.getWordFR());
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, cardFront)
                        .commit();
            } else {
                //if front was being viewed, replace with updated card
                cardFront = CardFrontFragment.setFrontWord(word.getWordFR());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, cardFront)
                        .commit();
            }
        }
        else {
            //same as above for the back
            if (cardBack == null) {
                cardBack = CardBackFragment.setBackInfo(word.getWordEN(), word.getWordInfo());
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, cardBack)
                        .commit();
            } else {
                cardBack = CardBackFragment.setBackInfo(word.getWordEN(), word.getWordInfo());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, cardBack)
                        .commit();
            }
        }

        //if at end or beginning of word list, disable appropriate button
        if (word == wordList.get(0)) {
            prevButt.setEnabled(false);
        }
        else {
            prevButt.setEnabled(true);
        }
        if (!nextWord.hasNext()) {
            nextButt.setEnabled(false);
        }
        else {
            nextButt.setEnabled(true);
        }
    }

    private void flipCard(int dir) {
        switch (dir) {
            //swipe left
            case 0:
                if (!showingBack) {
                    //animates card flip and switches to back of card fragment
                    getFragmentManager()
                            .beginTransaction()
                            //replace default fragment animations with custom card flippy ones
                            .setCustomAnimations(
                                    R.animator.card_flip_right_in,
                                    R.animator.card_flip_right_out)
                            //sub in back side fragment
                            .replace(R.id.container, CardBackFragment.setBackInfo(word.getWordEN(),
                                    word.getWordInfo()))
                            .commit();

                    showingBack = true;
                }
                break;
            //swipe right
            case 1:
                if (showingBack) {
                    //animates card flip and switches back to front of card
                    getFragmentManager()
                            .beginTransaction()
                            //replace default fragment animations with custom card flippy ones
                            .setCustomAnimations(
                                    R.animator.card_flip_left_in,
                                    R.animator.card_flip_left_out)
                            //sub in back side fragment
                            .replace(R.id.container,
                                    CardFrontFragment.setFrontWord(word.getWordFR()))
                            .commit();

                    showingBack = false;
                }
                break;
        }
    }
}
