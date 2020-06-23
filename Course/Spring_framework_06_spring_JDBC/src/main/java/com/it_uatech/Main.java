package com.it_uatech;

import com.it_uatech.domain.Person;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.it_uatech.dao.PersonDao;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        PersonDao dao = context.getBean(PersonDao.class);

        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource.getConnection().getMetaData().getURL());
        System.out.println(dataSource.getConnection().getMetaData().getUserName());

        System.out.println("All row counter before insert: "+dao.count());
        dao.insert(new Person(2,"Lolo"));
        dao.insert(new Person(3,"Koko"));
        System.out.println("All row counter after insert: "+dao.count());

        int id = 1;
        System.out.println("Find by id: "+id);
        Person person = dao.getById(id);
        System.out.println(person);

        System.out.println("Find all:");
        List<Person> personList = dao.getAll();
        personList.forEach(System.out::println);

        id = 2;
        System.out.println("Delete person by id: "+id);
        dao.deleteById(id);
        personList = dao.getAll();
        personList.forEach(System.out::println);

        Console.main(args);

        while(true){
            Thread.sleep(1000);
        }
    }
}
