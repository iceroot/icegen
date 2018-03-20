package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;

public class SpringMybatisTemplate implements Template {

    @Override
    public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
        StringBuilder sum = new StringBuilder();
        String nl = Count.NEWLINE;
        String pack = dataMap.get("domain");
        String packPath = pack.replace(".", "/");
        String projectName = dataMap.get("projectName");
        sum.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + nl);
        sum.append(
                "<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:aop=\"http://www.springframework.org/schema/aop\" xsi:schemaLocation=\""
                        + nl);
        sum.append("  http://www.springframework.org/schema/beans " + nl);
        sum.append("  http://www.springframework.org/schema/beans/spring-beans-3.2.xsd " + nl);
        sum.append("  http://www.springframework.org/schema/tx " + nl);
        sum.append("  http://www.springframework.org/schema/tx/spring-tx-3.2.xsd " + nl);
        sum.append("  http://www.springframework.org/schema/aop " + nl);
        sum.append("  http://www.springframework.org/schema/aop/spring-aop-3.2.xsd\" >" + nl);
        sum.append("" + nl);
        sum.append("    <!-- ========================================配置数据源========================================= -->"
                + nl);
        sum.append("    <!-- 配置数据源,使用的是alibaba的druid数据源 -->" + nl);
        sum.append(
                "    <bean name=\"dataSource\" class=\"com.alibaba.druid.pool.DruidDataSource\" init-method=\"init\" destroy-method=\"close\">"
                        + nl);
        sum.append("        <property name=\"url\" value=\"${jdbc.url}\" />" + nl);
        sum.append("        <property name=\"username\" value=\"${jdbc.username}\" />" + nl);
        sum.append("        <property name=\"password\" value=\"${jdbc.password}\" />" + nl);
        sum.append("        <property name=\"driverClassName\" value=\"${jdbc.driver}\" />" + nl);
        sum.append("        <!-- 初始化连接大小 -->" + nl);
        sum.append("        <property name=\"initialSize\" value=\"0\" />" + nl);
        sum.append("        <!-- 连接池最大使用连接数量 -->" + nl);
        sum.append("        <property name=\"maxActive\" value=\"20\" />" + nl);
        sum.append("        <!-- 连接池最大空闲 -->" + nl);
        sum.append("        <property name=\"maxIdle\" value=\"20\" />" + nl);
        sum.append("        <!-- 连接池最小空闲 -->" + nl);
        sum.append("        <property name=\"minIdle\" value=\"0\" />" + nl);
        sum.append("        <!-- 获取连接最大等待时间 -->" + nl);
        sum.append("        <property name=\"maxWait\" value=\"60000\" />" + nl);
        sum.append("        <!-- " + nl);
        sum.append("        <property name=\"poolPreparedStatements\" value=\"true\" /> " + nl);
        sum.append("        <property name=\"maxPoolPreparedStatementPerConnectionSize\" value=\"33\" /> " + nl);
        sum.append("        -->" + nl);
        sum.append("        <property name=\"validationQuery\" value=\"SELECT 1\" />" + nl);
        sum.append("        <property name=\"testOnBorrow\" value=\"false\" />" + nl);
        sum.append("        <property name=\"testOnReturn\" value=\"false\" />" + nl);
        sum.append("        <property name=\"testWhileIdle\" value=\"true\" />" + nl);
        sum.append("        <!-- 配置间隔多久才进行一次检测,检测需要关闭的空闲连接,单位是毫秒 -->" + nl);
        sum.append("        <property name=\"timeBetweenEvictionRunsMillis\" value=\"60000\" />" + nl);
        sum.append("        <!-- 配置一个连接在池中最小生存的时间,单位是毫秒 -->" + nl);
        sum.append("        <property name=\"minEvictableIdleTimeMillis\" value=\"25200000\" />" + nl);
        sum.append("        <!-- 打开removeAbandoned功能 -->" + nl);
        sum.append("        <property name=\"removeAbandoned\" value=\"true\" />" + nl);
        sum.append("        <!-- 1800秒,也就是30分钟 -->" + nl);
        sum.append("        <property name=\"removeAbandonedTimeout\" value=\"1800\" />" + nl);
        sum.append("        <!-- 关闭abanded连接时输出错误日志 -->" + nl);
        sum.append("        <property name=\"logAbandoned\" value=\"true\" />" + nl);
        sum.append("        <!-- 监控数据库 -->" + nl);
        sum.append("        <!-- <property name=\"filters\" value=\"stat\" /> -->" + nl);
        sum.append("        <property name=\"filters\" value=\"mergeStat\" />" + nl);
        sum.append("    </bean>" + nl);
        sum.append("    " + nl);
        sum.append("    <!-- ========================================分隔线========================================= -->"
                + nl);
        sum.append("    <!-- ========================================针对myBatis的配置项============================== -->"
                + nl);
        sum.append("    <!-- 配置sqlSessionFactory -->" + nl);
        sum.append("    <bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">" + nl);
        sum.append("        <!-- 实例化sqlSessionFactory时需要使用上述配置好的数据源以及SQL映射文件 -->" + nl);
        sum.append("        <property name=\"dataSource\" ref=\"dataSource\" />" + nl);
        sum.append("        <!-- 自动扫描mapping目录下的所有SQL映射文件-->" + nl);
        sum.append("        <property name=\"mapperLocations\" value=\"classpath:" + packPath + "/" + projectName
                + "/mapping/*.xml\" />" + nl);
        sum.append("    </bean>" + nl);
        sum.append("    <!-- 配置扫描器 -->" + nl);
        sum.append("    <bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">" + nl);
        sum.append("        <!-- 扫描包中的所有接口 -->" + nl);
        sum.append("        <property name=\"basePackage\" value=\"" + pack + "." + projectName + ".dao\" />" + nl);
        sum.append("        <property name=\"sqlSessionFactoryBeanName\" value=\"sqlSessionFactory\" />" + nl);
        sum.append("    </bean>" + nl);
        sum.append("    " + nl);
        sum.append("    <!-- ========================================分隔线========================================= -->"
                + nl);
        sum.append("    <!-- 配置Spring的事务管理器 -->" + nl);
        sum.append(
                "    <bean id=\"transactionManager\" class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">"
                        + nl);
        sum.append("        <property name=\"dataSource\" ref=\"dataSource\" />" + nl);
        sum.append("    </bean>" + nl);
        sum.append("" + nl);
        sum.append("    <!-- 注解方式配置事物 -->" + nl);
        sum.append("    <!-- <tx:annotation-driven transaction-manager=\"transactionManager\" /> -->" + nl);
        sum.append("" + nl);
        sum.append("    <!-- 拦截器方式配置事物 -->" + nl);
        sum.append("    <tx:advice id=\"transactionAdvice\" transaction-manager=\"transactionManager\">" + nl);
        sum.append("        <tx:attributes>" + nl);
        sum.append("            <tx:method name=\"add*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"append*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"insert*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"save*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"update*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"modify*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"edit*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"delete*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"remove*\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"repair\" propagation=\"REQUIRED\" />" + nl);
        sum.append("            <tx:method name=\"delAndRepair\" propagation=\"REQUIRED\" />" + nl);
        sum.append("" + nl);
        sum.append("            <tx:method name=\"get*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("            <tx:method name=\"find*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("            <tx:method name=\"load*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("            <tx:method name=\"search*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("            <tx:method name=\"datagrid*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("" + nl);
        sum.append("            <tx:method name=\"*\" propagation=\"SUPPORTS\" />" + nl);
        sum.append("        </tx:attributes>" + nl);
        sum.append("    </tx:advice>" + nl);
        sum.append("    <aop:config>" + nl);
        sum.append("        <aop:pointcut id=\"transactionPointcut\" expression=\"execution(* " + pack + "."
                + projectName + ".service..*Impl.*(..))\" />" + nl);
        sum.append(
                "        <aop:advisor pointcut-ref=\"transactionPointcut\" advice-ref=\"transactionAdvice\" />" + nl);
        sum.append("    </aop:config>" + nl);
        sum.append("" + nl);
        sum.append("    <!-- 配置druid监控spring jdbc -->" + nl);
        sum.append(
                "    <bean id=\"druid-stat-interceptor\" class=\"com.alibaba.druid.support.spring.stat.DruidStatInterceptor\">"
                        + nl);
        sum.append("    </bean>" + nl);
        sum.append(
                "    <bean id=\"druid-stat-pointcut\" class=\"org.springframework.aop.support.JdkRegexpMethodPointcut\" scope=\"prototype\">"
                        + nl);
        sum.append("        <property name=\"patterns\">" + nl);
        sum.append("            <list>" + nl);
        sum.append("                <value>" + pack + "." + projectName + ".*</value>" + nl);
        sum.append("            </list>" + nl);
        sum.append("        </property>" + nl);
        sum.append("    </bean>" + nl);
        sum.append("    <aop:config>" + nl);
        sum.append("        <aop:advisor advice-ref=\"druid-stat-interceptor\" pointcut-ref=\"druid-stat-pointcut\" />"
                + nl);
        sum.append("    </aop:config>" + nl);
        sum.append("" + nl);
        sum.append("</beans>" + nl);
        String content = sum.toString();
        return content;
    }
}
