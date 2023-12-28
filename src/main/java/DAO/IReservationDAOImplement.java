package DAO;

import Business.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class IReservationDAOImplement implements IReservationDAO{
    Connection conn = ConnectionDB.getConnexion();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    IClientDAO client = new IClientDAOImplement();
    IConducteurDAO conducteur = new IConducteurDAOimplement();
    ITaxiDAO taxi = new ITaxiDAOImplementation();
    public void getAllReservations() {
        try {
            stmt = conn.prepareStatement("SELECT * FROM reservation");
            rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("[idReservation: " +rs.getInt("idReservation")
                + ", lieuSource: " + rs.getString("lieuSource") + ", lieuDestination; " +
                        rs.getString("lieuDestination") + ", typePaiement: " +
                        rs.getString("typePaiement") + ", tarif" + rs.getFloat("tarif")
                + ", date: " + rs.getDate("date") + ", heure: " + rs.getTime("heure")
                + ", idClient: " + rs.getInt("idClient") + ", idConducteur: " + rs.getInt("idConducteur")
                 + ", matricule du taxi: " + rs.getString("matricule") + "]");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insertReservation(Reservation r) {

        int idClient = client.getIdFromDB(r.getClient()); // l'argument est un client

        List<Object> l = conducteur.getRandomConducteur(); // retourne une listequi contient l'id d'un conducteur affecté à une reservation + le matricule de son taxi

        try {
            stmt = conn.prepareStatement("INSERT INTO reservation(lieuSource, lieuDestination, typePaiement, tarif, date, heure, idClient, idConducteur, matricule) VALUES (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, r.getLieuSource());
            stmt.setString(2, r.getLieuDestination());
            stmt.setString(3, r.getTypePaiement());
            stmt.setFloat(4, r.getTarif());
            stmt.setDate(5, java.sql.Date.valueOf(r.getD()));
            stmt.setTime(6, java.sql.Time.valueOf(r.getHeure()));
            stmt.setInt(7, idClient);
            stmt.setInt(8, (Integer) l.get(0)); //the cast is mandatory because the list contains objects
            stmt.setString(9, (String) l.get(1));
            taxi.updateTaxiStatus((String) l.get(1)); //pour faire un mise a jour du status du taxi choisi pour cette reservation
            int n = stmt.executeUpdate();
            if (n > 0) {
                System.out.println("reservation inserted ");
            }
            else {
                System.out.println("reservation not inserted");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
