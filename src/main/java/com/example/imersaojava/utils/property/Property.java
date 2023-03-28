package com.example.imersaojava.utils.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

    public static String getProperty(String property){
        Properties prop = new Properties();
        FileInputStream input = null;
        String apiKey = null;
        try {
            input = new FileInputStream("resources.application");
            prop.load(input);
            apiKey = prop.getProperty(property);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return apiKey;
    }

}
