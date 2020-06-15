package com.it_uatech;

import com.it_uatech.dao_csv.ReadFileImpl;
import com.it_uatech.locale.MyLocaleImpl;
import com.it_uatech.services.StudentTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import static org.junit.Assert.*;

@PropertySource("classpath:test.properties")
public class AppStudentTest {
    private AnnotationConfigApplicationContext context;
    private ReadFileImpl csvReader;
    private MyLocaleImpl locale;
    private StudentTest studentTest;

    @Before
    public void setUp(){
        context = new AnnotationConfigApplicationContext(AppStudentTestConfig.class);
        csvReader = context.getBean(ReadFileImpl.class);
        locale = context.getBean(MyLocaleImpl.class);
        studentTest = context.getBean(StudentTest.class);
    }

    @After
    public void destroy(){
        csvReader = null;
        locale = null;
        studentTest = null;
        context = null;
    }

    @Test
    public void questionListIsNotEmpty() {
        assertFalse(csvReader.getQuestionList().isEmpty());
    }

    @Test
    public void localeIsAvailable() {
        assertEquals("de", csvReader.getMyLocale().getLocale().getLanguage());
        assertEquals("/student-test-template/Test_DE.csv", csvReader.getMyLocale().getFilePathWithTest());
    }

    @Test
    public void testStudentTest(){
        assertNotNull(studentTest.getMessage());
        assertNotNull(studentTest.getQuestions());
        assertEquals(studentTest.getTestGoodResult(), 0.5, 0);
    }

}
