package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoixRolePage extends JFrame {
    //on stocke le role choisi par l'utilisateur
    public static String roleChoisi;
    public ChoixRolePage() {
        // Configuration de la fenêtre
        setTitle("Application de Resérvation d'un Taxi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chargement de l'image en arrière-plan
        ImageIcon backgroundIcon = new ImageIcon("carImage.png"); // Remplacez "background_image.jpg" par le chemin de votre image
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        setContentPane(backgroundLabel);

        // Texte "Choisir votre rôle"
        JLabel label = new JLabel("Choisir votre rôle");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        // Bouton "Conducteur"
        JButton conducteurButton = new JButton("Conducteur");
        // Si on clique sur le role Conducteur , la fenetre d'accueil se ferme, et celle de le ConnexionInscription s'ouvre
        conducteurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Connexion
                new ConnexionInscriptionPage();
                ChoixRolePage.roleChoisi = "conducteur";
                System.out.println("Bouton Conducteur clique");
                dispose();
            }
        });

        // Bouton "Passager"
        JButton passagerButton = new JButton("passager");
        passagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConnexionInscriptionPage();
                ChoixRolePage.roleChoisi = "passager";
                System.out.println("Bouton passager clique");
                dispose();
            }
        });

        // Ajout des composants à la fenêtre
        backgroundLabel.setLayout(new BoxLayout(backgroundLabel, BoxLayout.Y_AXIS));
        backgroundLabel.add(Box.createVerticalGlue());
        backgroundLabel.add(label);
        backgroundLabel.add(Box.createVerticalStrut(20));
        backgroundLabel.add(conducteurButton);
        backgroundLabel.add(Box.createVerticalStrut(10));
        backgroundLabel.add(passagerButton);
        backgroundLabel.add(Box.createVerticalGlue());

        // Affichage de la fenêtre
        setVisible(true);
    }


}

