package com.icexxx.icegen.initdata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.icexxx.icegen.utils.ArrayUtils;
import com.icexxx.icegen.utils.JdbcUtils;

public class GeneralController {
    public static String[][][] getData(String driver, String url, String username, String password,
            String databaseName) {

        ArrayList<String> tablesList = new ArrayList<String>();
        ArrayList<String[][]> list = new ArrayList<String[][]>();

        if (databaseName != null && !"".equals(databaseName.trim())) {
            url = url.substring(0, url.lastIndexOf("/")) + "/" + databaseName;
            url += "?useUnicode=true&characterEncoding=UTF-8";
        } else {
            databaseName = url.substring(url.lastIndexOf("/") + 1);
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rsmd = null;
        String[][][] result;
        try {
            conn = JdbcUtils.getConnection(driver, url, username, password);
            stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            rsmd = dbmd.getTables(databaseName, null, null, null);
            while (rsmd.next()) {
                String tableName = rsmd.getString(3);
                if (com.icexxx.icegen.utils.Count.FILTER != null && !"".equals(com.icexxx.icegen.utils.Count.FILTER)
                        && !tableName.startsWith(com.icexxx.icegen.utils.Count.FILTER)) {
                    continue;
                }
                String tableComment = rsmd.getString("REMARKS");
                tablesList.add(tableName);
                String tableOut = tableName;
                if (tableComment != null) {
                    tableOut += tableComment;
                }
                System.out.println(tableOut);
                String sql = "select * from " + tableName + " limit 1";
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int count = md.getColumnCount();
                ResultSet rs2 = dbmd.getColumns(null, "%", tableName, "%");
                String[][] tableStructure = new String[count + 1][5];
                tableStructure[0][0] = tableName;
                for (int i = 1; i <= count; i++) {
                    String columnName = md.getColumnName(i);
                    String columnType = md.getColumnTypeName(i);
                    String columnSize = String.valueOf(md.getColumnDisplaySize(i));
                    rs2.next();
                    String columnComment = rs2.getString("REMARKS");
                    tableStructure[i][0] = columnName;
                    tableStructure[i][1] = columnType;
                    tableStructure[i][2] = columnSize;
                    tableStructure[i][4] = columnComment;
                }
                tableStructure = ArrayUtils.sortTwoDim(tableStructure);
                list.add(tableStructure);

            }
            result = new String[tablesList.size() + 1][][];
            result[0] = new String[][] { { databaseName } };
            for (int i = 0; i < tablesList.size(); i++) {
                result[i + 1] = list.get(i);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("--从数据库获取数据发生错误--");
        } finally {
            if (rsmd != null) {
                try {
                    rsmd.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
