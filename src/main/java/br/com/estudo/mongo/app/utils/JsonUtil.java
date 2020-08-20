package br.com.estudo.mongo.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static <T> T readValue(String json, Class returnClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (T) mapper.readValue(json, returnClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
