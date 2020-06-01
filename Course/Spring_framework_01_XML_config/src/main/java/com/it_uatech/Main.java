package com.it_uatech;

import com.it_uatech.domain.Person;
import com.it_uatech.service.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonService personService = context.getBean(PersonService.class);
        Person person = personService.getByName("Ivan");
        System.out.println(person);
    }
}
