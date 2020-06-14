package com.it_uatech.services;

public class LocaleImpl implements Locale {

    @Override
    public java.util.Locale buildLocate(String language) {
        java.util.Locale locale = new java.util.Locale.Builder().setLanguage(language).build();
        return locale;
    }
}
