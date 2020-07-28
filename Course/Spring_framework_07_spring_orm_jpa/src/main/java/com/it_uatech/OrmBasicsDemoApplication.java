package com.it_uatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(EclipseLinkJpaConfiguration.class)
public class OrmBasicsDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(OrmBasicsDemoApplication.class, args);
	}

}
