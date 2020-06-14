package com.it_uatech.domain;

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

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String toString(){
        String data = question +" "+answer[0]+" "+answer[1]+" "+answer[2];

        return data;
    }
}
