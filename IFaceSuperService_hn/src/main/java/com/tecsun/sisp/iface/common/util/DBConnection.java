package com.tecsun.sisp.iface.common.util;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class DBConnection {

    private static Connection con;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public static final void initConn() throws SQLException {
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        return rs;
    }

    public static int executeUpdateMobile(SecQueryBean bean) throws SQLException {
        return 0;
    }

    public static int executeUpdate(String sql) throws SQLException {
        return 0;
    }

    public static Long getSequence() throws SQLException {
        Long seq = 0l;
        ResultSet resultSet=null;
        try {
            resultSet = executeQuery("select t_bank_seq.nextval from dual");
            if (resultSet.next()) {
                seq = resultSet.getLong("nextval");
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            closeConn();
        }
        return seq;
    }

    public static void closeConn() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
