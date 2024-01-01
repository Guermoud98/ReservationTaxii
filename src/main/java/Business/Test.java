package Business;

public class Test {
    public static void main (String[] args) {
        GestionReservation reservation = new GestionReservation();
        Client cl = new Client("guerra", "dalal", "1233", "guerra.dal@gmail.com", "T1a@A");
        reservation.Reserver("Caree Eden, Marrakech 40000","Jamaâ El-Fna, Marrakech 40000 ", "Carte Bancaire",cl);
    }

}
