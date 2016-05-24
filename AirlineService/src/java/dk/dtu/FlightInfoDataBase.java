/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author Djoni Frank Strømsten
 */
public class FlightInfoDataBase {
    
    FlightInfo flight_1;
    FlightInfo flight_2;
    FlightInfo flight_3;
    FlightInfo flight_4;
    FlightInfo flight_5;
    ArrayList<FlightInfo> flightList = new ArrayList<>();
    public ArrayList<Payment> payments;

    public FlightInfoDataBase() throws DatatypeConfigurationException {
        
        this.payments = new ArrayList<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate1 = new Date(), endDate1 = new Date();
        try {
            startDate1 = dateFormat.parse("2016-01-01");
            endDate1 = dateFormat.parse("2016-01-10");
        } catch (ParseException ex) {
            Logger.getLogger(FlightInfoDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.flight_1 = new FlightInfo("Copenhagen", "Berlin", "B12341", 
                "LameDuck", "SAS", startDate1, endDate1, 50);
        this.flight_2 = new FlightInfo("Berlin", "Amsterdam", "B12342", 
                "LameDuck", "Ryanair", startDate1, endDate1, 100);
        this.flight_3 = new FlightInfo("Amsterdam", "Paris", "B12343", 
                "LameDuck", "SAS", startDate1, endDate1, 88);
        this.flight_4 = new FlightInfo("Paris", "Madrid", "B12344", 
                "LameDuck", "Ryanair", startDate1, endDate1, 66);        
        this.flight_5 = new FlightInfo("Madrid", "Copenhagen", "B12345", 
                "LameDuck", "SAS", startDate1, endDate1, 70);
        
        flightList.add(flight_1);
        flightList.add(flight_2);
        flightList.add(flight_3);
        flightList.add(flight_4);
        flightList.add(flight_5);
    }
    
    public ArrayList<FlightInfo> getFlights(String origin, String destination, String startDate) throws ParseException {
        ArrayList<FlightInfo> flightListToReturn = new ArrayList<FlightInfo>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (FlightInfo flight : flightList) {
            if (flight.origin.toLowerCase().equals(origin.toLowerCase()) && 
                    flight.destination.toLowerCase().equals(destination.toLowerCase())&&
                    flight.startDate.equals(dateFormat.parse(startDate))) {
                flightListToReturn.add(flight);
            }
        }
        return flightListToReturn;
    }
}
