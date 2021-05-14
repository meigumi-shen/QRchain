package org.com.test;

import java.sql.*;
//import

public class testSQL {
    private Connection getconnection() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//连接成功！！！（虽然不知道为什么过了一晚上就好了）
        //Class.forName("mysql-connector-java-8.0.25.jar");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306","root","yin010826");
    }
    public void showDatabases() throws SQLException, ClassNotFoundException {
        Connection conn = getconnection();//和数据库建立连接
        Statement statement =conn.createStatement();// 用statement.executeQuery方法向数据库发送指令，并获得resultset返
        ResultSet resultSet = statement.executeQuery("show databases");
        //System.out.println("%20s",resultSet.getString("databases"));//println不能用于格式转换
        while (resultSet.next())
        {
            System.out.printf("%20s \n", resultSet.getString("Database"));
        }
        resultSet.close();
        statement.close();
        conn.close();
    }
}
