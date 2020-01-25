package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.utils.PojoMapUtil;

import cn.hutool.core.util.StrUtil;

public class ControllerTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String Stu = className;
        String st = StrUtil.lowerFirst(Stu);
        String pack = dataMap.get("domain");
        String projectName = dataMap.get("projectName");
        String pojo = dataMap.get("pojo");
        String pojoPack = dataMap.get("pojoPack");
        if (StrUtil.isNotBlank(pojoPack)) {
            pojoPack = "." + pojoPack;
        } else {
            pojoPack = "";
        }
        String pojoName = pack + "." + projectName + pojoPack + "." + pojo + "." + Stu + "";
        String keyPojo = Stu + "{pojoName}";
        pojoName = PojoMapUtil.get(keyPojo, pojoName);
        sum.append("package " + pack + "." + projectName + ".controller;" + nl);
        sum.append("" + nl);
        sum.append("import java.util.List;" + nl);
        sum.append("" + nl);
        sum.append("import javax.servlet.http.HttpServletRequest;" + nl);
        sum.append("import javax.servlet.http.HttpServletResponse;" + nl);
        sum.append("" + nl);
        sum.append("import org.apache.log4j.Logger;" + nl);
        sum.append("" + nl);
        sum.append("import org.springframework.beans.factory.annotation.Autowired;" + nl);
        sum.append("import org.springframework.stereotype.Controller;" + nl);
        sum.append("import org.springframework.util.CollectionUtils;" + nl);
        sum.append("import org.springframework.util.StringUtils;" + nl);
        sum.append("import org.springframework.web.bind.annotation.RequestMapping;" + nl);
        sum.append("import org.springframework.web.bind.annotation.ResponseBody;" + nl);
        sum.append("" + nl);
        sum.append("import " + pojoName + ";" + nl);
        sum.append("import " + pack + "." + projectName + ".service." + Stu + "Service;" + nl);
        sum.append("" + nl);
        sum.append("@Controller" + nl);
        sum.append("@RequestMapping(\"/" + st + "\")" + nl);
        sum.append("public class " + Stu + "Controller {" + nl);
        sum.append("" + nl);
        sum.append("    private static Logger log = Logger.getLogger(" + Stu + "Controller.class);" + nl);
        sum.append("    @Autowired" + nl);
        sum.append("    private " + Stu + "Service " + st + "Service;" + nl);
        sum.append("" + nl);
        sum.append("    // http://127.0.0.1:8080/" + projectName + "/" + st + "/find.do" + nl);
        sum.append("    @RequestMapping(\"/find\")" + nl);
        sum.append("    public String find(HttpServletRequest request, HttpServletResponse response) {" + nl);
        sum.append("        log.info(\"/" + st + "/find.do\");" + nl);
        sum.append("        List<" + Stu + "> " + st + " = " + st + "Service.getAll();" + nl);
        sum.append("        request.setAttribute(\"" + st + "List" + "\", " + st + ");" + nl);
        sum.append("        return \"" + st + "\";" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    // http://127.0.0.1:8080/" + projectName + "/" + st + "/list.do" + nl);
        sum.append("    @ResponseBody" + nl);
        sum.append("    @RequestMapping(\"/list\")" + nl);
        sum.append(
                "    public List<" + Stu + "> list(HttpServletRequest request, HttpServletResponse response) {" + nl);
        sum.append("        log.info(\"/" + st + "/list.do\");" + nl);
        sum.append("        List<" + Stu + "> " + st + " = " + st + "Service.getAll();" + nl);
        sum.append("        return " + st + ";" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    // http://127.0.0.1:8080/" + projectName + "/" + st + "/batchDelete.html?ids=1001,1002" + nl);
        sum.append("    @RequestMapping(\"/batchDelete\")" + nl);
        sum.append(
                "    public String batchDelete(String ids, HttpServletRequest request, HttpServletResponse response) {"
                        + nl);
        sum.append("        log.info(\"ids=\" + ids);" + nl);
        sum.append("        @SuppressWarnings(\"unchecked\")" + nl);
        sum.append("        List<String> arrayToList =" + nl);
        sum.append("        CollectionUtils.arrayToList(StringUtils.tokenizeToStringArray(ids, \",\"));" + nl);
        sum.append("        int count = " + st + "Service.deleteBatch(arrayToList);" + nl);
        sum.append("        System.out.println(\"删除\" + count + \"条记录\");" + nl);
        // sum.append(" List<"+Stu+"> "+st+" = "+st+"Service.getAll();" + nl );
        // sum.append(" request.setAttribute(\""+st+"List"+"\", "+st+");" + nl
        // );
        // sum.append(" return \""+st+"\";" + nl );
        sum.append("        return \"redirect:/" + st + "/find.html\";" + nl);
        sum.append("    }" + nl);
        sum.append("" + nl);
        sum.append("    // http://127.0.0.1:8080/" + projectName + "/" + st + "/update.do" + nl);
        sum.append("    @ResponseBody" + nl);
        sum.append("    @RequestMapping(\"/update\")" + nl);
        sum.append(
                "    public List<" + Stu + "> update(HttpServletRequest request, HttpServletResponse response) {" + nl);
        sum.append("        log.info(\"/" + st + "/update.do\");" + nl);
        sum.append("        //List<" + Stu + "> " + st + " = " + st + "Service.update();" + nl);
        sum.append("        return null;" + nl);
        sum.append("    }" + nl);
        sum.append("}" + nl);
        String content = sum.toString();
        return content;
    }
}
