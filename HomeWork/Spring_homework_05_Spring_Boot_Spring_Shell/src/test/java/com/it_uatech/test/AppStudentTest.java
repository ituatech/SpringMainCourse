package com.it_uatech.test;

import com.it_uatech.config.PropertyResult;
import com.it_uatech.services.ReadFile;
import com.it_uatech.services.StudentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableConfigurationProperties(PropertyResult.class)
public class AppStudentTest {

    @Configuration
    static class AppStudentTestConfig {

        @Bean
        public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource
                    = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("/i18n/bundle");
            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
            messageSource.setCacheSeconds(1);  //refresh cache after every 1 secs
            return messageSource;
        }

        @Bean
        public StudentTest getStudentTest(@Qualifier("ReadFileTest") ReadFile readFile, MessageSource message, PropertyResult propertyResult) {
            return new StudentTest(readFile, message, propertyResult);
        }
    }

    @Autowired
    private StudentTest studentTest;

    @MockBean(name = "ReadFileTest")
    private ReadFile readFile;


    @Test
    public void testStudentTest(){
        assertNotNull(studentTest.getMessage());
        assertNotNull(studentTest.getQuestions());
        assertEquals(studentTest.getTestGoodResult(), 0.5, 0);
    }
}
