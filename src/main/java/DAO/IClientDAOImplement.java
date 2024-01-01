package DAO;
import Business.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Connection;

public class IClientDAOImplement implements IClientDAO {
    Connection conn = ConnectionDB.getConnexion();
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    //la methode retourne une enumeration ErreurInscription
    public ErreurInscription register(Personne p) {
        try {
            // si les champs ne sont pas vide
            /*if (!p.getNom().isEmpty() || !p.getPrenom().isEmpty()|| !p.getEmail().isEmpty() || !p.getTelephone().isEmpty() || !p.getPassword().isEmpty()) {
                //isValidEmail(p.getEmail());  On fait appel à la fonction isValidEmail pour verifier la validité de l'email
                if (isValidEmail(p.getEmail()) && isValidPassword(p.getPassword())) {
                    if (!isExistEmail(p.getEmail())) {
                        stmt = conn.prepareStatement("INSERT INTO client(nom, prenom, telephone, email, password)"
                                + "VALUES (?, ?, ?, ?, ?)");
                        stmt.setString(1, p.getNom());
                        stmt.setString(2, p.getPrenom());
                        stmt.setString(3, p.getTelephone());
                        stmt.setString(4, p.getEmail().toLowerCase());
                        stmt.setString(5, p.getPassword());
                        int n = stmt.executeUpdate();
                        // I added these lines of code to test and verify if the row was inserted successfully
                        if (n > 0) {
                            System.out.println("Client Inserted!");

                        }
                        else {
                            System.out.println("Client not Inserted!");
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
                }
            }
            else {
                System.out.println("les champs sont vide");
                return ErreurInscription.CHAMP_VIDE;

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
            // Aucune erreur, procéder à l'inscription
            stmt = conn.prepareStatement("INSERT INTO client(nom, prenom, telephone, email, password)"
                    + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getPrenom());
            stmt.setString(3, p.getTelephone());
            stmt.setString(4, p.getEmail().toLowerCase());
            stmt.setString(5, p.getPassword());
            int n = stmt.executeUpdate();
            // I added these lines of code to test and verify if the row was inserted successfully
            if (n > 0) {
                System.out.println("Client Inserted!");

            }
            else {
                System.out.println("Client not Inserted!");
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
            stmt = conn.prepareStatement("SELECT * FROM client WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                flag = true;
                break;
            }
            rs.close();


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
                stmt = conn.prepareStatement("SELECT idClient, password, nom FROM client WHERE email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    if (rs.getString("password").equals(password)) {
                        System.out.println("Welcome " + rs.getString("nom"));
                        return true;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not connected");
        }
        return false;
    }
    public void updateNom(Personne p, String nom) {
        if (conn != null && isExistEmail(p.getEmail())) {
            try {
                stmt = conn.prepareStatement("UPDATE client SET nom = ? WHERE email = ?");
                stmt.setString(1, nom);
                stmt.setString(2, p.getEmail());
                int r = stmt.executeUpdate();
                if (r > 1) {
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
                stmt = conn.prepareStatement("UPDATE client SET prenom = ? WHERE email = ?");
                stmt.setString(1, prenom);
                stmt.setString(2, p.getEmail());
                stmt.executeUpdate();
                System.out.println("prenom updated");

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
                stmt = conn.prepareStatement("UPDATE client SET telephone = ? WHERE email = ?");
                stmt.setString(1, telephone);
                stmt.setString(2, p.getEmail());
                stmt.executeUpdate();
                System.out.println("telephone updated");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("user not connected");
        }
    }

    public int getIdFromDB(String email) {
        int id = -1;
        try {
            stmt = conn.prepareStatement("SELECT idClient FROM client WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idClient");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }
    public Client getPersonneById(int clientId) {
        Client client = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM client WHERE idClient = ?");
            stmt.setInt(1, clientId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Construisez l'objet Client à partir des résultats de la requête
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String password = rs.getString("password");

                client = new Client(nom, prenom, telephone, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void logout() {
        System.exit(0);
        conn = null;
    }
}






