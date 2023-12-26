 package DAO;
import Business.*;

import java.sql.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
 public static void main (String [] args) {
     LocalDate date = LocalDate.now();
     LocalTime time = LocalTime.now();
     Connection conn = ConnectionDB.getConnexion();
     IReservationDAO res = new IReservationDAOImplement();
     Conducteur cond = new Conducteur("Adam", "Smith", "0645365478", "adam.sm@gmail.com", "Aq67@");
     Client cl = new Client("meriem", "aitAli", "0656787656", "meriem.ait@gmail.com", "Merry@1");
     IConducteurDAO c = new IConducteurDAOimplement();
     //c.register(cond);
     Reservation r = new Reservation("Bab Doukala", "Gueliz", "Espece", 25.0f, date, time, cl);
     //res.insertReservation(r);
     //c.login("jacki.f@gmail.com", "Ja@18");

     //IClientDAO i = new IClientDAOImplement();
     //ITaxiDAO taxi = new ITaxiDAOImplementation();
     //IReservationDAO res = new IReservationDAOImplement();
     //res.getAllReservations();
     //taxi.getAllTaxis();
     //System.out.println(i.getIdFromDB(c1));

     //i.register(c2);
     //i.login("neha.et@gmail.com", "Neh2003@");
     //i.updateInfo("nehaa",null,"03435");
     //Conducteur c2 = new Conducteur("d","dyy","093338","dd@33");
     //Client c3 = new Client("ssd","dssyy","09223338","dd@323");
     //System.out.println(c1);S
     //System.out.println(c2);
     //System.out.println(c3);
     //i.logout();
     //System.out.println("hello");
     //i.updateTelephone(c2,  "0753684");

 }
   
}
