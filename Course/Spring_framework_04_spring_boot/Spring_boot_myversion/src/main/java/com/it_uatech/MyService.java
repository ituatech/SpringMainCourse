package com.it_uatech;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnClass(MyConfig.class)
@ConditionalOnProperty(value = "application.active",havingValue = "true")
@ConditionalOnBean(MyDao.class)
@Service
public class MyService {
    private double version;
    private boolean active;

    public MyService(ApplicationSettings settings){
        this.version = settings.getVersion();
        this.active = settings.isActive();
    }

    public double getVersion() {
        return version;
    }

    public boolean isActive() {
        return active;
    }
}
