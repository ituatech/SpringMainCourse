package com.it_uatech.config;

import com.it_uatech.dao.PersonDao;
import com.it_uatech.dao.PersonDaoSimple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DaoConfig {

    @Bean
    public PersonDao personDaoQualifierOne(@Value("${user.age.qualifier.one}") int age) {
        return new PersonDaoSimple(age);
    }

    @Bean
    public PersonDao personDaoQualifierTwo(@Value("${user.age.qualifier.two}") int age) {
        return new PersonDaoSimple(age);
    }

}
