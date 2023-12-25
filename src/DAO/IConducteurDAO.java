package DAO;

import Business.Taxi;

import java.util.List;

public interface IConducteurDAO extends IPersonneDAO {
    //public Conducteur SelectConductParId(int id );
    public String selectRandomMatricule();
    public List<Object> getRandomConducteur(); //cette methode retourne l'id d'un conducteur aléatoirement (util pour une reservation)
    public void updateTaxiStatus(String matricule);
    //public int SelectIdOfConducteur(Conducteur c);
    //public Conducteur SelectConducteurOfTaxi(Taxi taxi)
    //public void getAllConducteurs();
    public void conducteurOfATaxi(String matricule);

}
