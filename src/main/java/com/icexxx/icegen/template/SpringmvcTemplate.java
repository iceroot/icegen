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
        sum.append("    <context:component-scan base-package=\"" + pack + "." + projectName + ".controller\"/>" + nl);
        sum.append("    " + nl);
        sum.append("    <!--注解映射器 -->" + nl);
        sum.append(
                "    <!-- <bean class=\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping\"/> -->"
                        + nl);
        sum.append("    <!--注解适配器 -->" + nl);
        sum.append(
                "    <!-- <bean class=\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter\"/> -->"
                        + nl);
        sum.append("    <!--" + nl);
        sum.append("                  使用mvc:annotation-driven代替上边注解映射器和注解适配器 配置" + nl);
        sum.append("                  mvc:annotation-driven默认加载很多的参数绑定方法," + nl);
        sum.append(
                "                  比如json转换解析器就默认加载了，如果使用mvc:annotation-driven就不用配置上面的RequestMappingHandlerMapping和RequestMappingHandlerAdapter"
                        + nl);
        sum.append("                  实际开发时使用mvc:annotation-driven" + nl);
        sum.append("     -->" + nl);
        sum.append("     <mvc:annotation-driven/>" + nl);
        sum.append("     <!-- 2.静态资源默认servlet配置          (1)加入对静态资源的处理：js,gif,png    (2)允许使用\"/\"做整体映射  -->" + nl);
        sum.append("     <mvc:default-servlet-handler /> " + nl);
        sum.append("     <!--" + nl);
        sum.append("     <mvc:resources mapping=\"/img/**\" location=\"/img/\" />" + nl);
        sum.append("     <mvc:resources mapping=\"/js/**\" location=\"/js/\" />" + nl);
        sum.append("     <mvc:resources mapping=\"/css/**\" location=\"/css/\" />" + nl);
        sum.append("     <mvc:resources mapping=\"/html/**\" location=\"/html/\" />" + nl);
        sum.append("     <mvc:resources mapping=\"/jsp/**\" location=\"/jsp/\" /> " + nl);
        sum.append("     -->" + nl);
        sum.append("     <!-- " + nl);
        sum.append("                  配置视图解析器 " + nl);
        sum.append("                 解析jsp视图,默认使用jstl标签,所以classpath下得有jstl的包" + nl);
        sum.append("     -->" + nl);
        sum.append("    <!-- 避免IE执行ajax时,返回json出现下载文件 -->" + nl);
        sum.append("    <bean id=\"mappingJacksonHttpMessageConverter\"" + nl);
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
        sum.append(
                "    <!-- 启动Spring mvc的注解功能,完成请求和注解pojo的映射, 配置一个基于注解的定制的WebBindingInitializer,解决日期转换问题,方法级别的处理器映射 -->"
                        + nl);
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
