package UI;
import Business.Conducteur;
import DAO.IConducteurDAO;
import DAO.IConducteurDAOImplement;
import Session.ConducteurConnecte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConducteurProfilePage extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telephoneField;
    private JTextField matriculeField;

    private IConducteurDAO conducteur = new IConducteurDAOImplement();
    public ConducteurProfilePage() {
        initializeUI();
    }

    private void initializeUI() {
        // on a le conducteur qui est connecte en se basant de son id
        Conducteur c = (Conducteur) conducteur.getPersonneById(ConducteurConnecte.getConducteurId());

        setTitle("Conductor Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK); // Set background color to black
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Label and pre-filled text for Nom
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(nomLabel, gbc);

        gbc.gridx = 1;
        nomField = new JTextField(c.getNom());
        nomField.setEditable(false);
        panel.add(nomField, gbc);

        // Label and pre-filled text for Prenom
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel prenomLabel = new JLabel("Prenom:");
        prenomLabel.setForeground(Color.WHITE);
        panel.add(prenomLabel, gbc);

        gbc.gridx = 1;
        prenomField = new JTextField(c.getPrenom());
        prenomField.setEditable(false);
        panel.add(prenomField, gbc);

        // Label and pre-filled text for Telephone
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel telephoneLabel = new JLabel("Telephone:");
        telephoneLabel.setForeground(Color.WHITE);
        panel.add(telephoneLabel, gbc);

        gbc.gridx = 1;
        telephoneField = new JTextField(c.getTelephone());
        telephoneField.setEditable(false);
        panel.add(telephoneField, gbc);

        // Label and pre-filled text for Matricule du Taxi
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel matriculeLabel = new JLabel("Matricule du Taxi:");
        matriculeLabel.setForeground(Color.WHITE);
        panel.add(matriculeLabel, gbc);

        gbc.gridx = 1;
        /*getMatriculeConducteur : on fait appel a la method presente dans IConducteurDAO qui
        prend en parametre un id (dans notre cas c'est l'id du conducteur connecte en utilisant la classe
        utilitaire ConducteurConnecte) et qui retourne son matricule.
         */
        matriculeField = new JTextField(conducteur.getMatriculeConducteur(ConducteurConnecte.getConducteurId()));
        matriculeField.setEditable(false);
        panel.add(matriculeField, gbc);

        // Modify Fields Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton modifyButton = new JButton("Modifier les Champs");
        modifyButton.setBackground(Color.WHITE); // Set button background color
        modifyButton.setForeground(Color.BLACK); // Set button text color
        panel.add(modifyButton, gbc);

        // Confirm Button
        gbc.gridx = 1;
        JButton confirmButton = new JButton("Confirmer");
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setForeground(Color.BLACK);
        panel.add(confirmButton, gbc);



        // Add ActionListener for Modify Button
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable editable fields when the Modify button is clicked
                nomField.setEditable(true);
                prenomField.setEditable(true);
                telephoneField.setEditable(true);
            }
        });

        // Add ActionListener for Confirm Button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save changes (displaying a message in this example)
                conducteur.updateNom(c, nomField.getText());
                conducteur.updatePrenom(c, prenomField.getText());
                conducteur.updateTelephone(c, telephoneField.getText());
                JOptionPane.showMessageDialog(ConducteurProfilePage.this,
                        "Changes Confirmed:\nNom: " + nomField.getText() +
                                "\nPrenom: " + prenomField.getText() +
                                "\nTelephone: " + telephoneField.getText() +
                                "\nMatricule du Taxi: " + matriculeField.getText(),
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
