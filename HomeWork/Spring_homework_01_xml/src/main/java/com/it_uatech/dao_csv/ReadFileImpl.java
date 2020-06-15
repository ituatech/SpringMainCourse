package com.it_uatech.dao_csv;

import com.it_uatech.entities.Questions;
import com.it_uatech.services.ReadFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFileImpl implements ReadFile {

    private final String fileName;

    public ReadFileImpl(String fileName){
        this.fileName = fileName;
    }

    public List<Questions> getQuestionList() {
        List<Questions> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(this.fileName), StandardCharsets.UTF_8))) {
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

    public String getFileName() {
        return fileName;
    }
}
