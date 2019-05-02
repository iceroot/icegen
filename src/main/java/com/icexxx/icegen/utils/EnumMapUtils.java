package com.icexxx.icegen.utils;

import java.util.HashMap;
import java.util.Map;

public class EnumMapUtils {
	private static Map<String, String> map = new HashMap<String, String>();

	public static void put(String key, String value) {
		map.put(key, value);
	}

	public static String get(String key) {
		return map.get(key);
	}
}
