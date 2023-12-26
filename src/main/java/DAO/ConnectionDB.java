package DAO;

import java.sql.*;


public class ConnectionDB {
    private static Connection conn = null;
    private ConnectionDB(){}
    public static Connection getConnexion() {
        if (conn == null) {
            String connectionUrl = "jdbc:sqlserver://Maria-GM:1433;" + "databaseName=reservationTaxi;" +
                    "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            try {
                conn = DriverManager.getConnection(connectionUrl);
                System.out.println("Connected");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return conn;
    }
}

