package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Business.Client;
import Business.Conducteur;
import Business.Personne;
import DAO.*;

public class InscriptionPage extends JFrame {
    private IClientDAO clientDao = new IClientDAOImplement();
    private IConducteurDAO conducteurDAO = new IConducteurDAOImplement();


    public InscriptionPage() {
        super("Page de Connexion");

        // Création des composants
        JLabel nameLabel = new JLabel("Nom :");
        JLabel firstNameLabel = new JLabel("Prénom :");
        JLabel phoneLabel = new JLabel("Téléphone :");
        JLabel emailLabel = new JLabel("Email :");
        JLabel passwordLabel = new JLabel("Mot de passe :");

        JTextField nameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Inscription");

        // Ajout des écouteurs d'événements
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Connexion
                String name = nameField.getText();
                String firstName = firstNameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                System.out.println(ChoixRolePage.roleChoisi);
               // les intances du client et conduteur
                if ("passager".equals(ChoixRolePage.roleChoisi)){
                    Personne client = new Client(name, firstName, phone, email, password);
                    ErreurInscription erreurClient = clientDao.register(client);
                    switch (erreurClient) {
                        case EMAIL_EXIST:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Email existe deja", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case EMAIL_INVALIDE:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Veuillez saisir une adresse e-mail valide contenant au moins un chiffre, au moins une lettre miniscule et au moins un caractere ", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case PASSWORD_INVALID:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Veuillez saisir un password valide qui combine entre les lettres, chiffre et les caracteres", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case CHAMP_VIDE:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Les champs ne doivent pas etre vide", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case AUCUNE_ERREUR:
                            new ReservationPage();
                            dispose();
                            break;
                    }
                }
                else if ("conducteur".equals(ChoixRolePage.roleChoisi)) {
                    Personne conducteur = new Conducteur(name, firstName, phone, email, password);
                    ErreurInscription erreurConducteur = conducteurDAO.register(conducteur);
                    switch (erreurConducteur) {
                        case EMAIL_EXIST:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Email existe deja", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case EMAIL_INVALIDE:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Veuillez saisir une adresse e-mail valide contenant au moins un chiffre, au moins une lettre miniscule et au moins un caractere ", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case PASSWORD_INVALID:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Veuillez saisir un password valide qui combine entre les lettres, chiffre et les caracteres", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case CHAMP_VIDE:
                            JOptionPane.showMessageDialog(InscriptionPage.this, "Les champs ne doivent pas etre vide", "champ invalide",  JOptionPane.ERROR_MESSAGE);
                            break;
                        case AUCUNE_ERREUR:
                            new ReservationPage();
                            //new ConducteurProfilPage();
                            dispose();
                            break;
                    }

                }


            }
        });

        // Mise en page
        setLayout(new GridLayout(6, 2));
        add(nameLabel);
        add(nameField);
        add(firstNameLabel);
        add(firstNameField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Espacement
        add(loginButton);

        // Styles
        getContentPane().setBackground(Color.BLACK);
        nameLabel.setForeground(Color.WHITE);
        firstNameLabel.setForeground(Color.WHITE);
        phoneLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        nameField.setBackground(Color.WHITE);
        firstNameField.setBackground(Color.WHITE);
        phoneField.setBackground(Color.WHITE);
        emailField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);

        // Configuration de la fenêtre
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
