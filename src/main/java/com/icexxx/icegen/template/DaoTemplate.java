package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.ArrayUtils;

public class DaoTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String dao = dataMap.get("dao");
        String pojo = dataMap.get("pojo");
        String[][] table = data.getTable(className);
        String idType = ArrayUtils.idType(table);
        sum.append("package " + pack + "." + projectName + "." + dao + ";" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import " + pack + "." + projectName + "." + pojo + "." + Stu + ";" + nl);
        sum.append("" + nl);
        sum.append("public interface " + Stu + "Mapper {" + nl);
        sum.append("    int deleteByPrimaryKey(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    int insert(" + Stu + " record);" + nl);
        sum.append("" + nl);
        sum.append("    int insertSelective(" + Stu + " record);" + nl);
        sum.append("" + nl);
        sum.append("    " + Stu + " selectByPrimaryKey(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    int updateByPrimaryKeySelective(" + Stu + " record);" + nl);
        sum.append("" + nl);
        sum.append("    int updateByPrimaryKey(" + Stu + " record);" + nl);
        sum.append("" + nl);
        sum.append("    List<" + Stu + "> findAll();" + nl);
        sum.append("" + nl);
        sum.append("    int deleteBatch(" + idType + "[] ids);" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
