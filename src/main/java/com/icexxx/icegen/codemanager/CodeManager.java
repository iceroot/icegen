package com.icexxx.icegen.codemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.icexxx.icegen.data.AllData;
import com.icexxx.icegen.data.DbData;
import com.icexxx.icegen.data.JavaData;
import com.icexxx.icegen.data.PojoData;
import com.icexxx.icegen.enumsupport.EnumManager;
import com.icexxx.icegen.format.DataBaseUtils;
import com.icexxx.icegen.initdata.JdbcData;
import com.icexxx.icegen.staticdata.StaticData;
import com.icexxx.icegen.template.ApplicationContextTemplate;
import com.icexxx.icegen.template.ControllerTemplate;
import com.icexxx.icegen.template.DaoTemplate;
import com.icexxx.icegen.template.DbTemplate;
import com.icexxx.icegen.template.IndexTemplate;
import com.icexxx.icegen.template.JspTemplate;
import com.icexxx.icegen.template.MyBatisMapperTemplate;
import com.icexxx.icegen.template.PojoTemplate;
import com.icexxx.icegen.template.PomTemplate;
import com.icexxx.icegen.template.ServiceImplTemplate;
import com.icexxx.icegen.template.ServiceTemplate;
import com.icexxx.icegen.template.SpringMybatisTemplate;
import com.icexxx.icegen.template.SpringTestTemplate;
import com.icexxx.icegen.template.SpringmvcTemplate;
import com.icexxx.icegen.template.TestTemplate;
import com.icexxx.icegen.template.TestUtilTemplate;
import com.icexxx.icegen.utils.ExcelUtils;
import com.icexxx.icegen.utils.GenContext;
import com.icexxx.icegen.utils.PathUtils;
import com.icexxx.icegen.utils.StringUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;

public class CodeManager {

    /**
     * 程序执行的总方法
     */
    public static void execute(HashMap<String, String> config) {
        String enumPath = config.get("enum");
        EnumManager.execute(enumPath);
        HashMap<String, String> configFormat = CodeManager.format(config);
        StaticData.setFormatConfig(config);
        GenContext.setMap(configFormat);
        String domain = configFormat.get("domain");
        String projectName = configFormat.get("projectName");
        String pojo = configFormat.get("pojo");
        String domainPath = domain.replace(".", "/");
        GenContext.setDomainPath(domainPath);
        GenContext.setPackageName(domain);
        GenContext.setProjectName(projectName);
        GenContext.setPojo(pojo);
        String currentPath = StrUtil.removeSuffix(ClassUtil.getClassPath(), "/");
        if (currentPath.endsWith("/bin")) {
            currentPath = StrUtil.removeSuffix(currentPath, "/bin");
            currentPath = StrUtil.addSuffixIfNot(currentPath, "/src");
        } else {
            currentPath = StrUtil.removeSuffix(currentPath, "/target/classes");
            currentPath = StrUtil.addSuffixIfNot(currentPath, "/src/main/java");
        }
        boolean isSrcCode = new File(currentPath + "/original").exists();
        GenContext.setSrcCode(isSrcCode);
        if (isSrcCode) {
            OriginalService.original(currentPath + "/original");
            OriginalService.template(currentPath + "/template");
        } else {
            OriginalService.jarCode("original", "template");
        }
        CodeManager.executeFormat(configFormat);
    }

    private static void executeFormat(HashMap<String, String> configFormat) {
        Data data = null;
        Template template = null;
        String path = null;
        String[][][] jdbcData = JdbcData.initJdbcData(configFormat);
        String[][][] javaData = JdbcData.initJavaData(jdbcData);
        String baseFolder = configFormat.get("out");
        String fileName = baseFolder + "/debug.xls";
        String fullFolder = baseFolder + Count.SHASH + "src_" + configFormat.get("projectName").toLowerCase();
        FileUtil.del(fullFolder);
        String debug = configFormat.get("debug");
        if (StrUtil.isNotBlank(debug)) {
            if ("true".equalsIgnoreCase(debug) || "show".equalsIgnoreCase(debug)) {
                FileUtil.mkParentDirs(fileName);
                ExcelUtils.export(javaData, fileName);
            }
        }

        String pojoPack = configFormat.get("pojoPack");
        if (StrUtil.isNotBlank(pojoPack)) {
        	pojoPack = "/" + pojoPack;
        } else {
        	pojoPack = "";
        }

        template = new PomTemplate();
        data = new PojoData();
        path = "pom.xml";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "README.md";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".classpath";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".project";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "LICENSE";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/.jsdtscope";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.jdt.core.prefs";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.m2e.core.prefs";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.wst.common.component";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.wst.common.project.facet.core.xml";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.wst.jsdt.ui.superType.container";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.wst.jsdt.ui.superType.name";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = ".settings/org.eclipse.wst.validation.prefs";
        writeFile(template, data, path);
        for (int i = 1; i < javaData.length; i++) {
            template = new ControllerTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/java/${domainPath}/${projectName}/controller/" + "${className}" + "Controller.java";
            writeFile(template, data, path);
        }
        for (int i = 1; i < javaData.length; i++) {
            template = new DaoTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/java/${domainPath}/${projectName}/dao/" + "${className}" + "Mapper.java";
            writeFile(template, data, path);
        }
        for (int i = 1; i < javaData.length; i++) {
            template = new PojoTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/java/${domainPath}/${projectName}" + pojoPack + "/${pojo}/" + "${className}" + ".java";
            writeFile(template, data, path);
        }
        for (int i = 1; i < javaData.length; i++) {
            template = new ServiceTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/java/${domainPath}/${projectName}/service/" + "${className}" + "Service.java";
            writeFile(template, data, path);
        }
        for (int i = 1; i < javaData.length; i++) {
            template = new ServiceImplTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/java/${domainPath}/${projectName}/service/impl/" + "${className}" + "ServiceImpl.java";
            writeFile(template, data, path);
        }
        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/java/${domainPath}/${projectName}/util/" + "readme.txt";
        writeFile(template, data, path);
        for (int i = 1; i < javaData.length; i++) {
            template = new MyBatisMapperTemplate();
            data = new AllData(javaData, jdbcData, i);
            path = "src/main/java/${domainPath}/${projectName}/mapping/" + "${className}" + "Mapper.xml";
            writeFile(template, data, path);
        }
        template = new ApplicationContextTemplate();
        data = new PojoData();
        path = "src/main/resources/applicationContext.xml";
        writeFile(template, data, path);

        template = new DbTemplate();
        data = new DbData();
        path = "src/main/resources/db.properties";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/resources/log4j.properties";
        writeFile(template, data, path);

        template = new SpringmvcTemplate();
        data = new PojoData();
        path = "src/main/resources/springmvc.xml";
        writeFile(template, data, path);

        template = new SpringMybatisTemplate();
        data = new PojoData();
        path = "src/main/resources/spring-mybatis.xml";
        writeFile(template, data, path);

        template = new IndexTemplate();
        data = new PojoData();
        path = "src/main/webapp/index.jsp";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/css/index.css";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/html5.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/icewood.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/icewood.min.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/icewood-import.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/index.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/jquery.1.11.3.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/jquery.1.11.3.min.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/lodash.min.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/selectivizr.1.0.2.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/js/selectivizr.1.0.2.min.js";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/WEB-INF/error/404.jsp";
        writeFile(template, data, path);

        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/WEB-INF/error/500.jsp";
        writeFile(template, data, path);
        for (int i = 1; i < javaData.length; i++) {
            template = new JspTemplate();
            data = new JavaData(javaData, i);
            path = "src/main/webapp/WEB-INF/jsp/${className.lower}.jsp";
            writeFile(template, data, path);
        }
        template = new PojoTemplate();
        data = new PojoData();
        path = "src/main/webapp/WEB-INF/web.xml";
        writeFile(template, data, path);
        for (int i = 1; i < javaData.length; i++) {
            template = new TestTemplate();
            data = new JavaData(javaData, i);
            path = "src/test/java/${domainPath}/${projectName}/test/" + "${className}" + "Test.java";
            writeFile(template, data, path);
        }
        for (int i = 1; i < javaData.length; i++) {
            template = new SpringTestTemplate();
            data = new JavaData(javaData, i);
            path = "src/test/java/${domainPath}/${projectName}/test/" + "${className}" + "SpringTest.java";
            writeFile(template, data, path);
        }
        template = new PojoTemplate();
        data = new PojoData();
        path = "src/test/resources/" + "empty.txt";
        writeFile(template, data, path);

        template = new TestUtilTemplate();
        data = new PojoData();
        path = "src/test/java/${domainPath}/${projectName}/test/util/RandomUtils.java";
        writeFile(template, data, path);

    }

    private static void writeFile(Template template, Data data, String path) {
        Map<String, String> map = GenContext.getMap();
        HashMap<String, String> dataMap = (HashMap<String, String>) map;
        String packageName = GenContext.getPackageName();
        String domainPath = GenContext.getDomainPath();
        String projectName = GenContext.getProjectName();
        String pojo = GenContext.getPojo();
        String className = null;
        if (path.contains("${className}")) {
            className = data.getData().get("className");
        }
        if (path.contains("${className.lower}")) {
            className = data.getData().get("className");
        }
        path = convertPath(path, domainPath, projectName, pojo, className);
        String simpleName = StrUtil.subAfter(path, "/", true);
        if (!path.contains("/")) {
            simpleName = path;
        }
        simpleName = StrUtil.removePrefix(simpleName, "$");
        String original = OriginalService.getOriginal(simpleName);
        String template2 = OriginalService.getTemplate(simpleName);
        String out = dataMap.get("out");
        String basePath = out + "/" + projectName;
        path = basePath + "/" + path;
        boolean isSrcCode =  GenContext.isSrcCode;
        if(isSrcCode){
            if (original != null) {
                FileUtil.copy(original, path, true);
            } else if (template2 != null) {
                PathUtils.templateFile(template2, path, projectName);
            } else {
                String code = template.getCode(dataMap, data, packageName, className);
                FileUtil.writeUtf8String(code, path);
            }
        }else{
            if (original != null) {
                InputStream resourceAsStream = CodeManager.class.getClassLoader().getResourceAsStream(original);
                OutputStream outputStream = null;
                FileUtil.mkParentDirs(path);
                try {
                    outputStream = new FileOutputStream(new File(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                IoUtil.copy(resourceAsStream, outputStream);
            } else if (template2 != null) {
                PathUtils.templateFileJar(template2, path, projectName);
            } else {
                String code = template.getCode(dataMap, data, packageName, className);
                FileUtil.writeUtf8String(code, path);
            }
        }
    }

    private static String convertPath(String path, String domainPath, String projectName, String pojo,
            String className) {
        if (StrUtil.isNotBlank(path)) {
            if (path.contains("${domainPath}")) {
                path = path.replace("${domainPath}", domainPath);
            }
            if (path.contains("${projectName}")) {
                path = path.replace("${projectName}", projectName);
            }
            if (path.contains("${projectNameIce}")) {
            	path = path.replace("${projectNameIce}", projectName);
            }
            if (path.contains("${pojo}")) {
                path = path.replace("${pojo}", pojo);
            }
            if (path.contains("${className}")) {
                path = path.replace("${className}", className);
            }
            if (path.contains("${className.lower}")) {
                path = path.replace("${className.lower}", StrUtil.lowerFirst(className));
            }
        }
        return path;
    }

    private static HashMap<String, String> format(HashMap<String, String> config) {
        String projectName = config.get("projectName");
        String databaseName = config.get("databaseName");
        String domain = config.get("domain");
        String driver = config.get("driver");
        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");
        String databaseType = config.get("databaseType");
        String out = config.get("out");
        String pojo = config.get("pojo");
        String dao = config.get("dao");
        String ser = config.get("ser");
        String saveMapperName = config.get("saveMapperName");
        String updateMapperName = config.get("updateMapperName");
        String deleteMapperName = config.get("deleteMapperName");
        String getByIdMapperName = config.get("getByIdMapperName");
        String effective = config.get("effective");
        String urlDatabaseName = DataBaseUtils.analyzeUrl(url);
        if (databaseName == null || "".equals(databaseName.trim())) {
            config.put("databaseName", urlDatabaseName);
            databaseName = urlDatabaseName;
        }
        if (databaseType == null) {
            config.put("databaseType", "mysql");
        }
        if (projectName == null && databaseName == null) {
            throw new RuntimeException("--数据库和工程名均不存在--");
        }
        if (domain == null) {
            throw new RuntimeException("--域名不存在--");
        }
        domain = StringUtils.formatDmain(domain);
        config.put("domain", domain);
        if (databaseName == null) {
            config.put("databaseName", projectName);
        }
        if (projectName == null) {
            config.put("projectName", databaseName);
        }
        if (driver == null) {
            if ("mysql".equalsIgnoreCase(databaseType.trim())) {
                config.put("driver", "com.mysql.jdbc.Driver");
            }
        }
        if (url == null) {
            if ("mysql".equalsIgnoreCase(databaseType.trim())) {
                config.put("url", "jdbc:mysql://localhost:3306/test");
            }
        }

        databaseType = databaseType.trim();
        if ("mysql".equalsIgnoreCase(databaseType)) {
            if (url != null && !url.startsWith("jdbc:mysql://")) {
                url = "jdbc:mysql://" + url;
            }
            if (username == null) {
                config.put("username", "root");
            }
            if (password == null) {
                config.put("password", "1");
            }
        }
        if (StrUtil.isBlank(out)) {
            out = PathUtils.getDesktop();
            out = StrUtil.removeSuffix(out, "/");
            config.put("out", out);
        }
        if (StrUtil.isBlank(pojo)) {
            config.put("pojo", "pojo");
        }
        if (StrUtil.isBlank(dao)) {
            config.put("dao", "dao");
        }
        if (StrUtil.isBlank(ser)) {
            config.put("ser", "false");
        }
        if (StrUtil.isBlank(saveMapperName)) {
            config.put("saveMapperName", "save${upperFirst}");
        }
        if (StrUtil.isBlank(updateMapperName)) {
            config.put("updateMapperName", "update${upperFirst}");
        }
        if (StrUtil.isBlank(deleteMapperName)) {
            config.put("deleteMapperName", "delete${upperFirst}");
        }
        if (StrUtil.isBlank(getByIdMapperName)) {
            config.put("getByIdMapperName", "get${upperFirst}ById");
        }
        if (StrUtil.isBlank(effective)) {
            config.put("effective", "1");
            config.put("invalid", "0");
        } else if("0".equals(effective)){
            config.put("effective", "0");
            config.put("invalid", "1");
        } else if("1".equals(effective)){
        	config.put("effective", "1");
        	config.put("invalid", "0");
        }
        return config;
    }

}
