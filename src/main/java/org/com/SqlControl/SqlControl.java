package org.com.SqlControl;

import java.sql.*;

public class SqlControl {
    private static Connection getconnection() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动(8.0更换了驱动目录)
        Class.forName("com.mysql.cj.jdbc.Driver");//连接成功！！！（虽然不知道为什么过了一晚上就好了）
        return DriverManager.getConnection("jdbc:mysql://localhost:3306","root","yin010826");
    }
    public static void CreateDatabase(String databaseName) throws SQLException, ClassNotFoundException {
        Connection conn = getconnection();
        Statement statement = conn.createStatement();
        int res = 0;
        try {
            res = statement.executeUpdate("create database " + databaseName);
            System.out.println(res);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void Use(String databaseName) throws SQLException, ClassNotFoundException {
        Connection conn = getconnection();
        Statement statement = conn.createStatement();
        try {
            statement.executeUpdate("user " + databaseName);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void Insert_Into(String tableName , String value){

    }
}
