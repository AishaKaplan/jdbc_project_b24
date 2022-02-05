package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P01_JDBCIntro {
    String dbUrl = "jdbc:oracle:thin:@34.192.175.227:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {
        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement();

        // using with statement we will execute query
         ResultSet rs=statement.executeQuery("select * from departments where manager_id is not null ");

while(rs.next()){
    System.out.println(rs.getInt(1)+"-"+rs.getString("department_name")+"-"+rs.getInt(3)+"-"+rs.getInt(4));
}
/*
can we add more query to get data from database in one connection class?
yes; but as a tester usually we don't do it.
 */
        rs=statement.executeQuery("select * from regions");
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString("region_name"));
        }
        rs.close();
        statement.close();
        conn.close();
    }
}