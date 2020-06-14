package com.it_uatech;

import com.it_uatech.services.StudentTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        StudentTest test = ctx.getBean(StudentTest.class);
        test.startTest();
    }

}
