package com.it_uatech.config;

import com.it_uatech.services.ReadFile;
import com.it_uatech.services.StudentTest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class ConfigServices {

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
    public StudentTest getStudentTest(@Qualifier("ReadFile") ReadFile readFile, MessageSource message, PropertyResult propertyResult) {
        return new StudentTest(readFile, message, propertyResult);
    }
}
