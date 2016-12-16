package com.example.stephen.languor;

public class Question {

    //attributes
    private int questID;
    private String questQuest;
    private Option questOpt1 = new Option();
    private Option questOpt2 = new Option();
    private Option questOpt3 = new Option();
    private Option questOpt4 = new Option();
    private int questLvl;

    //constructors
    public Question(int questID, String questQuest, String questOpt1, String questOpt2,
                    String questOpt3, String questOpt4, int questCorrect, int questLvl) {
        this.questID = questID;
        this.questQuest = questQuest;
        this.setQuestOpt1(questOpt1, false);
        this.setQuestOpt2(questOpt2, false);
        this.setQuestOpt3(questOpt3, false);
        this.setQuestOpt4(questOpt4, false);
        this.questLvl = questLvl;

        switch (questCorrect) {
            case 1:
                this.getQuestOpt1().setCorrect(true);
                break;
            case 2:
                this.getQuestOpt2().setCorrect(true);
                break;
            case 3:
                this.getQuestOpt3().setCorrect(true);
                break;
            case 4:
                this.getQuestOpt4().setCorrect(true);
                break;
        }
    }

    public Question() {
        this.questID = 0;
        this.questQuest = "";
        this.setQuestOpt1("", false);
        this.setQuestOpt2("", false);
        this.setQuestOpt3("", false);
        this.setQuestOpt4("", false);
        this.questLvl = 0;
    }

    //accessors
    public String getQuestQuest() {
        return questQuest;
    }

    public Option getQuestOpt1() {
        return questOpt1;
    }

    public Option getQuestOpt2() {
        return questOpt2;
    }

    public Option getQuestOpt3() {
        return questOpt3;
    }

    public Option getQuestOpt4() {
        return questOpt4;
    }

    public void setQuestOpt1(String text, boolean correct) {
        this.questOpt1.setQuestOptText(text);
        this.questOpt1.setCorrect(correct);
    }

    public void setQuestOpt2(String text, boolean correct) {
        this.questOpt2.setQuestOptText(text);
        this.questOpt1.setCorrect(correct);
    }

    public void setQuestOpt3(String text, boolean correct) {
        this.questOpt3.setQuestOptText(text);
        this.questOpt3.setCorrect(correct);
    }

    public void setQuestOpt4(String text, boolean correct) {
        this.questOpt4.setQuestOptText(text);
        this.questOpt4.setCorrect(correct);
    }
}
