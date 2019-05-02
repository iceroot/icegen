package com.icexxx.icegen.utils;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;

public class PojoMapUtil {
	public static String get(String key, String value) {
		Map<String, String> mapPojo = GenContext.getMapPojo();
		if (mapPojo == null) {
			mapPojo = new HashMap<String, String>();
			GenContext.setMapPojo(mapPojo);
		}
		String valueOld = mapPojo.get(key);
		if (StrUtil.isBlank(valueOld)) {
			mapPojo.put(key, value);
			return value;
		} else {
			return valueOld;
		}
	}
}
