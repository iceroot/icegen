package com.icexxx.icegen.template;

import java.util.HashMap;

import com.icexxx.icegen.codemanager.Count;
import com.icexxx.icegen.codemanager.Data;
import com.icexxx.icegen.codemanager.Template;
import com.icexxx.icegen.format.FieldFormat;
import com.icexxx.icegen.utils.ArrayUtils;
import com.icexxx.icegen.utils.NameUtil;
import com.icexxx.icegen.utils.PojoMapUtil;

import cn.hutool.core.util.StrUtil;

public class MyBatisMapperTemplate implements Template {

	@Override
	public String getCode(HashMap<String, String> dataMap, Data data, String packageName, String className) {
		StringBuilder sum = new StringBuilder();
		String nl = Count.NEWLINE;
		String Stu = className;
		String st = StrUtil.lowerFirst(Stu);
		String pack = dataMap.get("domain");
		String projectName = dataMap.get("projectName");
		String[][] table = data.getTable(className);
		String[][] jdbcTable = ((com.icexxx.icegen.data.AllData) data).getJdbcTable(className);
		jdbcTable = FieldFormat.columnJdbcTypeCast(jdbcTable);
		String tableName = jdbcTable[0][0];
		String dao = dataMap.get("dao");
		String pojo = dataMap.get("pojo");
		String saveMapperName = dataMap.get("saveMapperName");
		String updateMapperName = dataMap.get("updateMapperName");
		String deleteMapperName = dataMap.get("deleteMapperName");
		String getByIdMapperName = dataMap.get("getByIdMapperName");
		String delete = dataMap.get("delete");
		String effective = dataMap.get("effective");
		String invalid = dataMap.get("invalid");
		saveMapperName = NameUtil.replace(saveMapperName, Stu, st);
		updateMapperName = NameUtil.replace(updateMapperName, Stu, st);
		deleteMapperName = NameUtil.replace(deleteMapperName, Stu, st);
		getByIdMapperName = NameUtil.replace(getByIdMapperName, Stu, st);
		String idType = ArrayUtils.idType(table);
		String mapperName = pack + "." + projectName + "." + dao + "." + Stu + "Mapper";
		String pojoName = pack + "." + projectName + "." + pojo + "." + Stu + "";
		String keyPojo = Stu + "{pojoName}";
		pojoName = PojoMapUtil.get(keyPojo, pojoName);
		sum.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + nl);
		sum.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"
						+ nl);
		sum.append("<mapper namespace=\"" + mapperName + "\">" + nl);
		sum.append("    <resultMap id=\"BaseResultMap\" type=\"" + pojoName + "\">" + nl);
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			String columnName = jdbcTable[i][0];
			String columnType = jdbcTable[i][1].toUpperCase();
			String tag = "result";
			if ("id".equalsIgnoreCase(fieldName)) {
				tag = "id";
			}
			sum.append("        <" + tag + " column=\"" + columnName + "\" jdbcType=\"" + columnType + "\" property=\""
					+ fieldName + "\" />" + nl);
		}
		sum.append("    </resultMap>" + nl);
		sum.append("    <sql id=\"Base_Column_List\">" + nl);
		sum.append("" + genStrJoin(jdbcTable) + " " + nl);
		sum.append("    </sql>" + nl);
		sum.append("    <select id=\"" + getByIdMapperName
				+ "\" parameterType=\"java.lang.Integer\" resultMap=\"BaseResultMap\">" + nl);
		sum.append("        select " + nl);
		sum.append("        <include refid=\"Base_Column_List\" />" + nl);
		sum.append("        from " + tableName + "" + nl);
		sum.append("        where id = #{id,jdbcType=INTEGER}" + nl);
		sum.append("    </select>" + nl);
		sum.append("    <delete id=\"" + deleteMapperName + "\" parameterType=\"java.lang.Integer\">" + nl);
		sum.append("        delete from " + tableName + "" + nl);
		sum.append("        where id = #{id,jdbcType=INTEGER}" + nl);
		sum.append("    </delete>" + nl);
		sum.append("    <insert id=\"" + saveMapperName + "\" parameterType=\"" + pojoName + "\">" + nl);
		sum.append("        insert into " + tableName + " (" + nl);
		sum.append("" + genStrJoinOther(jdbcTable) + nl);
		sum.append("        ) values (" + nl);
		sum.append(genStrJoinType(table, jdbcTable));
		sum.append("        )" + nl);
		sum.append("    </insert>" + nl);
		sum.append("    <insert id=\"insertSelective\" parameterType=\"" + pojoName + "\">" + nl);
		sum.append("        insert into " + tableName + "" + nl);
		sum.append("        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" + nl);
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			String columnName = jdbcTable[i][0];
			sum.append("            <if test=\"" + fieldName + " != null\">" + nl);
			sum.append("                " + columnName + "," + nl);
			sum.append("            </if>" + nl);
		}
		sum.append("        </trim>" + nl);
		sum.append("        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" + nl);
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			// String columnName = jdbcTable[i][0];
			String columnType = jdbcTable[i][1].toUpperCase();
			sum.append("            <if test=\"" + fieldName + " != null\">" + nl);
			sum.append("                #{" + fieldName + ",jdbcType=" + columnType + "}," + nl);
			sum.append("            </if>" + nl);
		}
		sum.append("        </trim>" + nl);
		sum.append("    </insert>" + nl);
		sum.append("    <update id=\"" + updateMapperName + "\" parameterType=\"" + pojoName + "\">" + nl);
		sum.append("        update " + tableName + "" + nl);
		sum.append("        <set>" + nl);
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			String columnName = jdbcTable[i][0];
			String columnType = jdbcTable[i][1].toUpperCase();
			sum.append("            <if test=\"" + fieldName + " != null\">" + nl);
			sum.append("                " + columnName + " = #{" + fieldName + ",jdbcType=" + columnType + "}," + nl);
			sum.append("            </if>" + nl);
		}
		sum.append("        </set>" + nl);
		sum.append("        where id = #{id,jdbcType=INTEGER}" + nl);
		sum.append("    </update>" + nl);
		sum.append("    <update id=\"updateByPrimaryKey\" parameterType=\"" + pojoName + "\">" + nl);
		sum.append("        update " + tableName + "" + nl);
		sum.append("        set ");
		int index = 0;
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			String columnName = jdbcTable[i][0];
			if ("id".equalsIgnoreCase(fieldName)) {
				continue;
			}
			if (index++ != 0) {
				sum.append("            ");
			}
			sum.append(columnName + " = #{" + fieldName + ",jdbcType=VARCHAR}");
			if (i != table.length - 1) {
				sum.append(",");
			}
			sum.append(nl);
		}
		sum.append("        where id = #{id,jdbcType=INTEGER}" + nl);
		sum.append("    </update>" + nl);
		sum.append("    <select id=\"findAll\" resultMap=\"BaseResultMap\">" + nl);
		sum.append("        select " + nl);
		sum.append("        <include refid=\"Base_Column_List\"/>" + nl);
		if(ArrayUtils.containInArray(jdbcTable, delete)) {
			sum.append("        from " + tableName + " " + nl);
			sum.append("        where 1 = 1 and " + delete + " = " + effective + " " + nl);
		} else {
			sum.append("        from " + tableName + " " + nl);
		}
		sum.append("    </select>" + nl);
		sum.append("    <delete id=\"deleteBatch\" parameterType=\"" + idType + "\">" + nl);
		if(ArrayUtils.containInArray(jdbcTable, delete)) {
			sum.append("        update " + tableName + " set is_del = " + invalid + " where id in " + nl);
		}else {
			sum.append("        delete from " + tableName + " where id in " + nl);
		}
		String ids = "ids";
		sum.append("        <foreach item=\"id_cursor\" collection=\"" + ids
				+ "\" open=\"(\" close=\")\" separator=\",\">" + nl);
		sum.append("            #{id_cursor}" + nl);
		sum.append("        </foreach>" + nl);
		sum.append("    </delete>" + nl);
		sum.append("</mapper>" + nl);
		String content = sum.toString();
		return content;
	}

	private String genStrJoin(String[][] jdbcTable) {
		String nl = Count.NEWLINE;
		StringBuilder sum = new StringBuilder();
		for (int i = 1; i < jdbcTable.length; i++) {
			if (i % 8 == 1) {
				sum.append("        ");
			}
			sum.append(jdbcTable[i][0]);
			if (i != jdbcTable.length - 1) {
				sum.append(", ");
			}
			if (i % 8 == 0) {
				sum.append(nl);
			}
		}
		return sum.toString();
	}

	private String genStrJoinOther(String[][] jdbcTable) {
		String nl = Count.NEWLINE;
		StringBuilder sum = new StringBuilder();
		int index = 0;
		for (int i = 1; i < jdbcTable.length; i++) {
			String columnName = jdbcTable[i][0];
			if (ArrayUtils.isNumIdColumn(jdbcTable, i)) {
				continue;
			}
			if (index++ % 8 == 0) {
				sum.append("            ");
			}
			sum.append(columnName);
			if (i != jdbcTable.length - 1) {
				sum.append(", ");
			}
			if (index % 8 == 0) {
				sum.append(nl);
			}
		}
		return sum.toString();
	}

	private Object genStrJoinType(String[][] table, String[][] jdbcTable) {
		String nl = Count.NEWLINE;
		StringBuilder sum = new StringBuilder();
		int index = 0;
		for (int i = 1; i < table.length; i++) {
			String fieldName = table[i][0];
			String columnType = jdbcTable[i][1].toUpperCase();
			if (!ArrayUtils.isNumIdColumn(jdbcTable, i)) {
				if (index % 4 == 0) {
					sum.append("        ");
					sum.append("    ");
				}
				sum.append("#{");
				sum.append(fieldName);
				sum.append(",jdbcType=");
				sum.append(columnType);
				sum.append("}");
				if (i != jdbcTable.length - 1) {
					sum.append(", ");
				}
				if (index++ % 4 == 3) {
					sum.append(nl);
				}
			}
		}
		index--;
		if (index % 4 != 3) {
			sum.append(nl);
		}
		return sum.toString();
	}
}
