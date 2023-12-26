package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ITaxiDAOImplementation implements ITaxiDAO{
    Connection conn = ConnectionDB.getConnexion();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    public void getAllTaxis() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM taxi");
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("[matricule: " + rs.getString("matricule") + ", modele: "
                + rs.getString("modele") + ", status: " + rs.getString("status") + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
