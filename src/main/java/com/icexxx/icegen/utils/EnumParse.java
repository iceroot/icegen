package com.icexxx.icegen.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

public class EnumParse {

    public static Map<String, String> convertMap(Map<String, List<String>> map) {
        Set<Entry<String, List<String>>> set = map.entrySet();
        Map<String, String> mapSimple = new HashMap<String, String>();
        for (Entry<String, List<String>> entry : set) {
            String key = entry.getKey();
            List<String> list = entry.getValue();
            String value = "";
            if (list != null && list.size() > 0) {
                value = list.get(0);
            }
            mapSimple.put(key, value);
        }
        return mapSimple;
    }

    public static Map<String, List<String>> getEnumMap(String folderName) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (File fl : files) {
            List<String> lines = null;
            lines = FileUtil.readUtf8Lines(fl);
            String className = null;
            boolean inner = false;

            for (String line : lines) {
                line = line.trim();
                line = line.replace("  ", "");
                if (line.startsWith("public enum ")) {
                    line = StrUtil.subAfter(line, "public enum ", false);
                    if (line.contains("//")) {
                        line = StrUtil.subBefore(line, "//", false);
                    }
                    line = removeEnd(line, "{");
                    line = line.trim();
                    className = line;
                    List<String> list = new ArrayList<String>();
                    map.put(className, list);
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                } else if (line.startsWith("//")) {
                } else if (line.startsWith("public ")) {
                } else if (line.startsWith("private ")) {
                } else if (line.startsWith("return ")) {
                } else if (line.startsWith("this.")) {
                } else if (line.equals("")) {
                } else if (line.equals("}")) {
                } else if (line.startsWith("/*")) {
                    inner = true;
                } else if (line.endsWith("*/")) {
                    inner = false;
                } else if (inner) {

                } else if (className != null) {
                    if (map.get(className) != null) {
                        if (line.contains("//")) {
                            line = StrUtil.subBefore(line, "//", false);
                        }
                        line = removeEnd(line, ",");
                        line = removeEnd(line, ";");
                        if (line.contains("(")) {
                            line = StrUtil.subBefore(line, "(", false);
                        }
                        line = line.trim();
                        map.get(className).add(line);
                    }
                }
            }
        }
        return map;
    }

    private static String removeEnd(String line, String suffix) {
        line = line.trim();
        if (line.endsWith(suffix)) {
            line = line.substring(0, line.length() - suffix.length());
        }
        return line;
    }

}
