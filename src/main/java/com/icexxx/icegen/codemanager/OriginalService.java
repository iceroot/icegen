package com.icexxx.icegen.codemanager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

public class OriginalService {
    private static Map<String, String> original = new HashMap<String, String>();
    private static Map<String, String> template = new HashMap<String, String>();

    public static void original(String basePath) {
        List<File> loopFiles = FileUtil.loopFiles(basePath);
        for (File file : loopFiles) {
            String name = file.getName();
            name = StrUtil.removePrefix(name, "$");
            String path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            original.put(name, path);
        }
    }

    public static void template(String basePath) {
        List<File> loopFiles = FileUtil.loopFiles(basePath);
        for (File file : loopFiles) {
            String name = file.getName();
            name = StrUtil.removePrefix(name, "$");
            String path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            template.put(name, path);
        }
    }

    public static String getOriginal(String key) {
        return original.get(key);
    }

    public static String getTemplate(String key) {
        return template.get(key);
    }
}
