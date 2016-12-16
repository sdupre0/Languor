//based on example code at http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/

package com.example.stephen.languor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteAssetHelper {

    //attributes
    private static final int dbVer = 6; //database version
    private static final String dbName = "languor.db"; //database name
    private static final String tableQuests = "quests"; //questions table name
    private static final String tableWords = "words"; //words table name

    //question column names
    //most not needed (at least at present)
    /*private static final String key_id = "questID";
    private static final String key_quest = "questQuest";
    private static final String key_opt1 = "questOpt1";
    private static final String key_opt2 = "questOpt2";
    private static final String key_opt3 = "questOpt3";
    private static final String key_opt4 = "questOpt4";
    private static final String key_correct = "questCorrect";*/
    private static final String key_lvl = "questLvl";

    //word column names
    //most not needed (at least at present)
    /*private static final String key_word_fr = "wordFR";
    private static final String key_word_en = "wordEN";
    private static final String key_extra = "wordInfo";*/
    private static final String key_cat = "wordCat";

    //constructor
    public DBHandler(Context context) {
        super(context, dbName, null, dbVer);
        setForcedUpgrade();
    }

    //read all questions of one level
    public List<Question> getQuestsByLvl(int level) {
        List<Question> questList = new ArrayList<>();

        String query = "SELECT * FROM " + tableQuests + " WHERE " + key_lvl + " = " + level;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7)));
                questList.add(question);
            } while (cursor.moveToNext());
        }
        return questList;
    }

    //get count of questions of a certain level
    public int getQuestCountByLvl (int level) {
        String query = "SELECT * FROM " + tableQuests + " WHERE " + key_lvl + " = " + level;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount();
    }

    //get all words of one category
    public List<Word> getWordsByCat(String category) {
        List<Word> wordList = new ArrayList<>();

        String q = "SELECT * FROM " + tableWords + " WHERE " + key_cat + " = '" + category + "'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3));
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        return wordList;
    }

    //get count of words of a certain category
    public int getWordCountByCat (String category) {
        String q = "SELECT * FROM " + tableWords + " WHERE " + key_cat + " = '" + category + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);

        return cursor.getCount();
    }
}