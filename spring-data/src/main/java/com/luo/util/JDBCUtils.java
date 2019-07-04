package com.luo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc 原始操作
 */
public class JDBCUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
      /*  String url = "jdbc:mysql:///spring-data?serverTimezone=UTC";
        String user = "root";
        String password = "123";
        String driverClass = "com.mysql.jdbc.Driver";*/
        //Class.forName(driverClass);
        InputStream resource = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties p = new Properties();
        p.load(resource);
        String url = p.getProperty("jdbc.url");
        String user = p.getProperty("jdbc.user");
        String password = p.getProperty("jdbc.password");
        String driverClass = p.getProperty("jdbc.driverClass");

        Class.forName(driverClass);
        //from java.sql
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
