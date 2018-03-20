package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

import cn.hutool.core.util.StrUtil;

public class JspTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String st = StrUtil.lowerFirst(Stu);
        String projectName = dataMap.get("projectName");
        String[][] table = data.getTable(className);
        sum.append("<%@ page language=\"java\" pageEncoding=\"UTF-8\"%>" + nl);
        sum.append("<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>" + nl);
        sum.append("<!DOCTYPE html>" + nl);
        sum.append("<html>" + nl);
        sum.append("    <head>" + nl);
        sum.append("        <title>显示用户信息</title>" + nl);
        sum.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + nl);
        sum.append("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>" + nl);
        sum.append(
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"/" + projectName + "/css/index.css\">" + nl);
        sum.append("        <script type=\"text/javascript\" src=\"/" + projectName
                + "/js/jquery.1.11.3.min.js\"></script>" + nl);
        sum.append("        <!--[if (gte IE 6)&(lte IE 8)]>" + nl);
        sum.append("            <script type=\"text/javascript\" src=\"/" + projectName
                + "/js/selectivizr.1.0.2.min.js\"></script>" + nl);
        sum.append("            <noscript><link rel=\"stylesheet\" href=\"[fallback css]\" /></noscript>" + nl);
        sum.append("        <![endif]-->" + nl);
        sum.append("        <script type=\"text/javascript\" src=\"/" + projectName + "/js/index.js\"></script>" + nl);
        sum.append("    </head>" + nl);
        sum.append("    <body>" + nl);
        sum.append("        <div class=\"css3\"><span>css3</span></div>" + nl);
        sum.append("        <div class=\"javascript\">javascript</div>" + nl);
        sum.append("        <table>" + nl);
        sum.append("            <tr>" + nl);
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            sum.append("                <td>" + fieldName + "</td>" + nl);
        }
        sum.append("                <td>操作</td>" + nl);
        sum.append("            </tr>" + nl);
        sum.append("            <c:forEach var=\"" + st + "\" items=\"${" + st + "List" + "}\">" + nl);
        sum.append("                <tr>" + nl);
        for (int i = 1; i < table.length; i++) {
            String fieldName = StrUtil.upperFirst(table[i][0]);
            String fieldNameLower = StrUtil.lowerFirst(fieldName);
            sum.append("                    <td>${" + st + "." + fieldNameLower + "}</td>" + nl);
        }
        sum.append("                    <td><a href=\"/" + projectName + "/" + st + "/batchDelete.html?ids=${" + st
                + ".id}\">删除</a></td>" + nl);
        sum.append("                </tr>" + nl);
        sum.append("            </c:forEach>" + nl);
        sum.append("        </table>" + nl);
        sum.append("    </body>" + nl);
        sum.append("</html>" + nl);
        String content = sum.toString();
        return content;
    }
}
