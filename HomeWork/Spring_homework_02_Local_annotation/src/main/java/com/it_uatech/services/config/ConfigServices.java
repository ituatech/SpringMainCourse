package com.it_uatech.services.config;

import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.services.MyLocaleImpl;
import com.it_uatech.services.StudentTest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import java.util.Scanner;

@Configuration
@PropertySource(value= "classpath:aplication.properties")
public class ConfigServices {
    public static final Scanner SCANNER = new Scanner(System.in);

    @Bean("UserLocation")

    public java.util.Locale getLocale(@Value("${propFile}") String propFile) {
        System.out.println("Enter you language: en");
        System.out.println("Schreiben Sie bitte Ihre Sprache: de");
        System.out.println("Введите ваш язык: ru");
        String lang = SCANNER.next();

        if (lang.equalsIgnoreCase("en")) {
            return new MyLocaleImpl(propFile).buildLocate(lang,"/student-test-template/Test_EN.csv");
        } else if (lang.equalsIgnoreCase("de")) {
            return new MyLocaleImpl(propFile).buildLocate(lang,"/student-test-template/Test_DE.csv");
        } else if (lang.equalsIgnoreCase("ru")) {
            return new MyLocaleImpl(propFile).buildLocate(lang,"/student-test-template/Test_RU.csv");
        } else {
            return new MyLocaleImpl(propFile).buildLocate("en","/student-test-template/Test_EN.csv");
        }
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);  //refresh cache after every 1 secs
        return messageSource;
    }

    @Bean
    public StudentTest getStudentTest(ReadFile readFile, MessageSource message, @Qualifier("UserLocation") java.util.Locale locale, @Value("${test.goodResult}") double res) {
        return new StudentTest(readFile, message, locale, res);
    }

/*    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
*/
}
