package com.it_uatech.dao_csv;

import com.it_uatech.config.PropertyCsvFilePath;
import com.it_uatech.domain.Questions;
import com.it_uatech.services.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Repository("ReadFile")
public class ReadFileImpl implements ReadFile {

    private Map<String,String> csvPathName;

    @Autowired
    public ReadFileImpl(PropertyCsvFilePath property) {
        this.csvPathName = property.getCsvPathNames();
    }

    @Override
    public List<Questions> getQuestionList(Locale locale) {
        List<Questions> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(csvPathName.get(locale.getLanguage())), StandardCharsets.UTF_8))) {
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

    public Map<String, String> getCsvPathName() {
        return csvPathName;
    }
}
