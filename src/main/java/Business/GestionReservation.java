package Business;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GestionReservation {

    public static void main(String[] args) {
        System.out.println("hello");
        try {

            String apiKey = "5b0f324abbff4feda79ea888b6472ae6";
            String location = "37.7749,-122.4194"; // Coordinates for San Francisco
            String countryCode = "us";

            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + apiKey +
                    "&q=" + location + "&countrycode=" + countryCode;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                System.out.println("Response Content: " + content.toString());
            } else {
                System.out.println("Error in HTTP request");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
