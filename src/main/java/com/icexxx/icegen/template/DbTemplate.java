package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class DbTemplate implements Template {

	@Override
	public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
		StringBuilder sum = new StringBuilder();
		String nl = Count.NEWLINE;
		String username = dataMap.get("username");
		String password = dataMap.get("password");
		String url = dataMap.get("url");
		String driver = dataMap.get("driver");
		sum.append("jdbc.driver=" + driver + nl);
		sum.append("#jdbc.driver=net.sf.log4jdbc.DriverSpy" + nl);
		sum.append(
				"jdbc.url=" + url + "?characterEncoding=UTF-8&useUnicode=true&zeroDateTimeBehavior=convertToNull" + nl);
		sum.append("jdbc.username=" + username + nl);
		sum.append("jdbc.password=" + password + nl);
		String content = sum.toString();
		return content;
	}
}
