package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.ArrayUtils;
import com.icexxx.icegen.utils.NameUtil;
import com.icexxx.icegen.utils.PojoMapUtil;

import cn.hutool.core.util.StrUtil;

public class DaoTemplate implements Template {

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
        String saveMapperName = dataMap.get("saveMapperName");
        String updateMapperName = dataMap.get("updateMapperName");
        String deleteMapperName = dataMap.get("deleteMapperName");
        String getByIdMapperName = dataMap.get("getByIdMapperName");
        saveMapperName = NameUtil.replace(saveMapperName, Stu, st);
        updateMapperName = NameUtil.replace(updateMapperName, Stu, st);
        deleteMapperName = NameUtil.replace(deleteMapperName, Stu, st);
        getByIdMapperName = NameUtil.replace(getByIdMapperName, Stu, st);
        String[][] table = data.getTable(className);
        String idType = ArrayUtils.idType(table);
        sum.append("package " + pack + "." + projectName + "." + dao + ";" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import org.apache.ibatis.annotations.Param;" + nl);
        sum.append("" + nl);
        String pojoName = pack + "." + projectName + "." + pojo + "." + Stu + "";
        String keyPojo = Stu + "{pojoName}";
        pojoName = PojoMapUtil.get(keyPojo, pojoName);
        sum.append("import " + pojoName + ";" + nl);
        sum.append("" + nl);
        sum.append("public interface " + Stu + "Mapper {" + nl);
        sum.append("    int " + deleteMapperName + "(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    int " + saveMapperName + "(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    int insertSelective(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    " + Stu + " " + getByIdMapperName + "(Integer id);" + nl);
        sum.append("" + nl);
        sum.append("    int " + updateMapperName + "(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    int updateByPrimaryKey(" + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        sum.append("    List<" + Stu + "> findAll();" + nl);
        sum.append("" + nl);
        sum.append("    List<" + Stu + "> listAll(" + "@Param(\"" + st + "\") " + Stu + " " + st + ");" + nl);
        sum.append("" + nl);
        String ids = "ids";
        sum.append("    int deleteBatch(" + "@Param(\"" + ids + "\") List<" + idType + "> " + ids + ");" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
