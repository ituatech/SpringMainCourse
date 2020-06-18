package com.it_uatech.test;

import com.it_uatech.config.PropertyCsvFilePath;
import com.it_uatech.dao_csv.ReadFileImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@EnableConfigurationProperties(PropertyCsvFilePath.class)
public class CsvReaderTest {

    @Configuration
    static class NestedConfiguration {

        @Bean("ReadFileTest")
        public ReadFileImpl readFileImpl(PropertyCsvFilePath property){
            return new ReadFileImpl(property);
        }
    }


    @Autowired
    private @Qualifier("ReadFileTest") ReadFileImpl csvReader;

    @Test
    public void questionListIsNotEmpty() {
        assertFalse(csvReader.getQuestionList(Locale.GERMAN).isEmpty());
    }

    @Test
    public void csvTestPathDefined() {
        assertEquals("/student-test-template/Test_DE.csv", csvReader.getCsvPathName().get("de"));
        assertEquals("/student-test-template/Test_EN.csv", csvReader.getCsvPathName().get("en"));
        assertEquals("/student-test-template/Test_RU.csv", csvReader.getCsvPathName().get("ru"));
    }
}
