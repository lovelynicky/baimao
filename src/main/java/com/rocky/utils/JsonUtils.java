package com.rocky.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rocky.domain.FieldValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 16/6/10.
 */
public class JsonUtils {

    /**
     * 将 key为field(attribute),value(attribute's value)的json字符串转化为给定的class对象
     * @param jsonString
     * @param t
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T convertToObject(String jsonString, T t, Class<T> clazz) {
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
