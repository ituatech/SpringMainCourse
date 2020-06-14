package com.it_uatech.dao_csv;

import com.it_uatech.domain.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Repository
@ConfigurationProperties("application")
public class ReadFileImpl implements ReadFile {

    private String csvFilePath;

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public List<Questions> getQuestionList(Locale locale) {
        List<Questions> list = null;
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(buildCsvFilePath(locale)), "utf-8"));
            String line;
            list = new ArrayList<>();
            while ((line = fileReader.readLine()) != null) {
                String[] testLine = line.split(";");
                list.add(new Questions(testLine[0], new String[]{testLine[1], testLine[2], testLine[3]}));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    private String buildCsvFilePath(Locale locale) {
        String filePath = null;
        Map<String,String> pathNames = new HashMap<>();
        String[] filePathes = csvFilePath.split(";");
        for(String path : filePathes){
            String[] keyValue = path.split(":");
            pathNames.put(keyValue[0],keyValue[1]);
        }
        String keyLanguageFromLocale = locale.getLanguage();
        if (pathNames.containsKey(keyLanguageFromLocale)){
            filePath = pathNames.get(keyLanguageFromLocale);
        }else{
            filePath = pathNames.get("en");
        }
        return filePath;
    }
}
