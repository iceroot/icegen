package com.icexxx.icegen.data;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Data;

public class AllData implements Data {
    private String[][] javaData;
    private String[][] jdbcData;

    public AllData() {

    }

    public AllData(String[][][] javaData, int i) {
        this.javaData = javaData[i];
    }

    public AllData(String[][][] javaData, String[][][] jdbcData, int i) {
        this.javaData = javaData[i];
        this.jdbcData = jdbcData[i];
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        String className = javaData[0][0];
        map.put("className", className);
        return map;
    }

    @Override
    public String[][] getTable(String clasName) {
        String[][] table = this.javaData;
        return table;
    }

    public String[][] getJdbcTable(String clasName) {
        String[][] table = this.jdbcData;
        return table;
    }
}
