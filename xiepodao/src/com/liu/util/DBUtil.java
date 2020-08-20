package com.liu.util;

import java.sql.*;

public class DBUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/xiepodao_car?useUnicode=true&characterEncoding=utf-8";
    private static final String USER_NAME = "root";
    private static final String PWD = "321";

    static {
        try {
            //加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConn(){
        Connection connection = null;
        try {
             connection = DriverManager.getConnection(URL, USER_NAME, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConn(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps){
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRS(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
