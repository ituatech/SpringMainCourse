package com.it_uatech.dao_csv;

import com.it_uatech.services.ReadFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReadFileImplTest {

    private ReadFile questions;

    @Before
    public void setUp(){
        this.questions = new ReadFileImpl("/Test.csv");
    }

    @Test
    public void questionListIsNotEmpty() {
        assertTrue(!questions.getQuestionList().isEmpty());
    }
}