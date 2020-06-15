package com.it_uatech;

import com.it_uatech.services.StudentTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.it_uatech.dao_csv")
@Import(com.it_uatech.config.ConfigServices.class)

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        StudentTest test = context.getBean(StudentTest.class);
        test.startTest();
        context.close();
    }
}
