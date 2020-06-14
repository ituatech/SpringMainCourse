package com.it_uatech;


import com.it_uatech.dao_csv.ReadFile;
import com.it_uatech.dao_csv.ReadFileImpl;
import com.it_uatech.services.MyLocaleImpl;
import com.it_uatech.services.StudentTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@PropertySource("classpath:test.properties")
public class AppStudentTestConfig {

    @Bean("locale")
    java.util.Locale getLocale(@Value("${propFile}")String propFile){
        return new MyLocaleImpl(propFile).buildLocate("de","/student-test-template/Test_DE.csv");
    }

    @Bean
    MyLocaleImpl getLocaleImpl(@Value("${propFile}")String propFile){
        return new MyLocaleImpl(propFile);
    }

    @Bean
    ReadFile getCsvReader(@Value("${propFile}")String propFile){
        return new ReadFileImpl(propFile);
    }

    @Bean
    StudentTest getStudentTest(ReadFile csvReader, MessageSource messageSource, java.util.Locale locale, @Value("${test.goodResult}")double goodPercent){
        return new StudentTest(csvReader, messageSource, locale, goodPercent);
    }

    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }
}
