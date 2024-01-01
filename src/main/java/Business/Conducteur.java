package Business;

public class Conducteur extends Personne{
    private Taxi taxi;
    private int id;
    private String matricule;

    public Conducteur(String nom, String prenom, String telephone, String email, String password)
    {
        super(nom, prenom, telephone, email, password);
    }
    public Conducteur () {}
    public int getIntClient() {

        return super.getId();
    }
    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }
    public void setMatricule(String matricule) {
        this.matricule=matricule;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setIdConducteur(int id) {
        this.id =id;
    }


}
