package com.it_uatech;

import com.it_uatech.services.StudentTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-config.xml");
        StudentTest test = (StudentTest) context.getBean("StudentTest");
        test.startTest();
    }
}
