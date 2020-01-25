package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.EnumUtils;
import com.icexxx.icegen.utils.PojoMapUtil;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

public class PojoTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String pojo = dataMap.get("pojo");
        String ser = dataMap.get("ser");
        String enumPackage = com.icexxx.icegen.utils.Count.ENUM;
        String[][] table = data.getTable(className);
        String pojoName = pack + "." + projectName + "." + pojo + "." + className + "";
        String keyPojo = className + "{pojoName}";
        pojoName = PojoMapUtil.get(keyPojo, pojoName);
        pojoName = StrUtil.subBefore(pojoName, ".", true);
        sum.append("package " + pojoName + ";" + nl);
        String tab = "    ";
        String space = " ";
        sum.append("" + nl);
        if ("true".equalsIgnoreCase(ser)) {
            sum.append("import java.io.Serializable;" + nl);
            if (!"true".equalsIgnoreCase(table[0][1])) {
                sum.append("" + nl);
            }
        }
        if ("true".equalsIgnoreCase(table[0][2])) {
            sum.append("import java.math.BigDecimal;" + nl);
            if (!"true".equalsIgnoreCase(table[0][1])) {
                sum.append("" + nl);
            }
        }
        if ("true".equalsIgnoreCase(table[0][1])) {
            sum.append("import java.util.Date;" + nl);
            sum.append("" + nl);
        }
        boolean enumLine = false;
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            if (EnumUtils.contains(fieldName)) {
                sum.append("import " + pack + "." + projectName + "." + enumPackage + "." + fieldName + ";" + nl);
                enumLine = true;
            }
        }
        if (enumLine) {
            sum.append(nl);
        }
        sum.append("public class " + className + space);
        if ("true".equalsIgnoreCase(ser)) {
            sum.append("implements Serializable ");
        }
        sum.append("{" + nl);
        if ("true".equalsIgnoreCase(ser)) {
            String uuid = RandomUtil.randomLong() + "L";
            sum.append(tab);
            sum.append("private static final long serialVersionUID = " + uuid + ";");
            sum.append(nl);
        }
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            String fieldType = table[i][1];
            if (EnumUtils.contains(fieldName)) {
                fieldType = fieldName;
            }
            sum.append(tab);
            if ("span".equals(com.icexxx.icegen.utils.Count.POJO_COMMENT) && table[i][4] != null
                    && !"".equals(table[i][4])) {
                String comment = table[i][4];
                comment = comment.replace("\r\n", " ");
                comment = comment.replace("\n", " ");
                comment = comment.replace("\t", " ");
                sum.append(tab);
                sum.append("/**");
                sum.append(tab);
                sum.append(" * " + comment);
                sum.append(tab);
                sum.append(" */");
            }
            sum.append("private " + fieldType + " " + table[i][0] + ";");
            if ("show".equals(com.icexxx.icegen.utils.Count.POJO_COMMENT) && table[i][4] != null
                    && !"".equals(table[i][4])) {
                String comment = table[i][4];
                comment = comment.replace("\r\n", " ");
                comment = comment.replace("\n", " ");
                comment = comment.replace("\t", " ");
                sum.append("// " + comment);
            }
            sum.append(nl);
        }

        sum.append("" + nl);
        sum.append(tab);
        sum.append("public " + className + "()" + space + "{" + nl);
        sum.append(nl);
        sum.append(tab);
        sum.append("}" + nl);
        sum.append("" + nl);
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            String fieldType = table[i][1];
            if (EnumUtils.contains(fieldName)) {
                fieldType = fieldName;
            }
            sum.append(tab);
            sum.append("public void set" + StrUtil.upperFirst(table[i][0]) + "(" + fieldType + " " + table[i][0] + ")"
                    + space + "{" + nl);
            sum.append(tab + tab);
            sum.append("this." + table[i][0] + space + "=" + space + table[i][0] + ";" + nl);
            sum.append(tab);
            sum.append("}" + nl);

            sum.append(tab);
            sum.append("public " + fieldType + " get" + StrUtil.upperFirst(table[i][0]) + "()" + space + "{" + nl);
            sum.append(tab + tab);
            sum.append("return " + table[i][0] + ";" + nl);
            sum.append(tab);
            sum.append("}" + nl);
        }

        sum.append(tab);
        sum.append("@Override" + nl);
        sum.append(tab);
        sum.append("public String toString() {" + nl);
        sum.append(tab + tab);
        sum.append("return \"" + className + " [");
        for (int i = 1; i < table.length; i++) {
            if (i == table.length - 1) {
                sum.append(table[i][0] + "=\" + " + table[i][0] + " + ");
            } else {
                String nlToString = "";
                if (i % 4 == 3) {
                    nlToString = nl + tab + tab;
                }
                sum.append(table[i][0] + "=\" + " + table[i][0] + nlToString + " + \", ");
            }
        }
        sum.append("\"]\";" + nl);
        sum.append(tab);
        sum.append("}" + nl);
        sum.append("}" + nl);
        sum.append("");
        return sum.toString();
    }

}
