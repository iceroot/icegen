package com.icexxx.icegen.codemanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DataPlus {

	public static HashMap<String, String> plus(HashMap<String, String> data, HashMap<String, String> configFormat) {
		Iterator<Entry<String, String>> ite = configFormat.entrySet().iterator();
		while (ite.hasNext()) {
			Entry<String, String> entry = ite.next();
			String key = entry.getKey();
			String value = entry.getValue();
			data.put(key, value);
		}
		return data;
	}

}
