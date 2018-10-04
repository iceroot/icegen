package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.NameUtil;
import com.icexxx.icegen.utils.PojoMapUtil;

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
        String saveMapperName = dataMap.get("saveMapperName");
        String updateMapperName = dataMap.get("updateMapperName");
        String deleteMapperName = dataMap.get("deleteMapperName");
        String getByIdMapperName = dataMap.get("getByIdMapperName");
        saveMapperName = NameUtil.replace(saveMapperName, Stu, st);
        updateMapperName = NameUtil.replace(updateMapperName, Stu, st);
        deleteMapperName = NameUtil.replace(deleteMapperName, Stu, st);
        getByIdMapperName = NameUtil.replace(getByIdMapperName, Stu, st);
        String pojoName = pack + "." + projectName + "." + pojo + "." + Stu + "";
        String keyPojo = Stu + "{pojoName}";
        pojoName = PojoMapUtil.get(keyPojo, pojoName);
        sum.append("package " + pack + "." + projectName + ".service;" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import " + pojoName + ";" + nl);
        sum.append("" + nl);
        sum.append("public interface " + Stu + "Service {" + nl);
        sum.append("    void " + saveMapperName + "(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    " + Stu + " " + getByIdMapperName + "(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    List<" + Stu + "> getAll();" + nl);
        sum.append("" + nl);
        sum.append("    int deleteBatch(String[] ids);" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
