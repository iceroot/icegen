package com.icexxx.icegen.enumsupport;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.icexxx.icegen.utils.EnumMapUtils;
import com.icexxx.icegen.utils.EnumParse;
import com.icexxx.icegen.utils.EnumUtils;

import java.util.Set;

import cn.hutool.core.util.StrUtil;

public class EnumManager {
    public static void execute(String enumPath) {
        // EnumUtils.add("OrderStatus");
        // EnumUtils.add("OrderType");
        // EnumMapUtils.put("OrderType", "NORMAL_ORDER");
        // EnumMapUtils.put("OrderStatus", "WAIT");
        if (StrUtil.isBlank(enumPath)) {
            return;
        }
        String folderName = StrUtil.removeSuffix(enumPath, "/");
        if (!new File(folderName).exists()) {
            return;
        }
        Map<String, List<String>> map = EnumParse.getEnumMap(folderName);
        Map<String, String> map2 = EnumParse.convertMap(map);
        Set<Entry<String, String>> set = map2.entrySet();
        for (Entry<String, String> entry : set) {
            String key = entry.getKey();
            String value = entry.getValue();
            EnumUtils.add(key);
            EnumMapUtils.put(key, value);
        }
    }
}
