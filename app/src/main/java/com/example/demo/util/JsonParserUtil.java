package com.example.demo.util;

import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectMapper getObjectMapperInstance(){
        return objectMapper;
    }
    public static void jsonToTrpInfo(String jsonString,TripInfo tripInfo) {
        try {
            tripInfo = JsonParserUtil.getObjectMapperInstance().readValue(jsonString, TripInfo.class);
            System.out.println("Name: " + tripInfo.getIc_diPwrOnAvgEgyConsump());
//            System.out.println("Age: " + person.getAge());
//            System.out.println("City: " + person.getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

