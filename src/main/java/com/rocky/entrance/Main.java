package com.rocky.entrance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rocky.domain.Access;
import com.rocky.domain.FieldValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by liluoqi on 16/6/2.
 */
public class Main {
    public static void main(String[] args) {
        String accessJsonString = "[{'field':'comment','value':'0'},{'field':'evaluation','value':'0'}" +
                ",{'field':'summary','value':'0'}]";
        Access access = convertObjectFromJsonString(accessJsonString, new Access(), Access.class);
    }

    private static <T> T convertObjectFromJsonString(String jsonString, T t, Class<T> clazz) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        List<FieldValue> fieldValues = gson.fromJson(jsonString, new TypeToken<List<FieldValue>>() {
        }.getType());
        String substring = gson.toJson(t).substring(1);
        String string = substring.substring(0, substring.length() - 1);
        String[] arrays = string.split(",");
        Map<String, Object> accessMap = new HashMap<String, Object>();
        for (String inner : arrays) {
            String[] innerArrays = inner.split(":");
            for (int index = 0; index < innerArrays.length; index = index + 2) {
                accessMap.put(innerArrays[index].replace("\"", ""), innerArrays[index + 1]);
            }
        }

        for (String key : accessMap.keySet()) {
            for (FieldValue fieldValue : fieldValues) {
                if (key.equals(fieldValue.getField())) {
                    accessMap.put(key, fieldValue.getValue());
                }
            }
        }
        return gson.fromJson(gson.toJson(accessMap), clazz);
    }
}
