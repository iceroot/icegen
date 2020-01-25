package com.icexxx.icegen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import com.icexxx.icegen.format.CalssFormat;
import com.icexxx.icegen.format.FieldFormat;
import com.icexxx.icegen.staticdata.StaticData;

import cn.hutool.core.util.StrUtil;

public class JdbcUtils {
    public static Connection getConnection(String driver, String url, String username, String password) {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            String message = e.getMessage();
            String exceptionClass = e.getClass().getName();
            if ("com.mysql.jdbc.exceptions.jdbc4.CommunicationsException".equals(exceptionClass)) {
                if (message.startsWith("Communications link failure") && message.endsWith(
                        "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")) {
                    throw new RuntimeException("--数据库无法连接--");
                }
            }
            if (message.startsWith("Access denied for user ") && message.endsWith(" (using password: YES)")) {
                throw new RuntimeException("--数据库密码错误--");
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            String driverClassName = e.getMessage().trim();
            if ("com.mysql.jdbc.Driver".equalsIgnoreCase(driverClassName)) {
                throw new RuntimeException("--未找到 mysql jar包  [mysql-connector-java-x.x.x-bin.jar]--");
            }
            throw new RuntimeException("--数据库驱动未找到,可能是没有引入相关jar文件--");
        } catch (Exception e) {
            String exceptionType = e.getClass().toString().trim();
            if (exceptionType.endsWith("MySQLSyntaxErrorException")) {
                e.printStackTrace();
                throw new RuntimeException("--指定的数据库名称不存在--");
            } else if (exceptionType.endsWith("com.mysql.jdbc.CommunicationsException")) {
                e.printStackTrace();
                throw new RuntimeException("--未能连接上数据库--");
            } else if (exceptionType.endsWith("com.mysql.jdbc.exceptions.jdbc4.CommunicationsException")) {
                e.printStackTrace();
                throw new RuntimeException("--未能连接上数据库--");
            } else if (exceptionType.endsWith("java.net.ConnectException")) {
                e.printStackTrace();
                throw new RuntimeException("--未能连接上数据库2--");
            }
            e.printStackTrace();
            throw new RuntimeException("--其他错误--");
        }
        return conn;
    }

    public String[][][] database2java(String[][][] data) {
        int len = data.length;
        String[][][] result = new String[len][][];
        HashMap<String, String> formatConfig = StaticData.getFormatConfig();
        String projectName = formatConfig.get("projectName");
        result[0] = new String[][] { { projectName } };
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int length = data[i][j].length;
                if (length < 2) {
                    throw new RuntimeException("--从数据库获取的数据不完整--");
                } else if (length == 2) {
                    String columnName = data[i][j][0];
                    String columnType = data[i][j][1];
                    String fieldName = FieldFormat.columnNameCast(columnName);
                    String fieldType = FieldFormat.columnTypeCast(columnType);
                    result[i][j][0] = fieldName;
                    result[i][j][1] = fieldType;
                }
            }
        }
        return result;
    }

    public static String[][] database2java(String[][] data) {
        int len = data.length;
        String[][] result = new String[len][];
        boolean haveDate = false;
        result[0] = new String[] { data[0][0], "false" };
        for (int i = 1; i < data.length; i++) {
            int length = data[i].length;
            if (length < 2) {
                throw new RuntimeException("--从数据库获取的数据不完整--");
            }
            String fieldName;
            String fieldType = null;
            result[i] = new String[length];
            if (length >= 2) {
                String columnName = data[i][0];
                String columnType = data[i][1];
                fieldName = FieldFormat.columnNameCast(columnName);
                fieldType = FieldFormat.columnTypeCast(columnType);
                result[i][0] = fieldName;
                result[i][1] = fieldType;
            }
            if (length >= 3) {
                String columnLength = data[i][2];
                String fieldLength = FieldFormat.columnLengthCast(columnLength, fieldType);
                result[i][2] = fieldLength;
            }
            if (length >= 4) {
                String columnIsNull = data[i][3];
                String fieldIsNull = FieldFormat.columnIsNullCast(columnIsNull);
                result[i][3] = fieldIsNull;
            }
            if ("Date".equalsIgnoreCase(fieldType)) {
                haveDate = true;
            }
        }
        if (haveDate) {
            result[0][1] = "true";
        }
        return result;
    }

    public static String getClassName(String[][] jdbcData) {
        if (jdbcData != null && jdbcData.length > 0) {
            if (jdbcData[0] != null && jdbcData[0].length > 0) {
                return StrUtil.upperFirst(CalssFormat.format(jdbcData[0][0]));
            }
        }
        throw new RuntimeException("--未在指定位置找到类名--");
    }

}
