package com.it_uatech;

import com.it_uatech.domain.Person;
import com.it_uatech.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.it_uatech.config")
@Configuration
public class MainJavaConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainJavaConfig.class);

        PersonService service = context.getBean(PersonService.class);

        Person person = service.getByName("Ivan");
        System.out.println(person);
    }
}
