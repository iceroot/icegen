package com.icexxx.icegen.format;

import java.util.HashMap;

import com.icexxx.icegen.staticdata.StaticData;
import com.icexxx.icegen.utils.BooleanUtils;

import cn.hutool.core.util.StrUtil;

public class CalssFormat {
    public static String format(String className, boolean saveLine) {
        if (saveLine) {
            return className;
        } else {
            return classNameCast(className);
        }
    }

    public static String format(String className) {
        HashMap<String, String> formatConfig = StaticData.getFormatConfig();
        String prefix = formatConfig.get("prefix");
        String prefix2 = formatConfig.get("prefix2");
        String prefixs = formatConfig.get("prefixs");
        if (prefix == null || "".equals(prefix)) {
            return className;
        }
        prefix = prefix.replace("-", "_").toLowerCase();
        if (!prefix.endsWith("_")) {
            prefix += "_";
        }
        prefix2 = prefix2.replace("-", "_").toLowerCase();
        if (!prefix2.endsWith("_")) {
            prefix2 += "_";
        }
        String classNameFormat = className.replace("-", "_").toLowerCase();
        String pre = null;
        if (prefix2.startsWith(prefix)) {
            if (classNameFormat.startsWith(prefix2)) {
                pre = prefix2;
            } else if (classNameFormat.startsWith(prefix)) {
                pre = prefix;
            }
        } else {
            if (classNameFormat.startsWith(prefix)) {
                pre = prefix;
            } else if (classNameFormat.startsWith(prefix2)) {
                pre = prefix2;
            }
        }
        if (StrUtil.isNotBlank(prefixs)) {
            String[] pres = prefixs.split(",");
            for (int i = 0; i < pres.length; i++) {
                String curPre = pres[i];
                curPre = curPre.replace("-", "_").toLowerCase();
                if (classNameFormat.startsWith(curPre)) {
                    pre = curPre;
                    break;
                }
            }
        }
        if (pre != null) {
            int len = pre.length();
            boolean saveLine = BooleanUtils.format(formatConfig.get("saveLine"), true);
            String classNameRemove = className.substring(len);
            return format(classNameRemove, saveLine);
        }
        return className;
    }

    public static String classNameCast(String columnName) {
        columnName = columnName.replace("-", "_");
        String[] columnNameSplit = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnNameSplit.length; i++) {
            String every = columnNameSplit[i];
            if (i == 0) {
                sb.append(StrUtil.upperFirst(every));
            } else {
                sb.append(StrUtil.upperFirst(every));
            }
        }
        return sb.toString();
    }
}
