package com.icexxx.icegen.codemanager;

import java.util.HashMap;

public class DataManager {
    private static HashMap<String, String[][]> map = new HashMap<String, String[][]>();

    public static String[][] getTable(String tableName) {
        setTable(null);
        return map.get(tableName);
    }

    /**
     * 规定三维数组
     * 
     * @param dataThreeDim
     * @return
     */
    public static void setTable(String[][][] dataThreeDim) {
        dataThreeDim = new String[9][][];
        dataThreeDim[0] = new String[2][2];
        dataThreeDim[0][0][0] = "finance";
        dataThreeDim[0][1][0] = "finance";
        dataThreeDim[0][0][1] = "finance";

        dataThreeDim[1] = new String[][] { { "Person" }, { "id", "int" }, { "name", "String" }, { "age", "int" },
                { "city", "String" } };
        for (int i = 1; i < dataThreeDim.length; i++) {
            String[][] table = dataThreeDim[i];
            if (table != null && table.length != 0) {
                if (table[0].length > 0) {
                    map.put(table[0][0], table);
                } else {
                    throw new RuntimeException("--数据未填充，可能不存在表名-");
                }

            } else {
                // throw new RuntimeException("--数据未填充，可能不存在表数据库名-");
            }
        }
    }
}
