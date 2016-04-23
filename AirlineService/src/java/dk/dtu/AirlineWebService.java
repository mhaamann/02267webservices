/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author User
 */
@WebService(serviceName = "AirlineWebService")
public class AirlineWebService {

    
    FlightInfoDataBase flightDB;

    public AirlineWebService() throws DatatypeConfigurationException {
        this.flightDB = new FlightInfoDataBase();
    }
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " ! from Airline";
    }
    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightInfo> getFlights(@WebParam(name = "origin") String origin, 
            @WebParam(name = "destination") String destination, 
            @WebParam(name = "startDate") XMLGregorianCalendar startDate){     
        ArrayList<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
        
        if(flightDB.flight_1.origin.equals(origin) && 
                flightDB.flight_1.destination.equals(destination) &&
                flightDB.flight_1.startDate.equals(startDate)){
            flightInfoList.add(flightDB.flight_1);
        }
        
        if(flightDB.flight_2.origin.equals(origin) && 
                flightDB.flight_2.destination.equals(destination) &&
                flightDB.flight_2.startDate.equals(startDate)){
            flightInfoList.add(flightDB.flight_2);
        }
        
        if(flightDB.flight_3.origin.equals(origin) && 
                flightDB.flight_3.destination.equals(destination) &&
                flightDB.flight_3.startDate.equals(startDate)){
            flightInfoList.add(flightDB.flight_3);
        }
        
        if(flightDB.flight_4.origin.equals(origin) && 
                flightDB.flight_4.destination.equals(destination) &&
                flightDB.flight_4.startDate.equals(startDate)){
            flightInfoList.add(flightDB.flight_4);
        }
        
        if(flightDB.flight_5.origin.equals(origin) && 
                flightDB.flight_5.destination.equals(destination) &&
                flightDB.flight_5.startDate.equals(startDate)){
            flightInfoList.add(flightDB.flight_5);
        }
        
        return flightInfoList;
    }
    
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber, @WebParam(name = "creditCard") CreditCard creditCard){
        return true;
    }
    
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber, @WebParam(name = "price") Integer price, @WebParam(name = "creditCard") CreditCard creditCard){
        return true;
    }
}
