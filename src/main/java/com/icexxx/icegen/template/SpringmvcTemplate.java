package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class SpringmvcTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        sum.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"" + nl);
        sum.append(
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:mvc=\"http://www.springframework.org/schema/mvc\""
                        + nl);
        sum.append("    xmlns:context=\"http://www.springframework.org/schema/context\"" + nl);
        sum.append(
                "    xmlns:aop=\"http://www.springframework.org/schema/aop\" xmlns:tx=\"http://www.springframework.org/schema/tx\""
                        + nl);
        sum.append("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans " + nl);
        sum.append("        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd" + nl);
        sum.append("        http://www.springframework.org/schema/mvc " + nl);
        sum.append("        http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd " + nl);
        sum.append("        http://www.springframework.org/schema/context " + nl);
        sum.append("        http://www.springframework.org/schema/context/spring-context-3.2.xsd " + nl);
        sum.append("        http://www.springframework.org/schema/aop " + nl);
        sum.append("        http://www.springframework.org/schema/aop/spring-aop-3.2.xsd " + nl);
        sum.append("        http://www.springframework.org/schema/tx " + nl);
        sum.append("        http://www.springframework.org/schema/tx/spring-tx-3.2.xsd\">" + nl);
        sum.append("" + nl);
        sum.append("     <context:component-scan base-package=\"" + pack + "." + projectName + ".controller\"/>" + nl);
        sum.append("     <mvc:annotation-driven/>" + nl);
        sum.append("     <mvc:default-servlet-handler /> " + nl);
        sum.append("     <bean id=\"mappingJacksonHttpMessageConverter\"" + nl);
        sum.append(
                "        class=\"org.springframework.http.converter.json.MappingJackson2HttpMessageConverter\">" + nl);
        sum.append("        <property name=\"supportedMediaTypes\">" + nl);
        sum.append("            <list>" + nl);
        sum.append("                <value>text/json;charset=UTF-8</value>" + nl);
        sum.append("                <value>text/html;charset=UTF-8</value>" + nl);
        sum.append("                <value>application/json;charset=UTF-8</value>" + nl);
        sum.append("            </list>" + nl);
        sum.append("        </property>" + nl);
        sum.append("    </bean>" + nl);
        sum.append("" + nl);
        sum.append("    <bean" + nl);
        sum.append("        class=\"org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter\">"
                + nl);
        sum.append("        <property name=\"cacheSeconds\" value=\"0\" />" + nl);
        sum.append("        <property name=\"messageConverters\">" + nl);
        sum.append("            <list>" + nl);
        sum.append("                <ref bean=\"mappingJacksonHttpMessageConverter\" />" + nl);
        sum.append("            </list>" + nl);
        sum.append("        </property>" + nl);
        sum.append("    </bean>" + nl);
        sum.append("    <bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">" + nl);
        sum.append("        <property name=\"prefix\" value=\"/WEB-INF/jsp/\"/>" + nl);
        sum.append("        <property name=\"suffix\" value=\".jsp\"/>" + nl);
        sum.append("    </bean>" + nl);
        sum.append("</beans>" + nl);
        String content = sum.toString();
        return content;
    }
}
