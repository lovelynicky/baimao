package com.rocky.entrance;

import com.rocky.domain.Access;
import com.rocky.utils.JsonUtils;

/**
 * Created by liluoqi on 16/6/2.
 */
public class Main {
    public static void main(String[] args) {
        String accessJsonString = "[{'field':'comment','value':'0'},{'field':'evaluation','value':'0'}" +
                ",{'field':'summary','value':'你好'}]";
        Access access = JsonUtils.convertToObject(accessJsonString, new Access(), Access.class);
        System.out.println(access.getSummary());
    }
}
