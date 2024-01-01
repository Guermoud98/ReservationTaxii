package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.*;
import Session.*;

public class ConnexionPage extends JFrame {
    private IClientDAO clientDao = new IClientDAOImplement();
    private  IConducteurDAO conducteurDao = new IConducteurDAOImplement();
    public ConnexionPage() {
        super("Application de Resérvation d'un Taxi");

        // Création des composants
        JLabel labelEmail = new JLabel("Email :");
        JTextField textFieldEmail = new JTextField();
        JLabel labelPassword = new JLabel("Mot de passe :");
        JPasswordField passwordField = new JPasswordField();
        JButton buttonConnexion = new JButton("Connexion");

        // Ajout des écouteurs d'événements
        buttonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton "Connexion"
                String email = textFieldEmail.getText();
                String password = String.valueOf(passwordField.getPassword());
                if("passager".equals(ChoixRolePage.roleChoisi)) {
                    if(clientDao.login(email, password)) {
                        JOptionPane.showMessageDialog(ConnexionPage.this, "Connexion réussie !","Informations de Connexion", JOptionPane.INFORMATION_MESSAGE);
                        int id = clientDao.getIdFromDB(email);
                        System.out.println("l'id du client connecte est " + id);
                        ClientConnecte.setClientId(id); //cette ligne va m'aider dans la reservation pour savoir l'id du client connecte qui veut faire la reservation
                        new ReservationPage();
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(ConnexionPage.this, "Email ou mot de passe incorrect !", "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    if("conducteur".equals(ChoixRolePage.roleChoisi)) {
                        if(conducteurDao.login(email, password)) {
                            int id = conducteurDao.getIdFromDB(email);
                            ConducteurConnecte.setConducteurId(id);//pour savoir l'id du conducteur connecte pour lui afficher par exemple les reservations qui lui concerne
                            System.out.println(ConducteurConnecte.getConducteurId());
                            JOptionPane.showMessageDialog(ConnexionPage.this, "Connexion réussie !","Informations de Connexion", JOptionPane.INFORMATION_MESSAGE);
                            //new ConducteurProfilPage();
                            new AfficherReservationPage(); //On affiche la page qui concerne les reservations du conducteur connecté
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(ConnexionPage.this, "Email ou mot de passe incorrect !", "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                // Ajoutez ici le code pour gérer la connexion avec les informations fournies
                // Pour l'instant, affichons simplement les informations
                JOptionPane.showMessageDialog(ConnexionPage.this,
                        "Email : " + email + "\nMot de passe : " + new String(password),
                        "Informations de Connexion", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Mise en page
        setLayout(new GridLayout(4, 2, 10, 10)); // Ajout de marges entre les composants
        add(new JLabel()); // Espace vide
        add(new JLabel()); // Espace vide
        add(labelEmail);
        add(textFieldEmail);
        add(labelPassword);
        add(passwordField);
        add(new JLabel()); // Espace vide
        add(buttonConnexion);

        // Couleurs de fond et de texte
        getContentPane().setBackground(new Color(34, 34, 34)); // Fond noir
        labelEmail.setForeground(Color.WHITE);
        labelPassword.setForeground(Color.WHITE);
        textFieldEmail.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        buttonConnexion.setBackground(Color.WHITE);
        buttonConnexion.setForeground(Color.BLACK);

        // Configuration de la fenêtre
        pack(); // Ajuste la taille en fonction du contenu
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose la fenêtre sans fermer l'application
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
