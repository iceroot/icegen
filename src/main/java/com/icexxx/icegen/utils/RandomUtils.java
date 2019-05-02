package com.icexxx.icegen.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
	/**
	 * 0为字符串,1 为 int
	 * 
	 * @param str  原始字符串
	 * @param type 字符串需要转换的目标类型
	 * @return
	 */
	public static String addYinHao(String str, int type) {
		if (type == 1) {
			return str;
		}
		return "\"" + str + "\"";
	}

	public static String addYinHao(int num, int type) {
		if (type == 1) {
			return num + "";
		}
		return "\"" + num + "\"";
	}

	public static String addYinHao(Date date, int type) {
		if (type == 1) {
			return date + "";
		}
		return "\"" + date + "\"";
	}

	public static String createStr(int size) {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for (int i = 0; i < size; i++) {
			char ch = (char) (ran.nextInt(26) + 97);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static String createStr() {
		return createStr(5);
	}

	public static String createNum(int size) {
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		for (int i = 0; i < size; i++) {
			char ch = (char) (ran.nextInt(10) + 48);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static String createNum() {
		return createNum(5);
	}

	public static String createData(int type) {
		if (type == 1) {
			return addYinHao(createNum(), 1);
		} else if (type == 2) {
			return addYinHao(createBoolean(), 1);
		} else if (type == 3) {
			return addYinHao(createDouble(), 1);
		} else if (type == 4) {
			return "new Date(" + addYinHao(createDate(), 0) + ")";
		} else if (type == 5) {
			return "new BigDecimal(" + addYinHao(createNum(), 0) + ")";
		}
		return addYinHao(createStr(), 0);
	}

	private static String createDouble() {
		Random ran = new Random();
		int dou = ran.nextInt(1000000);
		return dou / 100 + "";
	}

	private static String createBoolean() {
		Random ran = new Random();
		return ran.nextBoolean() + "";
	}

	private static Date createDate() {
		Random ran = new Random();
		int yearNum = ran.nextInt(36500);
		Calendar cal = Calendar.getInstance();
		long yearSecond = 31557600000l;// 365.25*24*36500*1000
		long millis = System.currentTimeMillis() + (10 - 100) * yearSecond + yearNum * 86400000l;
		cal.setTimeInMillis(millis);
		return cal.getTime();
	}

	public static int cast(String type) {
		if ("int".equalsIgnoreCase(type)) {
			return 1;
		} else if ("integer".equalsIgnoreCase(type)) {
			return 1;
		} else if ("long".equalsIgnoreCase(type)) {
			return 1;
		} else if ("double".equalsIgnoreCase(type)) {
			return 3;
		} else if ("byte".equalsIgnoreCase(type)) {
			return 1;
		} else if ("short".equalsIgnoreCase(type)) {
			return 1;
		} else if ("float".equalsIgnoreCase(type)) {
			return 3;
		} else if ("boolean".equalsIgnoreCase(type)) {
			return 2;
		} else if ("String".equalsIgnoreCase(type)) {
			return 0;
		} else if ("Date".equalsIgnoreCase(type)) {
			return 4;
		} else if ("BigDecimal".equalsIgnoreCase(type)) {
			return 5;
		}
		return 0;
	}

	public static Map<String, Boolean> importMap(List<String> list) {
		if (list == null) {
			return null;
		}
		boolean hutool = false;
		boolean other = false;
		for (String str : list) {
			if ("String".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("Integer".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("int".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("char".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("Character".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("long".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("short".equalsIgnoreCase(str)) {
				hutool = true;
			}
			if ("double".equalsIgnoreCase(str)) {
				other = true;
			}
			if ("Date".equalsIgnoreCase(str)) {
				other = true;
			}
			if ("BigDecimal".equalsIgnoreCase(str)) {
				other = true;
			}
			if (hutool && other) {
				break;
			}
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("hutool", hutool);
		map.put("other", other);
		return map;
	}

	public static List<String> list(String[][] table) {
		List<String> list = new ArrayList<String>();
		if (table == null) {
			return list;
		}
		for (int i = 1; i < table.length; i++) {
			String type = table[i][1];
			list.add(type);
		}
		return list;
	}

	public static String createValue(String fieldType) {
		if ("String".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomString(8)";
		} else if ("int".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomInt(10000)";
		} else if ("Integer".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomInt(10000)";
		} else if ("Long".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomLong(10000)";
		} else if ("char".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomChar()";
		} else if ("Character".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.randomChar()";
		} else if ("double".equalsIgnoreCase(fieldType)) {
			return "RandomUtils.roundDouble()";
		} else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
			return "RandomUtils.roundBigDecimal()";
		} else if ("Date".equalsIgnoreCase(fieldType)) {
			return "RandomUtils.roundDate()";
		} else if ("Boolean".equalsIgnoreCase(fieldType)) {
			return "RandomUtil.roundBoolean()";
		} else if ("short".equalsIgnoreCase(fieldType)) {
			return "(short)RandomUtil.randomInt(65536)";
		}
		return null;
	}
}
