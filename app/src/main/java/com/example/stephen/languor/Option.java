package com.example.stephen.languor;

public class Option {
    private String questOptText;
    private boolean correct;
    public Option() {
        this.questOptText = "";
        this.correct = false;
    }
    public String getQuestOptText() {
        return questOptText;
    }
    public void setQuestOptText(String questOptText) {
        this.questOptText = questOptText;
    }
    public boolean isCorrect() {
        return correct;
    }
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
