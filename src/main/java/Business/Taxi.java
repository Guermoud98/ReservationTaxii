package Business;

public class Taxi {
    private String matricule;
    private String modele;
    private String status;

    public Taxi(String matricule, String modele, String status) {
        this.matricule = matricule;
        this.modele = modele;
        this.status = status;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "matricule='" + matricule + '\'' +
                ", modele='" + modele + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
