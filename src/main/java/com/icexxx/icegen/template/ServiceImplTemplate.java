package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.ArrayUtils;
import com.icexxx.icegen.utils.NameUtil;
import com.icexxx.icegen.utils.PojoMapUtil;

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
		boolean isStringType = ArrayUtils.isStringType(idType);
		String pojoName = pack + "." + projectName + "." + pojo + "." + Stu + "";
		String keyPojo = Stu + "{pojoName}";
		pojoName = PojoMapUtil.get(keyPojo, pojoName);
		sum.append("package " + pack + "." + projectName + ".service.impl;" + nl);
		sum.append("" + nl);
		sum.append("import java.util.List;" + nl);
		sum.append("" + nl);
		sum.append("import org.springframework.beans.factory.annotation.Autowired;" + nl);
		sum.append("import org.springframework.stereotype.Service;" + nl);
		sum.append("" + nl);
		sum.append("import " + pack + "." + projectName + "." + dao + "." + Stu + "Mapper;" + nl);
		sum.append("import " + pojoName + ";" + nl);
		sum.append("import " + pack + "." + projectName + ".service." + Stu + "Service;" + nl);
		sum.append("" + nl);
		sum.append("import cn.hutool.core.convert.Convert;" + nl);
		sum.append("" + nl);
		sum.append("@Service(\"" + st + "Service\")" + nl);
		sum.append("public class " + Stu + "ServiceImpl implements " + Stu + "Service {" + nl);
		sum.append("    @Autowired" + nl);
		sum.append("    private " + Stu + "Mapper " + st + "Mapper;" + nl);
		sum.append("" + nl);
		sum.append("    public void " + saveMapperName + "(" + Stu + " " + st + ") {" + nl);
		sum.append("        " + st + "Mapper." + saveMapperName + "(" + st + ");" + nl);
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("    public " + Stu + " " + getByIdMapperName + "(Integer " + st + "Id) {" + nl);
		sum.append("        return " + st + "Mapper." + getByIdMapperName + "(" + st + "Id);" + nl);
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("    public List<" + Stu + "> getAll() {" + nl);
		sum.append("        return " + st + "Mapper.findAll();" + nl);
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("    public int deleteBatch(List<" + "String" + "> ids) {" + nl);
		if (!isStringType) {
			sum.append("        " + "List<" + idType + "> idsInt = Convert.toList(" + idType + ".class, ids);" + nl);
		}
		if (!isStringType) {
			sum.append("        return " + st + "Mapper.deleteBatch(idsInt);" + nl);
		} else {
			sum.append("        return " + st + "Mapper.deleteBatch(ids);" + nl);
		}
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("}" + nl);
		String content = sum.toString();
		return content;
	}
}
