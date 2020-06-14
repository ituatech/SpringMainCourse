package com.it_uatech.entities;

public class Questions {

    private String question;
    private String[] answer;

    public Questions(String question, String[] answer){
        this.question = question;
        this.answer = answer;
    }

    public String[] getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String toString(){
        return question +" "+answer[0]+" "+answer[1]+" "+answer[2];
    }
}
