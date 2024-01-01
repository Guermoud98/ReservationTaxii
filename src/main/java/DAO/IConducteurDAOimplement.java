package DAO;

import Business.Client;
import Business.Conducteur;
import Business.Personne;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IConducteurDAOImplement implements IConducteurDAO {
    Connection conn = ConnectionDB.getConnexion();
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    ResultSet rs = null;
    public ErreurInscription register(Personne p) {
        try {
          /*  //isValidEmail(p.getEmail());  On fait appel à la fonction isValidEmail pour verifier la validité de l'email
            if (isValidEmail(p.getEmail()) && isValidPassword(p.getPassword())) {
                if (!isExistEmail(p.getEmail())) {
                    ITaxiDAO t = new ITaxiDAOImplementation();
                    String matricule = t.selectRandomMatricule();
                    stmt = conn.prepareStatement("INSERT INTO conducteur(nom, prenom, telephone, email, password, matricule)"
                            + "VALUES (?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, p.getNom());
                    stmt.setString(2, p.getPrenom());
                    stmt.setString(3, p.getTelephone());
                    stmt.setString(4, p.getEmail().toLowerCase());
                    stmt.setString(5, p.getPassword());
                    stmt.setString(6, matricule);
                    t.updateTaxiAffectationConducteur(matricule); //on met a jour de la colonne taxiAffectation = Oui
                    int n = stmt.executeUpdate();
                    if (n > 0) {
                        System.out.println("Driver Inserted!");
                    }
                    else {
                        System.out.println("Driver not Inserted!");
                    }

                } else {
                    System.out.println("Email already exist");
                    return ErreurInscription.EMAIL_EXIST; //On fait appel a l'enumeration
                }
            } else {
                if (!isValidEmail(p.getEmail())) {
                    System.out.println("Invalid Email");
                    return ErreurInscription.EMAIL_INVALIDE;
                }
                if (!isValidPassword(p.getPassword())) {
                    System.out.println("Invalid password");
                    return ErreurInscription.PASSWORD_INVALID;
                }
            } */
            if (p.getNom().isEmpty() || p.getPrenom().isEmpty()|| p.getEmail().isEmpty() || p.getTelephone().isEmpty() || p.getPassword().isEmpty()) {
                return ErreurInscription.CHAMP_VIDE;
            }
            if (!isValidEmail(p.getEmail())) {
                return ErreurInscription.EMAIL_INVALIDE;
            }

            // Vérification si le mot de passe est valide
            if (!isValidPassword(p.getPassword())) {
                return ErreurInscription.PASSWORD_INVALID;
            }

            // Vérification si l'email existe déjà
            if (isExistEmail(p.getEmail())) {
                return ErreurInscription.EMAIL_EXIST;
            }
            ITaxiDAO t = new ITaxiDAOImplementation();
            String matricule = t.selectRandomMatricule();
            stmt = conn.prepareStatement("INSERT INTO conducteur(nom, prenom, telephone, email, password, matricule)"
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getPrenom());
            stmt.setString(3, p.getTelephone());
            stmt.setString(4, p.getEmail().toLowerCase());
            stmt.setString(5, p.getPassword());
            stmt.setString(6, matricule);
            t.updateTaxiAffectationConducteur(matricule); //on met a jour de la colonne taxiAffectation = Oui
            int n = stmt.executeUpdate();
            if (n > 0) {
                System.out.println("Conducteur Inserted!");

            }
            else {
                System.out.println("Conducteur not Inserted!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ErreurInscription.AUCUNE_ERREUR;

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
                break;
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

    public boolean login(String email, String password) {
        if (conn != null) {
            try {
                stmt = conn.prepareStatement("SELECT idConducteur, nom, password FROM conducteur WHERE email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                while (rs.next()) {
                        System.out.println("Welcome " + rs.getString("nom")
                        + ", ur password : " + rs.getString("password"));
                        return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not connected");
        }
        return false;
    }

    public int getIdFromDB(String email) {
        int id = -1;
        try {
            stmt = conn.prepareStatement("SELECT idConducteur FROM conducteur WHERE email = ?");
            stmt.setString(1, email);
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

            stmt2 = conn.prepareStatement("SELECT TOP 1 c.idConducteur, c.matricule  FROM conducteur c JOIN taxi t  ON c.matricule=t.matricule WHERE t.status=? ORDER BY NEWID()");
            stmt2.setString(1,"Disponible");
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
    public Conducteur getPersonneById(int conducteurId) {
        Conducteur conducteur = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM conducteur WHERE idConducteur = ?");
            stmt.setInt(1, conducteurId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Construisez l'objet Client à partir des résultats de la requête
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String password = rs.getString("password");

                conducteur = new Conducteur(nom, prenom, telephone, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conducteur;
    }
    //pour extraire le matricule du taxi d'un conducteur
    public String getMatriculeConducteur(int id) {
        String  matricule = null;
        try {
            stmt = conn.prepareStatement("SELECT matricule FROM conducteur WHERE idConducteur = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                matricule = rs.getString("matricule");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matricule;
    }
    //update
    public void updateNom(Personne p, String nom) {
        if (conn != null && isExistEmail(p.getEmail())) {
            try {
                stmt = conn.prepareStatement("UPDATE conducteur SET nom = ? WHERE email = ?");
                stmt.setString(1, nom);
                stmt.setString(2, p.getEmail());
                int r = stmt.executeUpdate();
                if (r > 0) {
                    System.out.println("nom updated");
                }
                else {
                    System.out.println("nom not updated");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("user not connected");
        }

    }
    public void updatePrenom(Personne p, String prenom) {
        if (conn != null && isExistEmail(p.getEmail())) {
            try {
                stmt = conn.prepareStatement("UPDATE conducteur SET prenom = ? WHERE email = ?");
                stmt.setString(1, prenom);
                stmt.setString(2, p.getEmail());
                int r = stmt.executeUpdate();
                if (r > 0) {
                    System.out.println("prenom updated");
                }
                else {
                    System.out.println("prenom not updated");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("user not connected");
        }
    }
    public void updateTelephone(Personne p, String telephone) {
        if (conn != null && isExistEmail(p.getEmail())) {
            try {
                stmt = conn.prepareStatement("UPDATE conducteur SET telephone = ? WHERE email = ?");
                stmt.setString(1, telephone);
                stmt.setString(2, p.getEmail());
                int r = stmt.executeUpdate();
                if (r > 0) {
                    System.out.println("telephone updated");
                }
                else {
                    System.out.println("telephone not updated");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("user not connected");
        }
    }


}
