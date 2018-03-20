package com.icexxx.icegen.format;

public class DataBaseUtils {

    public static String analyzeUrl(String url) {
        if (url.startsWith("jdbc:mysql:")) {
            return analyzeMysqlUrl(url);
        } else if (url.startsWith("jdbc:oracle:")) {
            return analyzeOracleUrl(url);
        } else if (url.startsWith("jdbc:microsoft:")) {
            return analyzeSqlServerUrl(url);
        } else if (url.startsWith("jdbc:sqlserver")) {
            return analyzeSqlServerUrl(url);
        }
        return null;
    }

    private static String analyzeSqlServerUrl(String url) {
        if (url.startsWith("jdbc:sqlserver:") || url.startsWith("jdbc:microsoft:sqlserver")) {
            url = removeStart(url, "jdbc:microsoft:sqlserver:");
            url = removeStart(url, "jdbc:sqlserver:");
            url = removeStart(url, "//");
            String[] strs = url.split(";");
            String databasePart = strs[1];
            String[] databaseNamePart = databasePart.split("=");
            String key = databaseNamePart[0].trim();
            String value = databaseNamePart[1].trim();
            if ("DatabaseName".equals(key)) {
                return value;
            }
        }
        return null;
    }

    private static String analyzeOracleUrl(String url) {
        if (url.startsWith("jdbc:oracle:")) {
            url = removeStart(url, "jdbc:oracle:");
            url = removeStart(url, "thin:");
            url = removeStart(url, "fat:");
            url = removeStart(url, "@");
            String[] strs = url.split(":");
            if (strs.length == 3) {
                try {
                    Integer.parseInt(strs[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
                return strs[2];
            }
        }
        return null;
    }

    private static String analyzeMysqlUrl(String url) {
        if (url.startsWith("jdbc:mysql:")) {
            url = removeStart(url, "jdbc:mysql:");
            url = removeStart(url, "//");
            int index = url.indexOf("/");
            if (index == -1) {
                return null;
            }
            url = url.substring(index + 1);
            int questionIndex = url.indexOf("?");
            if (questionIndex == -1) {
                return url;
            }
            url = url.substring(0, questionIndex);
            return url;
        }
        return null;
    }

    private static String removeStart(String url, String pr) {
        if (url != null && url.startsWith(pr)) {
            return url.substring(pr.length());
        }
        return url;
    }
}
