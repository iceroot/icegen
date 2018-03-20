package com.icexxx.icegen.utils;

import java.util.List;

import cn.hutool.core.io.FileUtil;

public class PathUtils {
    public static String getDesktop() {
        return System.getProperty("user.home").replace("\\", "/") + "/Desktop";
    }

    public static void templateFile(String oldPath, String newPath, String newStr) {
        StringBuilder sb = new StringBuilder();
        List<String> readUtf8Lines = FileUtil.readUtf8Lines(oldPath);
        if (readUtf8Lines != null) {
            for (String line : readUtf8Lines) {
                line = line.replace("${projectName}", newStr);
                sb.append(line);
                sb.append(com.icexxx.icegen.codemanager.Count.NEWLINE);
            }
        }
        String content = sb.toString();
        FileUtil.writeUtf8String(content, newPath);
    }
}
