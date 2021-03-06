package com.dw.demo.jdbc;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.util.Map;

/**
 * Created by finup on 2018/6/21.
 */
public class HiveJDBC {


    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void jdbc() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //replace "hive" here with the name of the user the queries should run as
        Connection con = DriverManager.getConnection("jdbc:hive2://127.0.0.1:10000/test", "finup", "");
        Statement stmt = con.createStatement();



        // show tables
        String sql = "show tables in test";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }

        //refresh table
        //stmt.executeUpdate("refresh table  employee");

        // show tables
        String sql2 = "select * from employee limit 10";
        System.out.println("Running: " + sql2);
        ResultSet res2 = stmt.executeQuery(sql2);

//        ResultSetMetaData rsmd = res2.getMetaData();
//        while(rsmd.){
//
//        }

        int idx = 0;
        while (res2.next()) {
            System.out.println(String.format("row[%d]=============", idx++));
            String id = res2.getString("id");
            String name = res2.getString("name");
            Integer age = res2.getInt("age");
            String birthday = res2.getString("birthday");
            String nation = res2.getString("nation");
            Double salary = res2.getDouble("salary");

            //array
            String loves = res2.getString("loves");
            //String lovesJson = JSON.toJSONString(loves);

            String info = res2.getString("info");
            String familyJson  = res2.getString("family");
            Map<String,String>  family = JSON.parseObject(familyJson,Map.class);

            System.out.println(String.format("id=%s,name=%s,age=%d,birthday=%s", id, name, age, birthday));
            System.out.println(String.format("nation=%s,salary=%f", nation, salary));
            System.out.println(String.format("loves=%s", loves));
            System.out.println(String.format("info=%s", info));
            System.out.println(String.format("familyJson=%s", familyJson));
            System.out.println("family==>" + family);
        }
    }

    public static void main(String[] args) throws Exception {

        jdbc();

    }

}
