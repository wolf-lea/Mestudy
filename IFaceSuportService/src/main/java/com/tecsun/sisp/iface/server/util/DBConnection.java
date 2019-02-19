package com.tecsun.sisp.iface.server.util;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class DBConnection {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    public static final void initConn() throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        DataSource dataSource = ctx.getBean("netportalDataSource", DataSource.class);
        con = dataSource.getConnection();
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        return rs;
    }

    public static int executeUpdateMobile(SecQueryBean bean) throws SQLException {
        String sql="update t_az01b set mobile=? where cernum=? and name=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, bean.getNewPhoneNo());
        stmt.setString(2,bean.getId());
        stmt.setString(3,bean.getName());
        int count = stmt.executeUpdate();
        stmt.close();
        return count;
    }

    public static int executeUpdate(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        int count = stmt.executeUpdate(sql);
        stmt.close();
        return count;
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
