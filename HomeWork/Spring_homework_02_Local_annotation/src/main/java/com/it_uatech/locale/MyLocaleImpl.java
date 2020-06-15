package com.it_uatech.locale;

import com.it_uatech.services.MyLocale;

import java.util.Locale;

public class MyLocaleImpl implements MyLocale {

    private final String filePathWithTest;
    private final Locale locale;

    public MyLocaleImpl(String filePathWithTest, String language){
        this.filePathWithTest = filePathWithTest;
        this.locale = new Locale.Builder().setLanguage(language).build();
    }

    @Override
    public String getFilePathWithTest() {
        return filePathWithTest;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
