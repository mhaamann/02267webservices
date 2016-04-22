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
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author User
 */
@WebService(serviceName = "AirlineWebService")
public class AirlineWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " ! from Airline";
    }
    @WebMethod(operationName = "getFlights")
    public ArrayList<FlightInfo> getFlights(String origin, String destination, XMLGregorianCalendar startDate){     
        FlightInfo a = new FlightInfo();
        ArrayList<FlightInfo> strings = new ArrayList<FlightInfo>();
        strings.add(a);
        return strings;
    }
    
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(String bookingNumber, CreditCard creditCard){
        return true;
    }
    
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(String bookingNumber, Integer price, CreditCard creditCard){
        return true;
    }
    
    
}
