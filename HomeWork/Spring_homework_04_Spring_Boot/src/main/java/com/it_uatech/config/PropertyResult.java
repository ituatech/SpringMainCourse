package com.it_uatech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("result")
@ConstructorBinding
public class PropertyResult {

    private double goodPercent;

    public PropertyResult(double goodPercent) {
        this.goodPercent = goodPercent;
    }

    public double getGoodPercent() {
        return goodPercent;
    }

}
