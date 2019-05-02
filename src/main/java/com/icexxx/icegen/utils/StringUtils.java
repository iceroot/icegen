package com.icexxx.icegen.utils;

import com.icexxx.icegen.codemanager.Count;

public class StringUtils {
	/**
	 * 去掉域名最后的点
	 * 
	 * @param domain
	 * @return
	 */
	public static String formatDmain(String domain) {
		if (domain.length() > 0) {
			String last = domain.substring(domain.length() - 1);
			if (".".equals(last)) {
				return domain.substring(0, domain.length() - 1);
			}
		}
		return domain;
	}

	/**
	 * 从简单路径名获取简单包名
	 * 
	 * @param shortClassName dao/Book.java
	 * @return
	 */
	public static String getPackageName(String shortClassName) {
		int index = shortClassName.lastIndexOf(Count.SHASH);
		return shortClassName.substring(0, index).replace(Count.SHASH, ".");
	}

	public static String getClassName(String shortClassName) {
		int index = shortClassName.lastIndexOf(Count.SHASH);
		shortClassName = shortClassName.substring(index + 1);
		return shortClassName;
	}

	public static String getSimpleNameFromDAOImpl(String className) {
		if (className == null || "".equals(className)) {
			return className;
		}
		if (className.endsWith("DAOImpl") || className.endsWith("DaoImpl") || className.endsWith("daoimpl")) {
			return className.substring(0, className.length() - 7);
		}
		if (className.endsWith("ServiceImpl") || className.endsWith("SERVICEImpl")
				|| className.endsWith("serviceimpl")) {
			return className.substring(0, className.length() - 11);
		}
		return className;
	}

	public static String getPath(String shortClassName) {
		int index = shortClassName.lastIndexOf("/");
		return shortClassName.substring(0, index);
	}

	public static String removeTest(String className) {
		if (className.endsWith("TEST") || className.endsWith("Test") || className.endsWith("test")) {
			return className.substring(0, className.length() - 4);
		}
		return className;
	}

	public static String createTab(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	public static String getSimpleNameFromServiceTest(String className) {
		if (className == null || "".equals(className)) {
			return className;
		}
		if (className.endsWith("ServiceTest") || className.endsWith("SERVICETest")
				|| className.endsWith("servicetest")) {
			return className.substring(0, className.length() - 11);
		}
		return className;
	}
}
