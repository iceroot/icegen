package com.icexxx.icegen.data;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Data;

public class PojoData implements Data {
    private String[][] javaData;

    public PojoData() {

    }

    public PojoData(String[][][] javaData, int i) {
        this.javaData = javaData[i];
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        String className = javaData[0][0];
        map.put("className", className);
        return map;
    }

    @Override
    public String[][] getTable(String tableName) {
        String[][] table = this.javaData;
        return table;
    }
}
