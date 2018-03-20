package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

import cn.hutool.core.util.StrUtil;

public class ServiceTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String st = StrUtil.lowerFirst(Stu);
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String pojo = dataMap.get("pojo");
        sum.append("package " + pack + "." + projectName + ".service;" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import " + pack + "." + projectName + "." + pojo + "." + Stu + ";" + nl);
        sum.append("" + nl);
        sum.append("public interface " + Stu + "Service {" + nl);
        sum.append("    void add(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    " + Stu + " getById(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    List<" + Stu + "> getAll();" + nl);
        sum.append("" + nl);
        sum.append("    int deleteBatch(String[] ids);" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
