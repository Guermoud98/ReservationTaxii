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

    public void register(Personne p) {
        try {
            //isValidEmail(p.getEmail());  On fait appel à la fonction isValidEmail pour verifier la validité de l'email
            if (isValidEmail(p.getEmail()) && isValidPassword(p.getPassword()) && !isExistEmail(p.getEmail())) {
                stmt = conn.prepareStatement("INSERT INTO client(nom, prenom, telephone, email, password)"
                        + "VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, p.getNom());
                stmt.setString(2, p.getPrenom());
                stmt.setString(3, p.getTelephone());
                stmt.setString(4, p.getEmail().toLowerCase());
                stmt.setString(5, p.getPassword());
                stmt.executeUpdate();
                System.out.println("Inserted!");

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
            stmt = conn.prepareStatement("SELECT * FROM client WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                flag = true;
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

    public void login(String email, String password) {
        if (conn != null) {
            try {
                stmt = conn.prepareStatement("SELECT idClient, password, nom FROM client WHERE email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    if (rs.getString("password").equals(password)) {
                        System.out.println("Welcome " + rs.getString("nom"));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you are not connected");
        }
    }
    public void updateNom(Personne p, String nom) {
        if (conn != null && isExistEmail(p.getEmail())) {
            try {
                stmt = conn.prepareStatement("UPDATE client SET nom = ? WHERE email = ?");
                stmt.setString(1, nom);
                stmt.setString(2, p.getEmail());
                stmt.executeUpdate();
                System.out.println("nom updated");

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

    public int getIdFromDB(Personne p) {
        int id = -1;
        try {
            stmt = conn.prepareStatement("SELECT idClient FROM client WHERE email = ?");
            stmt.setString(1, p.getEmail());
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

    public void logout() {
        System.exit(0);
        conn = null;
    }
}






