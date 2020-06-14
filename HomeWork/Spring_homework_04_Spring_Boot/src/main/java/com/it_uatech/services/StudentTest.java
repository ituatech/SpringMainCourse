package com.it_uatech.services;

import com.it_uatech.config.AppConfig;
import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.domain.Questions;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Scanner;

public class StudentTest {

    private final ReadFile readFile;
    private final MessageSource message;
    private final Locale localeImpl;
    private final AppConfig appConfig;

    public StudentTest(ReadFile readFile, MessageSource message, Locale localeImpl, AppConfig appConfig){
        this.readFile = readFile;
        this.message = message;
        this.localeImpl = localeImpl;
        this.appConfig = appConfig;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public Locale getLocale() {
        return localeImpl;
    }

    public MessageSource getMessage() {
        return message;
    }

    public ReadFile getReadFile() {
        return readFile;
    }

    public void startTest() {
        java.util.Locale locale = null;
        StringBuilder name = new StringBuilder();
        String toStringName = null;
        try(Scanner in = new Scanner(System.in)) {
            String language = localeBuilder(in);
            locale = localeImpl.buildLocate(language);
            System.out.println(message.getMessage("user.name", null, locale));
            name.append(in.next());
            name.append(" ");
            System.out.println(message.getMessage("user.surname", null, locale));
            name.append(in.next());
            toStringName = name.toString();
            System.out.println(message.getMessage("test.start", new String[]{toStringName}, locale));

            List<Questions> testList = readFile.getQuestionList(locale);
            int count = 0;
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
            System.out.println(message.getMessage("user.result", new Object[]{toStringName, count, testList.size()}, locale));
            if ((count / testList.size()) > appConfig.getGoodPersent()) {
                System.out.println(message.getMessage("user.resultGood", null, locale));
            } else {
                System.out.println(message.getMessage("user.resultBad", null, locale));
            }
        }catch (Exception ex){ex.printStackTrace();}
    }

    private String localeBuilder(Scanner in){
        System.out.println("Enter you language: en");
        System.out.println("Schreiben Sie bitte Ihre Sprache: de");
        System.out.println("Введите ваш язык: ru");
        String lang = in.next();

        if (lang.equalsIgnoreCase("en")) {
            return "en";
        } else if (lang.equalsIgnoreCase("de")) {
            return "de";
        } else if (lang.equalsIgnoreCase("ru")) {
            return "ru";
        } else {
            return "en";
        }
    }

}
