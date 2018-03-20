package com.icexxx.icegen.main;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.CodeManager;

public class Main {

    /**
     * 程序入口
     * 
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, String> config = new HashMap<String, String>();

        config.put("domain", "com.iceyyy");
        config.put("encoding", "utf8");

        config.put("projectName", "ssm");
        config.put("databaseType", "mysql");

        config.put("username", "root");
        config.put("password", "root");

        config.put("prefix", "t");
        // config.put("prefix2", "");
        // config.put("prefixs", "");
        config.put("saveLine", "n");// 保留斜杠
        config.put("catch", "1");
        config.put("url", "jdbc:mysql://127.0.0.1:3306/ssm");
        config.put("out", "C:/Users/Administrator/Desktop");
        config.put("baseDao", "com.iceyyy.BaseDao");
        config.put("enum", "");
        // config.put("databaseName","test");
        // config.put("debug", "true");

        CodeManager.execute(config);
    }

}
