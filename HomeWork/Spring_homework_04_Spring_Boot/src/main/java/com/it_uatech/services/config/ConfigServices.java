package com.it_uatech.services.config;

import com.it_uatech.config.AppConfig;
import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.services.Locale;
import com.it_uatech.services.LocaleImpl;
import com.it_uatech.services.StudentTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Scanner;

@Configuration
public class ConfigServices {

    @Bean
    public Locale getLocaleImpl() {
        return new LocaleImpl();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/bundle");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setCacheSeconds(1);  //refresh cache after every 1 secs
        return messageSource;
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public StudentTest getStudentTest(ReadFile readFile, MessageSource message, Locale locale, AppConfig appConfig) {
        return new StudentTest(readFile, message, locale, appConfig);
    }
}
