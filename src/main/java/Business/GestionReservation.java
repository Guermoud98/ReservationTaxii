package Business;

import DAO.IReservationDAO;
import DAO.IReservationDAOImplement;
import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import java.lang.Math;

import java.time.LocalDate;
import java.time.LocalTime;



public class GestionReservation {
    private String lieuDestination;
    private String lieuSource;
    private final JOpenCageGeocoder key = new JOpenCageGeocoder("5b0f324abbff4feda79ea888b6472ae6"); // la clé

    public GestionReservation(String lieuDestination, String lieuSource) {
        this.lieuDestination = lieuDestination;
        this.lieuSource = lieuSource;
    }
    public GestionReservation() {

    }
    public void Reserver(String lieuSource, String lieuDestination, Client cl) {
        //la conversion du lieuDestination et lieuSource en latitude et longitude
        System.out.println("LongitudeSource: " + getLongitudeLieu(lieuSource));
        System.out.println("LatitudeSource: " + getLatitudeLieu(lieuSource));
        System.out.println("LongitudeDestination: " + getLongitudeLieu(lieuDestination));
        System.out.println("LatitudeDestination: " + getLatitudeLieu(lieuDestination));

        System.out.println("La distance est " + calculerDistance(lieuSource, lieuDestination));
        System.out.println("la tarification est: " + CalculTarifEnFctDistance(lieuSource, lieuDestination) );
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        float tarif = (float) CalculTarifEnFctDistance(lieuSource, lieuDestination);
        IReservationDAO res = new IReservationDAOImplement();
        Reservation r = new Reservation(lieuSource, lieuDestination, "Carte Bancaire", tarif , date, time, cl );
        res.insertReservation(r);
    }

    //cette methode retourne un hashmap qui stock la longitude et sa valeur du lieu passe en parametre
    public float getLongitudeLieu(String lieu) {
        //request
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(lieu);
        //response
        JOpenCageResponse response = key.forward(request);
        //firstPosition : // get the coordinate pair of the first result
        JOpenCageLatLng firstResult = response.getFirstPosition();
        float srcLongitude = Float.parseFloat(firstResult.getLng().toString()); // on fait le parse car firstResultLatLngSource.getLng().toString() retourne un string
        return srcLongitude;
    }
    public float getLatitudeLieu(String lieu) {
        //request
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(lieu);
        //response
        JOpenCageResponse response = key.forward(request);
        //firstPosition : // get the coordinate pair of the first result
        JOpenCageLatLng firstResult = response.getFirstPosition();
        float srcLatitude = Float.parseFloat(firstResult.getLat().toString()); // on fait le parse car firstResult.getLng().toString() retourne un string
        return srcLatitude;
    }
    //distance entre lieuSource et lieuDestination
    public float calculerDistance(String lieuSource, String lieuDestination) {
        float distance = -1 ; //en km
        try {
           //les longitudes et latitudes reçus via l'Api sont en degre
           double srcLongitude = Math.toRadians(getLongitudeLieu(lieuSource));
           double srcLatitude = Math.toRadians(getLatitudeLieu(lieuSource));
           double destLongitude = Math.toRadians(getLongitudeLieu(lieuDestination));
           double destLatitude = Math.toRadians(getLatitudeLieu(lieuDestination));
           double rayonTerre = 6371;
           double differenceLongitude = Math.toRadians(destLongitude - srcLongitude);
           double distanceAngulaire = Math.acos(Math.sin(srcLatitude) * Math.sin(destLatitude) + Math.cos(srcLatitude) * Math.cos(destLatitude) * Math.cos(differenceLongitude));
           distance = (float) (distanceAngulaire * rayonTerre);

       } catch (Exception e) {
           e.printStackTrace();
       }

        return distance;
    }
    public double CalculTarifEnFctDistance(String lieuSource,String lieuDestination)
    {
        double tarifBase = 5.0;

        // Tarif par kilomètre
        double tarifParKilometre = 2.0;

        // Calcul du tarif total
        double distance = calculerDistance(lieuSource,lieuDestination);
        double tarifTotal = tarifBase + (tarifParKilometre * distance);
        return tarifTotal;

    }

}
