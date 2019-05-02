package com.icexxx.icegen.utils;

public class DateUtils {

	public static boolean hasDate(String[][] table) {
		boolean flag = false;
		for (int i = 1; i < table.length; i++) {
			if ("Date".equalsIgnoreCase(table[i][1])) {
				flag = true;
			}
		}
		return flag;
	}

	public static boolean hasBigDecimal(String[][] table) {
		boolean flag = false;
		for (int i = 1; i < table.length; i++) {
			if ("BigDecimal".equalsIgnoreCase(table[i][1])) {
				flag = true;
			}
		}
		return flag;
	}
}
