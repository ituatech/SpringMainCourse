package com.it_uatech.dao_csv;

import com.it_uatech.entities.Questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadFileImpl implements ReadFile {

    private String fileName;

    public ReadFileImpl(){}

    public ReadFileImpl(String fileName){
        this.fileName = fileName;
    }

    public List<Questions> getQuestionList() {
        List<Questions> list = null;
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(this.fileName), StandardCharsets.UTF_8));
            String line;
            list = new ArrayList<>();
            while ((line = fileReader.readLine()) != null){
                String[] testLine = line.split(";");
                list.add(new Questions(testLine[0], new String[]{testLine[1], testLine[2], testLine[3]}));
            }
        }catch (UnsupportedEncodingException e){e.printStackTrace();}
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();}

        return list;
    }

    public String getFileName() {
        return fileName;
    }
}
