package com.it_uatech;

import com.it_uatech.domain.Person;
import com.it_uatech.repostory.PersonRepository;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.insert(new Person("Vasiok"));

        Person nullPerson = repository.getById(1);

        System.out.println(nullPerson);

        nullPerson = repository.getByName("Vasiok");

        System.out.println(nullPerson);

        Console.main(args);
    }
}
