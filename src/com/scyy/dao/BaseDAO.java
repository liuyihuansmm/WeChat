package com.scyy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by LYH on 2016/3/2.
 */
public class BaseDAO {

    private static final  String url="jdbc:mysql://localhost:3306/WeiXinTest?user=root&password=&useUnicode=true&characterEncoding=GB2312";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getCon() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
