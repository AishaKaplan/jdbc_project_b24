package com.cydeo.jdbctests.day01;

import java.sql.*;

public class TestOracleConnection {
    public static void main(String[] args) throws SQLException {

        /* How can I get this in real ?

             This is called as Connection String
             You wiill ask this info your team to use .Basically TAKE IT AND USE IT
             They will you portnumber,IP,Username,password

         */

        String dbUrl = "jdbc:oracle:thin:@34.192.175.227:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";


        // DriverManager class getConnection method is used for make connection with DB
        Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

        // Create statement from Connection to runQueries
        Statement statement = conn.createStatement();

        // using with statement we will execute query
        ResultSet rs=statement.executeQuery("select * from regions");

        // move to cursor first row
        rs.next();

        // now we are at first row w will get data

        System.out.println("rs.getString(\"region_name\") = " + rs.getString("region_name"));
        System.out.println("rs.getString(2) = " + rs.getString(2));

        System.out.println(rs.getString(2));
        System.out.println(rs.getString("region_name"));

        // close connection

        rs.close();
        statement.close();
        conn.close();



    }
}
