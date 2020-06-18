package com.it_uatech.shell;

import com.it_uatech.services.StudentTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StartCommands {

    private final StudentTest studentTest;

    @Autowired
    public StartCommands(StudentTest studentTest){
        this.studentTest = studentTest;
    }

    @ShellMethod("Start Test, send locale")
    public void start(@ShellOption String locale){
        studentTest.startTest(locale);
    }
}
