package com.icexxx.icegen.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

public class PathUtils {
	public static String getDesktop() {
		return System.getProperty("user.home").replace("\\", "/") + "/Desktop";
	}

	public static void templateFile(String oldPath, String newPath, String newStr) {
		StringBuilder sb = new StringBuilder();
		List<String> readUtf8Lines = null;
		readUtf8Lines = FileUtil.readUtf8Lines(oldPath);
		if (readUtf8Lines != null) {
			for (String line : readUtf8Lines) {
				line = line.replace("${projectNameIce}", newStr);
				sb.append(line);
				sb.append(com.icexxx.icegen.codemanager.Count.NEWLINE);
			}
		}
		String content = sb.toString();
		FileUtil.writeUtf8String(content, newPath);
	}

	public static void templateFileJar(String oldPath, String newPath, String newStr) {
		StringBuilder sb = new StringBuilder();
		List<String> readUtf8Lines = new ArrayList<String>();
		InputStream inputStream = PathUtils.class.getClassLoader().getResourceAsStream(oldPath);
		IoUtil.readLines(inputStream, "UTF-8", readUtf8Lines);
		if (readUtf8Lines != null) {
			for (String line : readUtf8Lines) {
				line = line.replace("${projectNameIce}", newStr);
				sb.append(line);
				sb.append(com.icexxx.icegen.codemanager.Count.NEWLINE);
			}
		}
		String content = sb.toString();
		FileUtil.writeUtf8String(content, newPath);
	}
}
