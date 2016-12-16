package com.example.stephen.languor;

public class Word {

    //attributes
    private String wordFR;
    private String wordEN;
    private String wordInfo;
    private String wordCat;

    //constructors
    public Word(String wordFR, String wordEN, String wordInfo, String wordCat) {
        this.wordFR = wordFR;
        this.wordEN = wordEN;
        this.wordInfo = wordInfo;
        this.wordCat = wordCat;
    }

    public Word() {
        this.wordFR = "";
        this.wordEN = "";
        this.wordInfo = "";
        this.wordCat = "";
    }

    //accessors
    public String getWordFR() {
        return wordFR;
    }

    public String getWordEN() {
        return wordEN;
    }

    public String getWordInfo() {
        return wordInfo;
    }
}
