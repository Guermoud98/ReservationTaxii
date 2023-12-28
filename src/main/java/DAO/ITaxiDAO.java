package DAO;

public interface ITaxiDAO {
    public void getAllTaxis();
    public void updateTaxiAffectationConducteur(String matricule);
    public String selectRandomMatricule();
    public void updateTaxiStatus(String matricule);
}
