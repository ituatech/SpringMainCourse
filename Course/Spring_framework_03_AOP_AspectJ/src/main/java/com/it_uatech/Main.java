package com.it_uatech;

import com.it_uatech.service.PersonService;
import org.springframework.context.annotation.*;
import com.it_uatech.domain.Person;

@EnableAspectJAutoProxy
//@EnableLoadTimeWeaving
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person person = service.getByName("Petro");
        System.out.println("########################################################");
        System.out.println("Result of execution PersonService: " + person);
    }
}
