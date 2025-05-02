package com.tads.pw.trabalhodepw.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class dbConnection {



        private static final String USER = "postgres";
        private static final String PASSWORD = "postgres";
        private static final String URL_DB = "jdbc:postgresql://localhost:5432/teste";

        public static Connection getConnection() {
            Connection conn = null; // a default null connection
            try {
                conn = DriverManager.getConnection(dbConnection.URL_DB, dbConnection.USER, dbConnection.PASSWORD); // server password
                if (conn != null) {
                    System.out.println("Connected to database #1");
                }
            } catch (SQLException e) {
                System.out.println("Error when connecting...: " + e); //e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            return conn;
        }

}
