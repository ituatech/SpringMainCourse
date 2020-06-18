package com.it_uatech;

import com.it_uatech.services.StudentTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan("com.it_uatech.config")
public class Main {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Main.class, args);
        StudentTest test = context.getBean(StudentTest.class);
        test.startTest();
    }
}
