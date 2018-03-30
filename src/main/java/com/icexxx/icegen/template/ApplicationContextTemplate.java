package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class ApplicationContextTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        sum.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + nl);
        sum.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"" + nl);
        sum.append(
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:context=\"http://www.springframework.org/schema/context\""
                        + nl);
        sum.append("    xsi:schemaLocation=\"" + nl);
        sum.append("  http://www.springframework.org/schema/beans " + nl);
        sum.append("  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd " + nl);
        sum.append("  http://www.springframework.org/schema/context " + nl);
        sum.append("  http://www.springframework.org/schema/context/spring-context-3.0.xsd\">" + nl);
        sum.append("" + nl);
        sum.append("    <context:property-placeholder location=\"classpath:db.properties\" />" + nl);
        sum.append("    <context:component-scan base-package=\"" + pack + "." + projectName + "\" />" + nl);
        sum.append("</beans>" + nl);
        String content = sum.toString();
        return content;
    }
}
