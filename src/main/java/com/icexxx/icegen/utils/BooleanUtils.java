package com.icexxx.icegen.utils;

public class BooleanUtils {

    public static boolean format(String flag, boolean defaultValue) {
        flag = flag.trim();
        if (flag == null && "".equals(flag)) {
            return defaultValue;
        }
        if ("y".equalsIgnoreCase(flag)) {
            return true;
        } else if ("n".equalsIgnoreCase(flag)) {
            return false;
        } else if ("yes".equalsIgnoreCase(flag)) {
            return true;
        } else if ("no".equalsIgnoreCase(flag)) {
            return false;
        } else if ("true".equalsIgnoreCase(flag)) {
            return true;
        } else if ("false".equalsIgnoreCase(flag)) {
            return false;
        } else if ("t".equalsIgnoreCase(flag)) {
            return true;
        } else if ("f".equalsIgnoreCase(flag)) {
            return false;
        } else if ("1".equals(flag)) {
            return true;
        } else if ("0".equals(flag)) {
            return false;
        }
        return false;
    }

}
