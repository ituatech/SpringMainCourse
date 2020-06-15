package com.it_uatech.services;

import com.it_uatech.entities.Questions;

import java.util.List;
import java.util.Scanner;

public class StudentTest {

    private final ReadFile questions;

    public StudentTest(ReadFile questions){
        this.questions = questions;
    }

    public void startTest(){
        StringBuilder builder = new StringBuilder();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your first name:");
        builder.append(in.next());
        builder.append(" ");
        System.out.println("Enter your second name:");
        builder.append(in.next());
        System.out.println("OK, "+builder.toString()+", let start a test!");

        List<Questions> testList = questions.getQuestionList();
        int questionCount = 0;
        int rightAnswerCount = 0;
        for(Questions qTest:testList){
            String[] answerList = qTest.getAnswer();
            System.out.println(++questionCount+") " +qTest.getQuestion());

            for (String answerSuppose : answerList) {
                System.out.println("- " + answerSuppose);
            }

            System.out.println("Choose an answer: ");
            String answer = in.next();
            if (answer.equalsIgnoreCase(qTest.getRightAnswer())){
                rightAnswerCount++;
            }
        }
        System.out.println("Dear, " +builder.toString()+", You have "+rightAnswerCount+" right answers from "+testList.size());
    }
}
