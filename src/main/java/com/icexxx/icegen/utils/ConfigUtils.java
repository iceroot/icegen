package com.icexxx.icegen.utils;

import java.util.HashMap;

public class ConfigUtils {

	public static HashMap<String, String> setDefaultValue(HashMap<String, String> map, String key,
			String defaultValue) {
		String value = map.get(key);
		if (value == null) {
			map.put(key, defaultValue);
		}
		return map;
	}

	public static HashMap<String, String> setDefaultValue(HashMap<String, String> map, String key, String defaultValue,
			String defaultValueForEmpty) {
		String value = map.get(key);
		if (value == null) {
			map.put(key, defaultValue);
		} else if ("".equals(value)) {
			map.put(key, defaultValueForEmpty);
		}
		return map;
	}

}
