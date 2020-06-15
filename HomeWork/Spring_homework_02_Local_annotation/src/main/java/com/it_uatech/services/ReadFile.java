package com.it_uatech.services;

import com.it_uatech.domain.Questions;

import java.util.List;

public interface ReadFile {
    List<Questions> getQuestionList();
    MyLocale getMyLocale();
}
