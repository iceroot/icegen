package com.icexxx.icegen.codemanager;

import java.util.HashMap;

public interface Data {

    HashMap<String, String> getData();

    public String[][] getTable(String clasName);
}
