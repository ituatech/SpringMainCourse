package com.it_uatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringBootApplicationTests {

// 1) Method
/*
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void contextLoads() {

        ApplicationSettings settings = ctx.getBean(ApplicationSettings.class);
        assertEquals(settings.getVersion(),1.0,0);
    }
*/
// 2) Method

@Autowired
private ApplicationSettings settings;

@Autowired
private MyService service;

    @Test
    public void contextLoads() {
        assertEquals(settings.getVersion(),1.0,0);
        assertTrue(settings.isActive());

        assertEquals(service.getVersion(),1.0,0);
        assertTrue(service.isActive());
    }


}
