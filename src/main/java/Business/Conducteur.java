package Business;

public class Conducteur extends Personne{
    private Taxi taxi;

    public Conducteur(String nom, String prenom, String telephone, String email, String password)
    {
        super(nom, prenom, telephone, email, password);
    }
    public int getIntClient() {

        return super.getId();
    }
    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

}
