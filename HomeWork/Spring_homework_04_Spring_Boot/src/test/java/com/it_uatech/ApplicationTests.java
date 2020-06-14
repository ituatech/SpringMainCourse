package com.it_uatech;


import com.it_uatech.config.AppConfig;
import com.it_uatech.dao_csv.ReadFileImpl;
import com.it_uatech.services.StudentTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private ReadFileImpl readFile;

    @Autowired
    private StudentTest studentTest;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private com.it_uatech.services.Locale locale;

    @Test
    public void testReadCsv() {
        Assert.assertEquals(readFile.getCsvFilePath(), "en:/student-test-template/Test_EN.csv;de:/student-test-template/Test_DE.csv;ru:/student-test-template/Test_RU.csv");
        java.util.Locale locale = new java.util.Locale.Builder().setLanguage("en").build();
        Assert.assertEquals("en",locale.getLanguage());
        readFile.getQuestionList(locale);
    }

    @Test
    public void testStudentTest() {
        Assert.assertNotNull(studentTest.getReadFile());
        Assert.assertNotNull(studentTest.getLocale());
        Assert.assertNotNull(studentTest.getMessage());
        Assert.assertNotNull(studentTest.getAppConfig());
    }

    @Test
    public void testAppConfig(){
        Assert.assertEquals(appConfig.getGoodPersent(),0.7,0);
    }

    @Test
    public void testLocale(){
        java.util.Locale loc = locale.buildLocate("en");
        Assert.assertEquals("en",loc.getLanguage());
    }

}
