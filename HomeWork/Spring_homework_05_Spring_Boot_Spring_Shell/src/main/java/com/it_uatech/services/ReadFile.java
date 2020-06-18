package com.it_uatech.services;

import com.it_uatech.domain.Questions;

import java.util.List;
import java.util.Locale;

public interface ReadFile {
    List<Questions> getQuestionList(Locale locale);
}
