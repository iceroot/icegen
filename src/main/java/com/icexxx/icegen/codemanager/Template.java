package com.icexxx.icegen.codemanager;

import java.util.HashMap;

public interface Template {

	String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className);

}
