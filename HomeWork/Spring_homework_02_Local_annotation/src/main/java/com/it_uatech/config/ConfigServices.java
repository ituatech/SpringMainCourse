package com.it_uatech.config;

import com.it_uatech.locale.MyLocaleImpl;
import com.it_uatech.services.MyLocale;
import com.it_uatech.services.MyScanner;
import com.it_uatech.services.ReadFile;
import com.it_uatech.services.StudentTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@PropertySource(value= "classpath:application.properties")
public class ConfigServices {

    @Bean
    public MyScanner getScanner() {
        return new MyScanner();
    }

    @Bean
    public MyLocale getMyLocale(MyScanner scanner) {
        System.out.println("Enter you language: en");
        System.out.println("Schreiben Sie bitte Ihre Sprache: de");
        System.out.println("Введите ваш язык: ru");
        String lang = scanner.getScanner().next();

        if (lang.equalsIgnoreCase("en")) {
            return new MyLocaleImpl("/student-test-template/Test_EN.csv", lang);
        } else if (lang.equalsIgnoreCase("de")) {
            return new MyLocaleImpl("/student-test-template/Test_DE.csv", lang);
        } else if (lang.equalsIgnoreCase("ru")) {
            return new MyLocaleImpl("/student-test-template/Test_RU.csv", lang);
        } else {
            return new MyLocaleImpl("/student-test-template/Test_EN.csv", "en");
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
    public StudentTest getStudentTest(ReadFile readFile, MessageSource message, MyScanner scanner, @Value("${test.goodResult}") double testGoodResult) {
        return new StudentTest(readFile, message, scanner, testGoodResult);
    }
}
