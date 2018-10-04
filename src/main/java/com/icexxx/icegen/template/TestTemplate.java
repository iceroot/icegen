package com.icexxx.icegen.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.ArrayUtils;
import com.icexxx.icegen.utils.EnumUtils;
import com.icexxx.icegen.utils.NameUtil;
import com.icexxx.icegen.utils.RandomUtils;

import cn.hutool.core.util.StrUtil;

public class TestTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String st = StrUtil.lowerFirst(Stu);
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String[][] table = data.getTable(className);
        String pojo = dataMap.get("pojo");
        String saveMapperName = dataMap.get("saveMapperName");
        String updateMapperName = dataMap.get("updateMapperName");
        String deleteMapperName = dataMap.get("deleteMapperName");
        String getByIdMapperName = dataMap.get("getByIdMapperName");
        saveMapperName = NameUtil.replace(saveMapperName, Stu, st);
        updateMapperName = NameUtil.replace(updateMapperName, Stu, st);
        deleteMapperName = NameUtil.replace(deleteMapperName, Stu, st);
        getByIdMapperName = NameUtil.replace(getByIdMapperName, Stu, st);
        List<String> list = RandomUtils.list(table);
        Map<String, Boolean> importMap = RandomUtils.importMap(list);
        Boolean hutoolImport = importMap.get("hutool");
        Boolean otherImport = importMap.get("other");
        sum.append("package " + pack + "." + projectName + ".test;" + nl);
        sum.append("" + nl);
        if ("true".equalsIgnoreCase(table[0][2])) {
            sum.append("// import java.math.BigDecimal;" + nl);
            if (!"true".equalsIgnoreCase(table[0][1])) {
                sum.append("" + nl);
            }
        }
        if ("true".equalsIgnoreCase(table[0][1])) {
            sum.append("// import java.util.Date;" + nl);
            sum.append("" + nl);
        }

        if (otherImport) {
            sum.append("import " + pack + "." + projectName + ".test.util.RandomUtils;" + nl);
            sum.append(nl);
        }
        if (hutoolImport) {
            sum.append("import cn.hutool.core.util.RandomUtil;" + nl);
            sum.append(nl);
        }
        sum.append("import org.junit.After;" + nl);
        sum.append("import org.junit.Before;" + nl);
        sum.append("import org.junit.Test;" + nl);
        sum.append("import org.springframework.context.ApplicationContext;" + nl);
        sum.append("import org.springframework.context.support.ClassPathXmlApplicationContext;" + nl);
        sum.append("" + nl);
        sum.append("import " + pack + "." + projectName + "." + pojo + "." + Stu + ";" + nl);
        sum.append("import " + pack + "." + projectName + ".service." + Stu + "Service;" + nl);
        sum.append("" + nl);
        sum.append("public class " + Stu + "Test {" + nl);
        sum.append("    private " + Stu + "Service " + st + "Service;" + nl);
        sum.append("" + nl);
        sum.append("    @Before" + nl);
        sum.append("    public void before() {" + nl);
        sum.append("        ApplicationContext ac = new ClassPathXmlApplicationContext(" + nl);
        sum.append("                new String[] { \"applicationContext.xml\", \"spring-mybatis.xml\" });" + nl);
        sum.append("        " + st + "Service = (" + Stu + "Service) ac.getBean(\"" + st + "Service\");" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    @After" + nl);
        sum.append("    public void after() {" + nl);
        sum.append("" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    // @Test" + nl);
        sum.append("    public void " + saveMapperName + "() {" + nl);
        sum.append("        " + Stu + " " + st + " = new " + Stu + "();" + nl);
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            String fieldType = table[i][1];
            if (EnumUtils.contains(fieldName)) {
                fieldType = fieldName;
            }
            if (ArrayUtils.isNumIdField(table, i)) {
                sum.append(
                        "        // " + st + ".set" + fieldName + "(" + RandomUtils.createValue(fieldType) + ");" + nl);
            } else {
                sum.append("        " + st + ".set" + fieldName + "(" + RandomUtils.createValue(fieldType) + ");" + nl);
            }
        }
        sum.append("        " + st + "Service." + saveMapperName + "(" + st + ");" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    @Test" + nl);
        sum.append("    public void test() {" + nl);
        sum.append("" + nl);
        sum.append("    }" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
