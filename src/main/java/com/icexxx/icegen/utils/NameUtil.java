package com.icexxx.icegen.utils;

import cn.hutool.core.util.StrUtil;

public class NameUtil {
    public static String replace(String str, String upperFirst, String lowerFirst) {
        if (StrUtil.isBlank(str)) {
            return str;
        }
        if (StrUtil.isNotBlank(upperFirst)) {
            str = str.replace("${upperFirst}", upperFirst);
        }
        if (StrUtil.isNotBlank(lowerFirst)) {
            str = str.replace("${lowerFirst}", lowerFirst);
        }
        return str;
    }
}
