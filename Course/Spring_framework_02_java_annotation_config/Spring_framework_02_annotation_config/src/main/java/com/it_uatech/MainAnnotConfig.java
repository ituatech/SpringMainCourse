package com.it_uatech;

import com.it_uatech.domain.Person;
import com.it_uatech.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.it_uatech")
@PropertySource("classpath:application.properties")
public class MainAnnotConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainAnnotConfig.class);

        PersonService service = context.getBean(PersonService.class);

        Person person = service.getByName("Ivan");
        System.out.println(person);
    }
}
