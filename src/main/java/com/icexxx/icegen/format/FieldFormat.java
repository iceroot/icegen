package com.icexxx.icegen.format;

import com.icexxx.icegen.utils.BooleanUtils;

import cn.hutool.core.util.StrUtil;

public class FieldFormat {
    public static String columnNameCast(String columnName) {
        columnName = columnName.replace("-", "_");
        String[] columnNameSplit = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnNameSplit.length; i++) {
            String every = columnNameSplit[i];
            every = every.toLowerCase();
            if (i == 0) {
                sb.append(StrUtil.lowerFirst(every));
            } else {
                sb.append(StrUtil.upperFirst(every));
            }
        }
        return sb.toString();
    }

    public static String columnIsNullCast(String columnIsNull) {
        if (columnIsNull == null || "".equals(columnIsNull)) {
            return columnIsNull;
        }
        boolean isNull = BooleanUtils.format(columnIsNull, false);
        return String.valueOf(isNull);
    }

    public static String columnLengthCast(String columnLength, String fieldType) {
        if ("Date".equalsIgnoreCase(fieldType)) {
            return "";
        }
        return columnLength;
    }

    public static String columnTypeCast(String columnType) {
        if (columnType == null) {
            return "";
        }
        String type = columnType.trim().toLowerCase();
        if ("varchar".equals(type)) {
            return "String";
        } else if ("int".equals(type)) {
            return "Integer";
        } else if ("integer".equals(type)) {
            return "Integer";
        } else if ("int unsigned".equals(type)) {
            return "Integer";
        } else if ("tinyint".equals(type)) {
            return "Integer";
            // }else if("int".equals(type)){
            // return "int";
        } else if ("varchar2".equals(type)) {
            return "String";
            // }else if("date".equals(type)){
            // return "Date";
        } else if ("date".equals(type)) {
            return "Date";
        } else if ("double".equals(type)) {
            return "double";
        } else if ("double unsigned".equals(type)) {
            return "double";
        } else if ("bigint".equals(type)) {
            return "long";
        } else if ("char".equals(type)) {
            return "String";
        } else if ("".equals(type)) {
            return "String";
        } else if ("datetime".equals(type)) {
            return "Date";
        } else if ("smallint".equals(type)) {
            return "short";
        } else if ("timestamp".equals(type)) {
            return "Date";
        } else if ("time".equals(type)) {
            return "Date";
        } else if ("decimal".equals(type)) {
            return "BigDecimal";
        } else if ("float".equals(type)) {
            return "Float";
        } else if ("integer unsigned".equals(type)) {
            return "Integer";
        } else if ("bigint unsigned".equals(type)) {
            return "Long";
            // }else if("int unsigned".equals(type)){
            // return "Integer";
        } else if ("decimal unsigned".equals(type)) {
            return "BigDecimal";
        } else if ("tinyblob".equals(type)) {
            return "byte[]";
        } else if ("longblob".equals(type)) {
            return "byte[]";
        } else if ("blob".equals(type)) {
            return "byte[]";
        } else if ("varbinary".equals(type)) {
            return "byte[]";
        } else if ("tinyint unsigned".equals(type)) {
            return "Integer";
        } else {
            throw new RuntimeException("--未知的数据库类型 " + type + "--");
        }
    }

    public static String columnJdbcTypeCast(String columnType) {
        if (columnType == null) {
            return "";
        }
        if ("INT".equals(columnType)) {
            return "INTEGER";
        } else if ("DATE".equals(columnType)) {
            return "TIMESTAMP";
        } else if ("DECIMAL UNSIGNED".equals(columnType)) {
            return "DECIMAL";
        } else if ("INT UNSIGNED".equals(columnType)) {
            return "INTEGER";
        } else if ("TINYINT UNSIGNED".equals(columnType)) {
        	return "INTEGER";
        } else if ("DATETIME".equals(columnType)) {
            return "TIMESTAMP";
        } else if ("BIGINT UNSIGNED".equals(columnType)) {
            return "BIGINT";
        } else if ("TINYBLOB".equals(columnType)) {
            return "BLOB";
        } else if ("LONGBLOB".equals(columnType)) {
            return "BLOB";
            // }else if("varchar2".equals(type)){
            // return "String";
            // }else if("date".equals(type)){
            // return "Date";
            // }else if("double".equals(type)){
            // return "double";
            // }else if("bigint".equals(type)){
            // return "long";
            // }else if("char".equals(type)){
            // return "String";
            // }else if("".equals(type)){
            // return "String";
            // }else if("datetime".equals(type)){
            // return "Date";
            // }else if("smallint".equals(type)){
            // return "short";
            // }else if("timestamp".equals(type)){
            // return "Date";
            // }else if("decimal".equals(type)){
            // return "BigDecimal";
        } else {
            return columnType;
        }
    }

    public static String[][] columnJdbcTypeCast(String[][] jdbcTable) {
        String[][] newTable = new String[jdbcTable.length][];
        for (int i = 0; i < jdbcTable.length; i++) {
            newTable[i] = new String[jdbcTable[i].length];
            for (int j = 0; j < jdbcTable[i].length; j++) {
                newTable[i][j] = columnJdbcTypeCast(jdbcTable[i][j]);
            }
        }
        return newTable;
    }
}
