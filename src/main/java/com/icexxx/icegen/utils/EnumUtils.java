package com.icexxx.icegen.utils;

import java.util.ArrayList;
import java.util.List;

public class EnumUtils {
    private static List<String> list = new ArrayList<String>();

    public static void add(String fieldName) {
        list.add(fieldName);
    }

    public static void add(String fieldName, String className) {
        list.add(fieldName + "." + className);
    }

    public static boolean contains(String fieldName, String className) {
        if (list.contains(fieldName + "." + className)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean contains(String fieldName) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String strr = str;
            if (str.contains(".")) {
                strr = str.split("\\.")[1];
            }
            if (strr.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
