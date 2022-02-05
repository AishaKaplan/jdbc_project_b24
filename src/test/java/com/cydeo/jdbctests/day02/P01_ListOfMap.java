package com.cydeo.jdbctests.day02;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P01_ListOfMap {
    String dbUrl = "jdbc:oracle:thin:@34.192.175.227:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() {


        Map<String,Object> rowMap1=new HashMap<>();
        rowMap1.put("first_name", "Steven");
        rowMap1.put("last_name", "King");
        rowMap1.put("salary", 24000);

        System.out.println("rowMap1 = " + rowMap1);

        Map<String,Object> rowMap2=new HashMap<>();
        rowMap2.put("first_name", "Neena");
        rowMap2.put("last_name", "Kochhar");
        rowMap2.put("salary", 17000);

        System.out.println("rowMap2 = " + rowMap2);

        /**
         *
         *
         * it will keep continue until all the way down to table
         *
         */

        List<Map<String,Object>> dataList=new ArrayList<>();
        dataList.add(rowMap1);
        dataList.add(rowMap2);

        // Give me lastname of Steven
        System.out.println("dataList.get(0).get(\"last_name\") = " + dataList.get(0).get("last_name"));


    }


    @Test
    public void task2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select FIRST_NAME,LAST_NAME,SALARY  from EMPLOYEES where ROWNUM<6");
        ResultSetMetaData rsmd = rs.getMetaData();

        //move to first row
        rs.next();

        Map<String,Object> rowMap1=new HashMap<>();
        rowMap1.put(rsmd.getColumnName(1), rs.getString(1));
        rowMap1.put(rsmd.getColumnName(2), rs.getString(2));
        rowMap1.put(rsmd.getColumnName(3), rs.getString(3));

        System.out.println("rowMap1 = " + rowMap1);

        //move to second row
        rs.next();

        Map<String,Object> rowMap2=new HashMap<>();
        rowMap2.put(rsmd.getColumnName(1), rs.getString(1));
        rowMap2.put(rsmd.getColumnName(2), rs.getString(2));
        rowMap2.put(rsmd.getColumnName(3), rs.getString(3));

        System.out.println("rowMap2 = " + rowMap2);

        /**
         *
         *
         * it will keep continue until all the way down to table
         *
         */

        List<Map<String,Object>> dataList=new ArrayList<>();
        dataList.add(rowMap1);
        dataList.add(rowMap2);

        // Give me lastname of Steven
        System.out.println(dataList.get(0).get(rsmd.getColumnName(2)));
        rs.close();
        statement.close();
        connection.close();
    }

    @Test
    public void task3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select *  from employees where rownum<6 ");
        ResultSetMetaData rsmd = rs.getMetaData();


        // get columCount
        int columnCount = rsmd.getColumnCount();

        // to store all rows I need List of Map
        List<Map<String,Object>> dataList=new ArrayList<>();

        //iterate each row dynamically
        while(rs.next()){

            Map<String,Object> rowMap=new HashMap<>();

            // to fill this map I need some logic
            //move left to right

            for (int i = 1; i <= columnCount; i++) {   // it will run $columnCount times

                rowMap.put(rsmd.getColumnName(i), rs.getString(i));
            }

            //add ready map into List of map
            dataList.add(rowMap);

        }
        System.out.println("======= PRINT OUT EACH ROW MAP ======== ");

        for (Map<String, Object> eachRowMap : dataList) {

            System.out.println(eachRowMap);

        }

        rs.close();
        statement.close();
        connection.close();
    }





}