package DAO;

import Business.Personne;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IConducteurDAOimplement implements IConducteurDAO {
    Connection conn = ConnectionDB.getConnexion();
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    ResultSet rs = null;
    public void register(Personne p) {
        try {
            //isValidEmail(p.getEmail());  On fait appel à la fonction isValidEmail pour verifier la validité de l'email
            if (isValidEmail(p.getEmail()) && isValidPassword(p.getPassword()) && !isExistEmail(p.getEmail())) {
                String matricule = selectRandomMatricule();
                stmt = conn.prepareStatement("INSERT INTO conducteur(nom, prenom, telephone, email, password, matricule)"
                        + "VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, p.getNom());
                stmt.setString(2, p.getPrenom());
                stmt.setString(3, p.getTelephone());
                stmt.setString(4, p.getEmail().toLowerCase());
                stmt.setString(5, p.getPassword());
                stmt.setString(6, matricule );
                updateTaxiAffectationConducteur(matricule); //on met a jour de la colonne taxiAffectation = Oui
                stmt.executeUpdate();
                System.out.println("Inserted!");
                // On doit mettre a jour le status du taxi car il n'est plus dispo


            } else if (isExistEmail(p.getEmail())) {
                System.out.println("The email is already exists!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isValidEmail(String email) {
        String regexExpression = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(email);
        boolean m = matcher.find();
        return m;
    }

    public boolean isExistEmail(String email) {
        boolean flag = false;
        try {
            stmt = conn.prepareStatement("SELECT * FROM conducteur WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                flag = true;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }

    public boolean isValidPassword(String password) {
        String regexExpression = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{1,15})";
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(password);
        boolean ma = matcher.find();
        return ma;

    }

    public void login(String email, String password) {
        if (conn != null) {
            try {
                stmt = conn.prepareStatement("SELECT idConducteur, nom, password FROM conducteur WHERE email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                while (rs.next()) {
                        System.out.println("Welcome " + rs.getString("nom")
                        + ", ur password : " + rs.getString("password"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not connected");
        }
    }
    //On attribue au conducteur inscrit le matricule d'un taxi aleatoirement
    public String selectRandomMatricule() {
        String matricule = "";
        try {
            stmt2 = conn.prepareStatement("SELECT TOP 1 matricule FROM taxi WHERE affectationConducteur = ? ORDER BY NEWID()");
            stmt2.setString(1, "Non");
            rs = stmt2.executeQuery();
            while(rs.next()) {
                matricule = rs.getString("matricule");
            }
            //updateTaxiStatus();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricule;
    }
    public int getIdFromDB(Personne p) {
        int id = -1;
        try {
            stmt = conn.prepareStatement("SELECT idConducteur FROM conducteur WHERE email = ?");
            stmt.setString(1, p.getEmail());
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idConducteur");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    // La methode qui met à jour le statut du taxi apres avoir ete choisi pour une reservation
    public void updateTaxiStatus(String matricule) {
        String mat = selectRandomMatricule();
        try {
                stmt3 = conn.prepareStatement("UPDATE taxi SET status = ? WHERE matricule = ?");
                stmt3.setString(1, "Occupe");
                stmt3.setString(2, matricule );
                stmt3.executeUpdate();
                conn.commit();
                System.out.println("status changed");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void conducteurOfATaxi(String matricule) {
        try {

            stmt = conn.prepareStatement("SELECT * FROM conducteur WHERE matricule= ?");
            stmt.setString(1, matricule);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("[idConducteur: " + rs.getString("idConducteur")
                        + ", nom: " + rs.getString("nom") + ", prenom: " + rs.getString("prenom")
                        + ", telephone: " + rs.getString("telephone") + ", email: " + rs.getString("email")
                        + " ]");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // La methode getRandomConducteur affecte un conducteur  pour une reservation d'un client donne
    public List<Object> getRandomConducteur() {
        List<Object> l = new ArrayList<>();
        try {
            stmt2 = conn.prepareStatement("SELECT TOP 1 idConducteur, matricule FROM conducteur  ORDER BY NEWID()");
            rs = stmt2.executeQuery();
            while(rs.next()) {
                 l.add(rs.getInt("idConducteur"));
                 l.add(rs.getString("matricule"));
                System.out.println(" matricule: " + rs.getString("matricule"));
            }
            //updateTaxiStatus();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    //Lorsqu'un conducteur est inscrit , on lui affecte un taxi, dans ce cas nous devons change la colonne affecationConducteuur
    public void updateTaxiAffectationConducteur(String matricule) {
        String mat = selectRandomMatricule();
        try {
            stmt3 = conn.prepareStatement("UPDATE taxi SET affectationConducteur = ? WHERE matricule = ?");
            stmt3.setString(1, "Oui");
            stmt3.setString(2, matricule );
            stmt3.executeUpdate();
            conn.commit();
            System.out.println("affectationConducteur updated");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
