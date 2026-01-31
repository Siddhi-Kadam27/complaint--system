package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            // MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Use your correct database name here
            String url = "jdbc:mysql://localhost:3306/complaint_db?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "root@123";   // change if your MySQL password is different

            con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
