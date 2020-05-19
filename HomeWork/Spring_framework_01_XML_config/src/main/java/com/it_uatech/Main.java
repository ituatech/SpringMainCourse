package com.it_uatech;

import com.it_uatech.domain.Person;
import com.it_uatech.service.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        Person ivan;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonService personService = context.getBean(PersonService.class);
        ivan=personService.getByName("you welcome baby");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
