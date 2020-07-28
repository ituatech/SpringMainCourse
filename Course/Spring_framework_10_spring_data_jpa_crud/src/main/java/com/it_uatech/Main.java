package com.it_uatech;

import com.it_uatech.domain.Email;
import com.it_uatech.domain.Person;
import com.it_uatech.repository.EmailRepository;
import com.it_uatech.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void init() {
        personRepository.save(new Person("Pushkin"));
        personRepository.save(new Person("Masha"));
        personRepository.save(new Person("Gosha"));
        List<Person> list = personRepository.findAll();
        list.forEach(System.out::println);

        Person person = personRepository.findByName("Pushkin");
        System.out.println(person);
        System.out.println("##############################################");

        emailRepository.save(new Email("dyatel.gmail.com"));
        emailRepository.save(new Email("alator.gmail.com"));
        emailRepository.save(new Email("fiksik.gmail.com"));
        emailRepository.save(new Email("xerox.gmail.com"));
        emailRepository.save(new Email("wadik.gmail.com"));
        emailRepository.save(new Email("loha.gmail.com"));
        emailRepository.save(new Email("grizli.gmail.com"));
        emailRepository.save(new Email("uma.gmail.com"));
        List<Email> list1 = emailRepository.findAll(Sort.by(Sort.Direction.ASC,"email","id"));
        list1.forEach(System.out::println);
        System.out.println("#####Paging############################");
        Page<Email> emailPage = emailRepository.findAll(PageRequest.of(2,3));
        System.out.println("Page number: "+emailPage.getTotalPages());
        System.out.println("Total elements: "+emailPage.getTotalElements());
        emailPage.get().forEach(System.out::println);
        System.out.println("get by id = 5: "+emailRepository.get(8));
    }
}
