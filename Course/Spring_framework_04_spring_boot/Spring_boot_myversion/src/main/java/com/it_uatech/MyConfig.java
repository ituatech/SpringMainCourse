package com.it_uatech;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class}) // how to do own auto-configuration: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-auto-configuration.html
public class MyConfig{
}
