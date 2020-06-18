package com.it_uatech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;

@ConfigurationProperties("application")
@ConstructorBinding
public class PropertyCsvFilePath {

    private Map<String,String> csvPathNames;

    public PropertyCsvFilePath(Map<String, String> csvFilePath) {
        this.csvPathNames = csvFilePath;
    }

    public Map<String, String> getCsvPathNames() {
        return csvPathNames;
    }
}
