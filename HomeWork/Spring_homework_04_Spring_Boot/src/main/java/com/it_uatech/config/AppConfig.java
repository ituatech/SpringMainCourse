package com.it_uatech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("result")
public class AppConfig {
    private double goodPersent;

    public double getGoodPersent() {
        return goodPersent;
    }

    public void setGoodPersent(double goodPersent) {
        this.goodPersent = goodPersent;
    }
}
