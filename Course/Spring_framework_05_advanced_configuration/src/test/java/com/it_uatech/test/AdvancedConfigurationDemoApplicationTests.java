package com.it_uatech.test;

import com.it_uatech.services.SingletonGreetingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class AdvancedConfigurationDemoApplicationTests {

/*
	@Configuration
	static class NestedConfiguration {
		@Bean
		SingletonGreetingServiceImpl singletonGreetingService() {
			return new SingletonGreetingServiceImpl();
		}
	}
*/

    @TestConfiguration
    static class NestedTestConfiguration {
        @Bean
        public TempBeanClass tempBeanClass(){
            return new TempBeanClass();
        }
    }

    @Autowired private SingletonGreetingServiceImpl singletonGreetingService;

    @Autowired private TempBeanClass tempBeanClass;

    @Test
    void contextLoads() {
    }

}
