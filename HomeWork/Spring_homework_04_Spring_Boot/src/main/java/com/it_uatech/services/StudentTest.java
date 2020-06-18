package com.it_uatech.services;

import com.it_uatech.config.PropertyResult;
import com.it_uatech.domain.Questions;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class StudentTest {

    private final ReadFile questions;
    private final MessageSource message;
    private double testGoodResult;

    public StudentTest(ReadFile questions, MessageSource message, PropertyResult propertyResult) {
        this.questions = questions;
        this.message = message;
        this.testGoodResult = propertyResult.getGoodPercent();
    }

    public void startTest() {
        int rightAnswerCount;
        try (Scanner in = new Scanner(System.in)) {
            StringBuilder builder = new StringBuilder();
            Locale locale = localeBuilder(in);

            System.out.println(message.getMessage("user.name", null, locale));
            builder.append(in.next());
            builder.append(" ");
            System.out.println(message.getMessage("user.surname", null, locale));
            builder.append(in.next());
            System.out.println(message.getMessage("test.start", new String[]{builder.toString()}, locale));

            List<Questions> testList = questions.getQuestionList(locale);
            int questionCount = 0;
            rightAnswerCount = 0;
            for (Questions qTest : testList) {
                String[] answerList = qTest.getAnswer();
                System.out.println(++questionCount + ") " + qTest.getQuestion());

                for (String answerSuppose : answerList) {
                    System.out.println("- " + answerSuppose);
                }

                System.out.println(message.getMessage("answer.choice", null, locale));
                String answer = in.next();
                if (answer.equalsIgnoreCase(qTest.getRightAnswer())) {
                    rightAnswerCount++;
                }
            }

            System.out.println(message.getMessage("user.result", new Object[]{builder.toString(), rightAnswerCount, testList.size()}, locale));
            if ((double) (rightAnswerCount / testList.size()) > testGoodResult) {
                System.out.println(message.getMessage("user.resultGood", null, locale));
            } else {
                System.out.println(message.getMessage("user.resultBad", null, locale));
            }
        }
    }

    private Locale localeBuilder(Scanner in) {
        System.out.println("Enter you language: en");
        System.out.println("Schreiben Sie bitte Ihre Sprache: de");
        System.out.println("Введите ваш язык: ru");
        String selectedLanguage = in.next();

        if (!selectedLanguage.equalsIgnoreCase("en") &&
                !selectedLanguage.equalsIgnoreCase("de") &&
                !selectedLanguage.equalsIgnoreCase("ru")) {
            selectedLanguage = "en";
        }
        return new Locale.Builder().setLanguage(selectedLanguage).build();
    }

    public ReadFile getQuestions() {
        return questions;
    }

    public MessageSource getMessage() {
        return message;
    }

    public double getTestGoodResult() {
        return testGoodResult;
    }
}
