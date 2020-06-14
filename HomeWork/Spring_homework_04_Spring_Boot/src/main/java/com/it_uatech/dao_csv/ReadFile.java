package com.it_uatech.dao_csv;

import com.it_uatech.domain.Questions;

import java.util.List;
import java.util.Locale;

public interface ReadFile {
    public List<Questions> getQuestionList(Locale locale);
}
