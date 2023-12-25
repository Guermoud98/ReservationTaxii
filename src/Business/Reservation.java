package Business;


import java.time.LocalDate;
import java.time.LocalTime;
public class Reservation {
    private Client client;
    private Conducteur conducteur;
    private String lieuSource;
    private String lieuDestination;

    private String typePaiement;
    private float tarif;

    private LocalDate d;
    private LocalTime heure;

    public Reservation(String lieuSource, String lieuDestination, String typePaiement, float tarif, LocalDate d, LocalTime heure, Client cl) {
        this.lieuSource = lieuSource;
        this.lieuDestination = lieuDestination;
        this.tarif = tarif;
        this.d = d;
        this.heure = heure;
        this.client = cl;
        this.typePaiement = typePaiement;
    }

    public String getLieuSource() {
        return lieuSource;
    }

    public void setLieuSource(String lieuSource) {
        this.lieuSource = lieuSource;
    }

    public String getLieuDestination() {
        return lieuDestination;
    }

    public void setLieuDestination(String lieuDestination) {
        this.lieuDestination = lieuDestination;
    }

    public float getTarif() {
        return tarif;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public LocalDate getD() {
        return d;
    }

    public void setD(LocalDate d) {
        this.d = d;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }
    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client=" + client +
                ", conducteur=" + conducteur +
                ", lieuSource='" + lieuSource + '\'' +
                ", lieuDestination='" + lieuDestination + '\'' +
                ", typePaiement='" + typePaiement + '\'' +
                ", tarif=" + tarif +
                ", d=" + d +
                ", heure=" + heure +
                '}';
    }
}
