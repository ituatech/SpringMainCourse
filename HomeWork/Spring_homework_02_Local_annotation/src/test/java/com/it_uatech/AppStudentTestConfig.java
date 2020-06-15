package com.it_uatech;


import com.it_uatech.services.MyLocale;
import com.it_uatech.services.MyScanner;
import com.it_uatech.services.ReadFile;
import com.it_uatech.dao_csv.ReadFileImpl;
import com.it_uatech.locale.MyLocaleImpl;
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

    @Bean
    public MyScanner getScanner() {
        return new MyScanner();
    }

    @Bean
    public MyLocale getMyLocale(){
        return new MyLocaleImpl("/student-test-template/Test_DE.csv", "de");
    }

    @Bean
    ReadFile getCsvReader(MyLocale locale){
        return new ReadFileImpl(locale);
    }

    @Bean
    StudentTest getStudentTest(ReadFile csvReader, MessageSource messageSource, MyScanner scanner, @Value("${test.goodResult}")double goodPercent){
        return new StudentTest(csvReader, messageSource, scanner, goodPercent);
    }

    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }
}
