package com.it_uatech.domain;

public class Questions {

    private String question;
    private String[] answer;
    private String rightAnswer;

    public Questions(String question, String[] answer, String rightAnswer){
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
    }

    public String[] getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder(question);
        for (String readAnswer : answer){
            builder.append(" ");
            builder.append(readAnswer);
        }
        return builder.toString();
    }
}
