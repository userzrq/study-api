package com.userzrq.reflection.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RawJDBCDemo {

    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //获取与数据库连接的对象-Connetcion
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java3y", "root", "root");

            //获取执行sql语句的statement对象
            Statement statement = connection.createStatement();

            //执行sql语句,拿到结果集
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
