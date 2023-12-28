package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ITaxiDAOImplementation implements ITaxiDAO {
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

    //Lorsqu'un conducteur est inscrit , on lui affecte un taxi, dans ce cas nous devons change la colonne affecationConducteuur
    public void updateTaxiAffectationConducteur(String matricule) {
        //String mat = selectRandomMatricule();
        try {
            stmt = conn.prepareStatement("UPDATE taxi SET affectationConducteur = ? WHERE matricule = ?");
            stmt.setString(1, "Oui");
            stmt.setString(2, matricule);
            stmt.executeUpdate();
            conn.commit();
            System.out.println("affectationConducteur updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // On selectionne aleatoirement un matricule pour l'affecter au conducteur inscrit a condition de choisir les taxis qui ont l'affecttaionConducteur= Non
    public String selectRandomMatricule() {
        String matricule = "";
        try {
            stmt = conn.prepareStatement("SELECT TOP 1 matricule FROM taxi WHERE affectationConducteur = ? ORDER BY NEWID()");
            stmt.setString(1, "Non");
            rs = stmt.executeQuery();
            while (rs.next()) {
                matricule = rs.getString("matricule");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricule;
    }

    // La methode qui met à jour le statut du taxi apres avoir ete choisi pour une reservation
    public void updateTaxiStatus(String matricule) {
        try {
            stmt = conn.prepareStatement("UPDATE taxi SET status = ? WHERE matricule = ?");
            stmt.setString(1, "Occupe");
            stmt.setString(2, matricule );
            int n = stmt.executeUpdate();
            if (n > 0) {
                System.out.println("status changed");
            }
            else {
                System.out.println("status not Inserted!");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}