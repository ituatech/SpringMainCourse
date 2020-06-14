package com.it_uatech.services;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

public class MyLocaleImpl implements MyLocale {
    private final String propFile;

    public MyLocaleImpl(String propFile){
        this.propFile = propFile;
    }

    public String getPropFile() {
        return propFile;
    }

    @Override
    public Locale buildLocate(String language, String testPath) {
        Locale locale = new Locale.Builder().setLanguage(language).build();
        buildFilePath(testPath);
        return locale;
    }
    private void buildFilePath(String testPath) {
        try {
            Properties prop = new Properties();
            InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream(propFile));
            prop.load(in);
            // set the properties value
            prop.setProperty("file.path", testPath);
            in.close();


            URL url = this.getClass().getResource(propFile);
            File fileObject = new File(url.toURI());
            FileOutputStream out = new FileOutputStream(fileObject);


            // save properties to project root folder
            prop.store(out, null);
            out.close();

        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
