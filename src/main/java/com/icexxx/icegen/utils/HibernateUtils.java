package com.icexxx.icegen.utils;

public class HibernateUtils {
	public static String cast2HibernateType(String type) {
		if ("".equals(type)) {
			return "$";
		} else if ("int".equalsIgnoreCase(type)) {
			return "int";
		} else if ("string".equalsIgnoreCase(type)) {
			return "java.lang.String";
		} else if ("date".equalsIgnoreCase(type)) {
			return "java.util.Date";
		} else if ("double".equalsIgnoreCase(type)) {
			return "double";
		} else {
			return "java.lang.Object";
		}
	}
}
