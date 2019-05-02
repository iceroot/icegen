package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class IndexTemplate implements Template {

	@Override
	public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
		StringBuilder sum = new StringBuilder();
		String nl = Count.NEWLINE;
		String projectName = dataMap.get("projectName");
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
		sum.append("        <style type=\"text/css\">" + nl);
		sum.append("            table tr:first-child td:last-child {" + nl);
		sum.append("                background-color:lightgreen;" + nl);
		sum.append("            }" + nl);
		sum.append("        </style>" + nl);
		sum.append("        <script type=\"text/javascript\" src=\"/" + projectName + "/js/index.js\"></script>" + nl);
		sum.append("    </head>" + nl);
		sum.append("    <body>" + nl);
		sum.append("        <div class=\"css3\"><span>css3</span></div>" + nl);
		sum.append("        <div class=\"javascript\">javascript</div>" + nl);
		sum.append("        <table>" + nl);
		sum.append("            <tr>" + nl);
		sum.append("                <td>用户ID</td>" + nl);
		sum.append("                <td>用户名</td>" + nl);
		sum.append("                <td>出生日期</td>" + nl);
		sum.append("                <td>工资</td>" + nl);
		sum.append("                <td>登陆次数</td>" + nl);
		sum.append("                <td>点赞数</td>" + nl);
		sum.append("                <td>账户余额</td>" + nl);
		sum.append("                <td>登录状态</td>" + nl);
		sum.append("            </tr>" + nl);
		sum.append("            <c:forEach var=\"user\" items=\"${user}\">" + nl);
		sum.append("                <tr>" + nl);
		sum.append("                    <td>${user.id}</td>" + nl);
		sum.append("                    <td>${user.username}</td>" + nl);
		sum.append("                    <td>${user.birthday}</td>" + nl);
		sum.append("                    <td>${user.salary}</td>" + nl);
		sum.append("                    <td>${user.logincount}</td>" + nl);
		sum.append("                    <td>${user.praise}</td>" + nl);
		sum.append("                    <td>${user.account}</td>" + nl);
		sum.append("                    <td>${user.status}</td>" + nl);
		sum.append("                </tr>" + nl);
		sum.append("            </c:forEach>" + nl);
		sum.append("        </table>" + nl);
		sum.append("    </body>" + nl);
		sum.append("</html>" + nl);
		String content = sum.toString();
		return content;
	}
}
