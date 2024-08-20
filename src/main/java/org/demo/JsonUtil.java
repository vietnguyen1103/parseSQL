package org.demo;

import com.google.gson.Gson;

import java.util.Map;

public class JsonUtil {
    public static Map<String, String> parse(String object) {
        return new Gson().fromJson(object, Map.class);
    }
}
