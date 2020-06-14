package com.it_uatech;

import com.it_uatech.dao_csv.ReadFileImpl;
import com.it_uatech.services.MyLocaleImpl;
import com.it_uatech.services.StudentTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:test.properties")
public class AppStudentTest {
    private AnnotationConfigApplicationContext context;
    private ReadFileImpl csvReader;
    private MyLocaleImpl locale;
    private StudentTest studentTest;

    @Before
    public void init(){
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
    public void testLocale(){
        Assert.assertEquals(locale.getPropFile(), "/aplication.properties");
    }

    @Test
    public void testCsvReader(){
        Assert.assertEquals(csvReader.getPropFile(), "/aplication.properties");
        Assert.assertEquals(csvReader.getFilePath(), "/student-test-template/Test_DE.csv");
    }

    @Test
    public void testStudentTest(){
        Assert.assertNotNull(studentTest.getMessage());
        Assert.assertNotNull(studentTest.getLocale());
        Assert.assertNotNull(studentTest.getReadFile());
        Assert.assertEquals(studentTest.getTestRes(), 0.5, 0);
    }

}
