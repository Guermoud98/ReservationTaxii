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
     IClientDAO i = new IClientDAOImplement();
     Conducteur cond = new Conducteur("Amine", "Madd", "0866554455", "amine.a@gmail.com", "Am@#s1d");

     Client cl = new Client("asmahane", "asma", "0672673245", "asmae.as@gmail.com", "Asm12@T1");
     IConducteurDAO c = new IConducteurDAOImplement();
     //i.register(cl);
     //Reservation r = new Reservation("Sidi Abbad", "Targa", "Carte Bancaire", 30.0f, date, time, cl);
     //res.insertReservation(r);
     //c.login("jacki.f@gmail.com", "Ja@18");
     //c.register(cond);

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
