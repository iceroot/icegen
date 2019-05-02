package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class TestUtilTemplate implements Template {

	@Override
	public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
		StringBuilder sum = new StringBuilder();
		String nl = Count.NEWLINE;
		String pack = dataMap.get("domain");
		String projectName = dataMap.get("projectName");
		sum.append("package " + pack + "." + projectName + ".test.util;" + nl);
		sum.append("" + nl);
		sum.append("import java.math.BigDecimal;" + nl);
		sum.append("import java.math.RoundingMode;" + nl);
		sum.append("import java.util.Date;" + nl);
		sum.append("" + nl);
		sum.append("import cn.hutool.core.util.NumberUtil;" + nl);
		sum.append("import cn.hutool.core.util.RandomUtil;" + nl);
		sum.append("" + nl);
		sum.append("public class RandomUtils {" + nl);
		sum.append("    public static double roundDouble() {" + nl);
		sum.append("        return RandomUtil.randomDouble(0d, 10000d, 2, RoundingMode.HALF_UP);" + nl);
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("    public static BigDecimal roundBigDecimal() {" + nl);
		sum.append("        BigDecimal num = NumberUtil.round(RandomUtil.randomDouble(10000d), 2);" + nl);
		sum.append("        return num;" + nl);
		sum.append("    }" + nl);
		sum.append("" + nl);
		sum.append("    public static Date roundDate() {" + nl);
		sum.append("        Date date = RandomUtil.randomDay(-365, 365);" + nl);
		sum.append("        return date;" + nl);
		sum.append("    }" + nl);
		sum.append("}" + nl);
		String content = sum.toString();
		return content;
	}
}
