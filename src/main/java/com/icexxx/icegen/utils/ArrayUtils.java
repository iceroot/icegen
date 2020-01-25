package com.icexxx.icegen.utils;

import java.util.Arrays;
import java.util.Comparator;

import cn.hutool.core.util.StrUtil;

public class ArrayUtils {
    /**
     * 将三维数组的,每个表的0行0列 组成一个 一维数组,第一格 为表名,站位预留,并 扩展为 二维数组
     * 
     * @param javaData 原始三维数组
     * @return
     */
    public static String[][] ThreeDim2TwoDim(String[][][] javaData, String className) {
        String[][] result = new String[javaData.length + 1][1];
        result[0][0] = className;
        for (int i = 0; i < javaData.length; i++) {
            result[i + 1][0] = javaData[i][0][0];
        }
        return result;
    }

    /**
     * 将三维数组的,每个表的0行0列 组成一个 一维数组,并 扩展为 二维数组
     * 
     * @param javaData 原始三维数组
     * @return
     */
    public static String[][] ThreeDim2TwoDim(String[][][] javaData) {
        String[][] result = new String[javaData.length - 1][1];
        for (int i = 1; i < javaData.length; i++) {
            result[i - 1][0] = javaData[i][0][0];
        }
        return result;
    }

    public static String[] TwoDim2OneDim(String[][] table) {
        String[] result = new String[table.length];
        for (int i = 0; i < table.length; i++) {
            result[i] = table[i][0];
        }
        return result;
    }

    public static String[][] sortTwoDim(String[][] array) {
        final boolean hasId = hasId(array);
        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] array1, String[] array2) {
                if (array1 == null) {
                    return 1;
                }
                if (array2 == null) {
                    return -1;
                }
                if (array1.length == 0) {
                    return 1;
                }
                if (array2.length == 0) {
                    return -1;
                }
                String str1 = array1[0];
                if (str1 == null) {
                    return 1;
                }
                String str2 = array2[0];
                if (str2 == null) {
                    return -1;
                }
                if ("".equals(str1)) {
                    return 1;
                }
                if ("".equals(str2)) {
                    return -1;
                }
                if ("".equals(str1.trim())) {
                    return 1;
                }
                if ("".equals(str2.trim())) {
                    return -1;
                }
                if ("id".equalsIgnoreCase(str1)) {
                    return -1;
                }
                if ("id".equalsIgnoreCase(str2)) {
                    return 1;
                }
                if (!hasId && str1.toLowerCase().endsWith("id")) {
                    return -1;
                }
                if (!hasId && str2.toLowerCase().endsWith("id")) {
                    return 1;
                }
                return 0;
            }
        };
        String[] first = array[0];
        String[][] newArray = new String[array.length - 1][];
        newArray = Arrays.copyOfRange(array, 1, array.length);
        Arrays.sort(newArray, comparator);
        System.arraycopy(newArray, 0, array, 1, newArray.length);
        array[0] = first;
        return array;
    }

    private static boolean hasId(String[][] array) {
        if (array == null) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].length > 0 && "id".equalsIgnoreCase(array[i][0])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumIdColumn(String[][] jdbcTable, int i) {
        String[] column = jdbcTable[i];
        String name = column[0];
        String type = column[1];
        if (!"id".equalsIgnoreCase(name)) {
            return false;
        }
        if ("VARCHAR".equalsIgnoreCase(type)) {
            return false;
        }
        if ("VARCHAR2".equalsIgnoreCase(type)) {
            return false;
        }
        return true;
    }

    public static boolean isNumIdField(String[][] javaTable, int i) {
        String[] field = javaTable[i];
        String name = field[0];
        String type = field[1];
        if (!"id".equalsIgnoreCase(name)) {
            return false;
        }
        if ("String".equalsIgnoreCase(type)) {
            return false;
        }
        return true;
    }

    public static String idType(String[][] table) {
        if (table == null) {
            return "Integer";
        }
        if (table.length <= 1) {
            return "Integer";
        }
        for (int i = 1; i < table.length; i++) {
            String[] row = table[i];
            String name = row[0];
            String type = row[1];
            if ("id".equalsIgnoreCase(name)) {
                if ("String".equalsIgnoreCase(type)) {
                    return "String";
                } else if ("int".equalsIgnoreCase(type)) {
                    return "Integer";
                } else if ("Integer".equalsIgnoreCase(type)) {
                    return "Integer";
                } else if ("Long".equalsIgnoreCase(type)) {
                    return "Long";
                } else {
                    return "String";
                }
            }
        }
        return "Integer";
    }

    public static boolean isStringType(String type) {
        if ("String".equalsIgnoreCase(type)) {
            return true;
        }
        return false;
    }

    public static boolean containInArray(String[][] data, String tagValue) {
        if (StrUtil.isBlank(tagValue)) {
            return false;
        }
        if (data == null) {
            return false;
        }
        if (data.length == 0) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            String[] row = data[i];
            if (row == null) {
                continue;
            }
            if (row.length == 0) {
                continue;
            }
            String name = row[0];
            if (tagValue.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
