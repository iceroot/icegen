package com.icexxx.icegen.codemanager;

import java.io.File;
import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cn.hutool.core.collection.CollUtil;
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

	private static List<String> loopJarFoolder(String pathLocal) {
		List<String> list = new ArrayList<String>();
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(new File(pathLocal));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry nextElement = entries.nextElement();
			String simpleName = nextElement.getName();
			if (StrUtil.isNotBlank(simpleName)) {
				if (simpleName.startsWith("original/") || simpleName.startsWith("template/")) {
					list.add(simpleName);
				}
			}
		}
		return list;
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

	public static void jarCode(String originalPath, String templatePath) {
		ProtectionDomain protectionDomain = OriginalService.class.getProtectionDomain();
		String pathLocal = protectionDomain.getCodeSource().getLocation().getPath();
		List<String> list = loopJarFoolder(pathLocal);
		if (CollUtil.isEmpty(list)) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			String simpleName = list.get(i);
			if (simpleName.startsWith("original/")) {
				String shortName = StrUtil.removePrefix(simpleName, "original/");
				shortName = StrUtil.removePrefix(shortName, "$");
				original.put(shortName, simpleName);
			}
			if (simpleName.startsWith("template/")) {
				String shortName = StrUtil.removePrefix(simpleName, "template/");
				shortName = StrUtil.removePrefix(shortName, "$");
				template.put(shortName, simpleName);
			}
		}
	}
}
