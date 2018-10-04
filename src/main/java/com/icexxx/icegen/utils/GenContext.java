package com.icexxx.icegen.utils;

import java.util.Map;

public class GenContext {
    public static Map<String, String> map;
    public static Map<String, String> mapPojo;
    public static String domainPath;
    public static String packageName;
    public static String projectName;
    public static String pojo;
    public static boolean isSrcCode;

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        GenContext.map = map;
    }

    public String getKey(String key) {
        return map.get(key);
    }

    public static String getDomainPath() {
        return domainPath;
    }

    public static void setDomainPath(String domainPath) {
        GenContext.domainPath = domainPath;
    }

    public static String getProjectName() {
        return projectName;
    }

    public static void setProjectName(String projectName) {
        GenContext.projectName = projectName;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        GenContext.packageName = packageName;
    }

    public static String getPojo() {
        return pojo;
    }

    public static void setPojo(String pojo) {
        GenContext.pojo = pojo;
    }
    public static boolean isSrcCode() {
        return isSrcCode;
    }

    public static void setSrcCode(boolean isSrcCode) {
        GenContext.isSrcCode = isSrcCode;
    }

    public static Map<String, String> getMapPojo() {
        return mapPojo;
    }

    public static void setMapPojo(Map<String, String> mapPojo) {
        GenContext.mapPojo = mapPojo;
    }
}
