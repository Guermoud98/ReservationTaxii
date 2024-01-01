package Business;

public class Client extends Personne {
    public Client(String nom, String prenom, String telephone, String email, String password) {

        super(nom, prenom, telephone, email, password);
    }
    public Client () {}
    public int getIdClient() {

        return super.getId();
    }
}
