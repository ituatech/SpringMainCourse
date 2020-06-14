package com.it_uatech.dao_csv;

import com.it_uatech.domain.Questions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@DependsOn("UserLocation")
@Repository
@PropertySource("classpath:test.properties")
public class ReadFileImpl implements ReadFile {

    private String filePath;
    private String propFile;


    public ReadFileImpl(@Value("${propFile}") String propFile) {
        try {
            Properties prop = new Properties();
            InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream(propFile));
            prop.load(in);
            // get the properties value
            this.filePath=prop.getProperty("file.path");
            this.propFile=propFile;
            in.close();
        }catch (IOException ex){ex.printStackTrace();}
    }


    public List<Questions> getQuestionList() {
        List<Questions> list = null;
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filePath), StandardCharsets.UTF_8));
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

    public String getPropFile() {
        return propFile;
    }

    public String getFilePath() {
        return filePath;
    }
}
