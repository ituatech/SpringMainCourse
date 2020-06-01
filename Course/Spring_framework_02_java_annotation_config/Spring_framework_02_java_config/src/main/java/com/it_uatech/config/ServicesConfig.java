package com.it_uatech.config;

import com.it_uatech.dao.PersonDao;
import com.it_uatech.service.PersonService;
import com.it_uatech.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DaoConfig.class)
@Configuration
public class ServicesConfig {

    @Bean
    public PersonService personService(@Qualifier("personDaoQualifierTwo") PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
