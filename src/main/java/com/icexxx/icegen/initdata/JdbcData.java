package com.icexxx.icegen.initdata;

import java.util.Arrays;
import java.util.HashMap;

import com.icexxx.icegen.format.FieldFormat;
import com.icexxx.icegen.staticdata.StaticData;
import com.icexxx.icegen.utils.JdbcUtils;

public class JdbcData {
	public static String[][][] data = null;

	public static String[][][] initJdbcData(HashMap<String, String> configFormat) {
		String driver = configFormat.get("driver");
		String url = configFormat.get("url");
		String username = configFormat.get("username");
		String password = configFormat.get("password");
		String databaseName = configFormat.get("databaseName");
		System.out.println(configFormat);
		data = GeneralController.getData(driver, url, username, password, databaseName);
		return data;
	}

	public static String[][][] initJavaData(String[][][] jdbcData) {

		int len = jdbcData.length;
		String[][][] result = new String[len][][];

		HashMap<String, String> formatConfig = StaticData.getFormatConfig();
		String projectName = formatConfig.get("projectName");
		result[0] = new String[][] { { projectName } };
		for (int i = 1; i < jdbcData.length; i++) {
			boolean haveDate = false;
			boolean haveDecimal = false;
			String className = JdbcUtils.getClassName(jdbcData[i]);
			result[i] = new String[jdbcData[i].length][];
			result[i][0] = new String[] { className, "false", "false" };
			for (int j = 1; j < jdbcData[i].length; j++) {
				int length = jdbcData[i][j].length;
				if (length < 2) {
					throw new RuntimeException("--从数据库获取的数据不完整--");
				}
				String fieldName;
				String fieldType = null;
				result[i][j] = new String[length];
				if (length >= 2) {
					String columnName = jdbcData[i][j][0];
					String columnType = jdbcData[i][j][1];
					fieldName = FieldFormat.columnNameCast(columnName);
					try {
						fieldType = FieldFormat.columnTypeCast(columnType);
					} catch (Exception e) {
						System.out.println(Arrays.toString(jdbcData[i][0]));
						e.printStackTrace();
					}
					result[i][j][0] = fieldName;
					result[i][j][1] = fieldType;
				}
				if (length >= 3) {
					String columnLength = jdbcData[i][j][2];
					String fieldLength = FieldFormat.columnLengthCast(columnLength, fieldType);
					result[i][j][2] = fieldLength;
				}
				if (length >= 4) {
					String columnIsNull = jdbcData[i][j][3];
					String fieldIsNull = FieldFormat.columnIsNullCast(columnIsNull);
					result[i][j][3] = fieldIsNull;
				}
				if (length >= 5) {
					String columnComment = jdbcData[i][j][4];
					String fieldComment = columnComment;
					result[i][j][4] = fieldComment;
				}
				if ("Date".equalsIgnoreCase(fieldType)) {
					haveDate = true;
				}
				if ("BigDecimal".equalsIgnoreCase(fieldType)) {
					haveDecimal = true;
				}

			}
			if (haveDate) {
				result[i][0][1] = "true";
			}
			if (haveDecimal) {
				result[i][0][2] = "true";
			}
		}

		return result;
	}
}
