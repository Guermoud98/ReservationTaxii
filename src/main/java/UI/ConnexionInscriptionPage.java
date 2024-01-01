package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionInscriptionPage extends JFrame {
    public ConnexionInscriptionPage() {
        // Configuration de la fenêtre
        setTitle("Application de Resérvation d'un Taxi");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal avec un gestionnaire de disposition FlowLayout
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(Color.BLACK);  // Fond noir
        setContentPane(panel);

        // Bouton "Connexion"
        JButton connexionButton = new JButton("Connexion");
        styleButton(connexionButton); // Appliquer le style au bouton
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton "Connexion" est cliqué
                System.out.println("Bouton Connexion clique");
                new ConnexionPage();
                dispose();
            }
        });
        panel.add(connexionButton);

        // Bouton "Inscription"
        JButton inscriptionButton = new JButton("Inscription");
        styleButton(inscriptionButton); // Appliquer le style au bouton
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton "Inscription" est cliqué
                System.out.println("Bouton Inscription clique");
                new InscriptionPage();
                dispose();
            }
        });
        panel.add(inscriptionButton);

        // Affichage de la fenêtre
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false); // Pour supprimer le contour de mise au point
    }


}




