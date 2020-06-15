package com.it_uatech.dao_csv;

import com.it_uatech.domain.Questions;
import com.it_uatech.services.MyLocale;
import com.it_uatech.services.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Repository
@PropertySource("classpath:application.properties")
public class ReadFileImpl implements ReadFile {

    private MyLocale locale;

    @Autowired
    public ReadFileImpl(MyLocale locale) {
        this.locale = locale;
    }

    public List<Questions> getQuestionList() {
        List<Questions> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(this.locale.getFilePathWithTest()), StandardCharsets.UTF_8))) {
            String line = fileReader.readLine();
            while (line != null){
                String[] testLine = line.split(";");
                list.add(new Questions(testLine[0], Arrays.copyOfRange(testLine,1,testLine.length-1),testLine[testLine.length-1]));
                line = fileReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MyLocale getMyLocale() {
        return locale;
    }
}
