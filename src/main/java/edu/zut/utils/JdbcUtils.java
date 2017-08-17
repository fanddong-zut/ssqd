package edu.zut.utils;

import edu.zut.pojo.Period;

import java.sql.*;
import java.util.List;

public class JdbcUtils {

    private static String src = "jdbc:mysql://localhost:3306/ssqd?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "Zzti166411";

    public JdbcUtils() {
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(src, user, password);
    }

    public static boolean insertByPreparedStatement(String sql, List params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
        if (params != null && params.size()>0) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException exx) {
                        exx.printStackTrace();
                    }
                }
            }
        }
    }
}
