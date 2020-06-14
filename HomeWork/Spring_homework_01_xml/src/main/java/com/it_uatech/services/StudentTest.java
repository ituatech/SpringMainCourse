package com.it_uatech.services;

import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.entities.Questions;

import java.util.List;
import java.util.Scanner;

public class StudentTest {

    private final ReadFile readFile;

    public StudentTest(ReadFile readFile){
        this.readFile = readFile;
    }

    public void startTest(){
        int count = 0;
        String name;
        String Surname;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name:");
        name = in.next();
        System.out.println("Enter your name:");
        Surname = in.next();
        System.out.println("OK, "+name+" "+Surname+", let start a test!");
        List<Questions> testList = readFile.getQuestionList();
        int countQue = 0;
        for(Questions qTest:testList){
            countQue++;
            String answer;
            String[] answerList = qTest.getAnswer();
            System.out.println(countQue+") " +qTest.getQuestion());
            for (int i=0;i<(answerList.length-1);i++) {
                System.out.println("- " + answerList[i]);
            }
            System.out.println("Choose an answer: ");
            answer = in.next();
            if (answer.equalsIgnoreCase(answerList[answerList.length-1])){count++;}
        }
        System.out.println("Dear, " +name+" "+Surname+", You have "+count+" right answers from "+testList.size());
    }
}
