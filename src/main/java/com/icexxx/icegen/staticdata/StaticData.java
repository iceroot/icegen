package com.icexxx.icegen.staticdata;

import java.util.HashMap;

public class StaticData {
	private static HashMap<String, String> formatConfig;

	public static HashMap<String, String> getFormatConfig() {
		return formatConfig;
	}

	public static void setFormatConfig(HashMap<String, String> formatConfig) {
		StaticData.formatConfig = formatConfig;
	}
}
