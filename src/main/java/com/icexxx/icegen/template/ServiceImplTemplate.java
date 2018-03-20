package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.ArrayUtils;

import cn.hutool.core.util.StrUtil;

public class ServiceImplTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String st = StrUtil.lowerFirst(Stu);
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String dao = dataMap.get("dao");
        String pojo = dataMap.get("pojo");
        String[][] table = data.getTable(className);
        String idType = ArrayUtils.idType(table);
        boolean isStringType = ArrayUtils.isStringType(idType);
        sum.append("package " + pack + "." + projectName + ".service.impl;" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import org.springframework.beans.factory.annotation.Autowired;" + nl);
        sum.append("import org.springframework.stereotype.Service;" + nl);
        sum.append("" + nl);
        sum.append("import " + pack + "." + projectName + "." + dao + "." + Stu + "Mapper;" + nl);
        sum.append("import " + pack + "." + projectName + "." + pojo + "." + Stu + ";" + nl);
        sum.append("import " + pack + "." + projectName + ".service." + Stu + "Service;" + nl);
        sum.append("" + nl);
        sum.append("@Service(\"" + st + "Service\")" + nl);
        sum.append("public class " + Stu + "ServiceImpl implements " + Stu + "Service {" + nl);
        sum.append("    @Autowired" + nl);
        sum.append("    private " + Stu + "Mapper " + st + "Mapper;" + nl);
        sum.append("" + nl);
        sum.append("    public void add(" + Stu + " " + st + ") {" + nl);
        sum.append("        " + st + "Mapper.insert(" + st + ");" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    public " + Stu + " getById(Integer " + st + "Id) {" + nl);
        sum.append("        return " + st + "Mapper.selectByPrimaryKey(" + st + "Id);" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    public List<" + Stu + "> getAll() {" + nl);
        sum.append("        return " + st + "Mapper.findAll();" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    public int deleteBatch(String[] ids) {" + nl);
        if (!isStringType) {
            sum.append("        " + idType + "[] idsInt = idsInt(ids);" + nl);
        }
        if (!isStringType) {
            sum.append("        return " + st + "Mapper.deleteBatch(idsInt);" + nl);
        } else {
            sum.append("        return " + st + "Mapper.deleteBatch(ids);" + nl);
        }
        sum.append("    }" + nl);
        sum.append("" + nl);
        if (!isStringType) {
            String idTypeFun = "Int";
            if ("Long".equalsIgnoreCase(idType)) {
                idTypeFun = "Long";
            }
            sum.append("    private " + idType + "[] idsInt(String[] ids) {" + nl);
            sum.append("        " + idType + "[] nums = new " + idType + "[ids.length];" + nl);
            sum.append("        for (int i = 0; i < ids.length; i++) {" + nl);
            sum.append("            nums[i] = " + idType + ".parse" + idTypeFun + "(ids[i]);" + nl);
            sum.append("        }" + nl);
            sum.append("        return nums;" + nl);
            sum.append("    }" + nl);
        }
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
