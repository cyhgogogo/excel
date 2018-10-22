package com.excel.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author C_hao
 * @date 2018/10/22 10:16
 */
public class MemoryData {
    private static Map<String, String> sessionIDMap = new HashMap<String, String>();

    public static Map<String, String> getSessionIDMap() {
        return sessionIDMap;
    }

    public static void setSessionIDMap(Map<String, String> sessionIDMap) {
        MemoryData.sessionIDMap = sessionIDMap;
    }
}