package com.it_uatech.services;


import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractGreetingServiceImpl implements GreetingService {

    @Value("${greetings.first-greeting}")
    private String firstGreeting;

    @Value("${greetings.re-greeting}")
    private String reGreeting;

    @Value("${greetings.goodbye}")
    private String goodbye;

    private boolean isFirstGreetingSuccess;

    public AbstractGreetingServiceImpl() {
        this.isFirstGreetingSuccess = false;
    }

    @Override
    public boolean isFirstGreetingSuccess() {
        return isFirstGreetingSuccess;
    }

    @Override
    public String greeting() {
        return currentGreeting();
    }

    @Override
    public String goodbye() {
        if (isFirstGreetingSuccess) {
            isFirstGreetingSuccess = false;
            return goodbye;
        }
        else{
            isFirstGreetingSuccess = true;
            return firstGreeting;
        }
    }

    private synchronized String currentGreeting() {
        String greeting = isFirstGreetingSuccess ? reGreeting : firstGreeting;
        isFirstGreetingSuccess = true;
        return greeting;
    }
}
