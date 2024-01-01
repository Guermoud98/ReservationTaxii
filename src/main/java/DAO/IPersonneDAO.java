package DAO;
import Business.*;

public interface IPersonneDAO {
    public ErreurInscription register(Personne p);
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String password);
    public boolean isExistEmail(String email);
    public int getIdFromDB(String email);

    public Personne getPersonneById(int id);

    public boolean login(String email, String password);
   //public void consulterHistoriqueReservation();
     public void updateNom(Personne p, String nom);
     public void updatePrenom(Personne p, String email);
     public void updateTelephone(Personne p, String telephone);
     //public int getIdFromDB(Personne p);
    //public void logout();
}
