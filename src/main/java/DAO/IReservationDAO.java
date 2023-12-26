package DAO;

import Business.Client;
import Business.Reservation;

public interface IReservationDAO {
     public void getAllReservations();
     public void insertReservation(Reservation r);
    //public void getReservationOfSpecificClient(Client c);
}
