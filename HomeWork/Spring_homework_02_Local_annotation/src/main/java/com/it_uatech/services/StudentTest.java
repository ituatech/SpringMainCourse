package com.it_uatech.services;

import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.domain.Questions;
import com.it_uatech.services.config.ConfigServices;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class StudentTest {

    private final ReadFile readFile;
    private final MessageSource message;
    private final java.util.Locale locale;
    private double testRes;

    public StudentTest(ReadFile readFile,MessageSource message,java.util.Locale locale,double testRes){
        this.readFile = readFile;
        this.message = message;
        this.locale = locale;
        this.testRes = testRes;
    }

    public void startTest(){
        int count = 0;
        String name;
        String surname;
            Scanner in = ConfigServices.SCANNER;

            System.out.println(message.getMessage("user.name", null, locale));
            name = in.next();
            System.out.println(message.getMessage("user.surname", null, locale));
            surname = in.next();
            System.out.println(message.getMessage("test.start", new String[]{name, surname}, locale));
            List<Questions> testList = readFile.getQuestionList();
            int countQue = 0;
            for (Questions qTest : testList) {
                countQue++;
                String answer;
                String[] answerList = qTest.getAnswer();
                System.out.println(countQue + ") " + qTest.getQuestion());
                for (int i = 0; i < (answerList.length - 1); i++) {
                    System.out.println("- " + answerList[i]);
                }
                System.out.println(message.getMessage("answer.choise", null, locale));
                answer = in.next();
                if (answer.equalsIgnoreCase(answerList[answerList.length - 1])) {
                    count++;
                }
            }
            System.out.println(message.getMessage("user.result", new Object[]{name, surname, count, testList.size()}, locale));
            if ((count/testList.size())>testRes){
                System.out.println(message.getMessage("user.resultGood", null, locale));
            }else{
                System.out.println(message.getMessage("user.resultBad", null, locale));
            }
    }

    public double getTestRes() {
        return testRes;
    }

    public Locale getLocale() {
        return locale;
    }

    public MessageSource getMessage() {
        return message;
    }

    public ReadFile getReadFile() {
        return readFile;
    }
}
