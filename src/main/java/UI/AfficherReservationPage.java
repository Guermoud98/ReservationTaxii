package UI;

import Business.Reservation;
import DAO.IReservationDAO;
import DAO.IReservationDAOImplement;
import Session.ConducteurConnecte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfficherReservationPage extends JFrame {
    private IReservationDAO reservationDAO = new IReservationDAOImplement();

    public AfficherReservationPage() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Conducteur Reservations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK); // Set background color to black
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        //Menu
        JMenu menu = new JMenu("Menu");
        // element qui constitue le Menu
        JMenuItem e1 = new JMenuItem("Profil");
        //barre de menu
        JMenuBar menuBar = new JMenuBar();
        menu.add(e1);
        // l'ajout du menu au barre de menu
        menuBar.add(menu);

        JTextArea reservationsTextArea = new JTextArea();
        reservationsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservationsTextArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        panel.add(scrollPane, gbc);

        // Get reservations for the connected driver
        Reservation r = reservationDAO.getReservationById(ConducteurConnecte.getConducteurId());

        // Display reservations in the text area
        if (r != null) {
            reservationsTextArea.append("Votre prochaine reservation");
            reservationsTextArea.append("Lieu Source: " + r.getLieuSource() + "\n");
            reservationsTextArea.append("Lieu Destination: " + r.getLieuDestination() + "\n");
            reservationsTextArea.append("Type Paiement: " + r.getTypePaiement() + "\n");
            reservationsTextArea.append("Tarif: " + r.getTarif() + "\n");
            reservationsTextArea.append("Date: " + r.getD() + "\n");
            reservationsTextArea.append("Heure: " + r.getHeure() + "\n");
            reservationsTextArea.append("Le matricule de votre Taxi: " + r.getConducteurMatricule() + "\n");
        } else {
            reservationsTextArea.append("No reservations found for the connected driver.");
        }

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);

        e1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigez vers la page du profil ici
                dispose(); // Fermez la page de réservation si nécessaire
                new ConducteurProfilPage(); // Créez une nouvelle instance de ConducteurProfilePage
            }
        });
        setJMenuBar(menuBar);
    }

}


