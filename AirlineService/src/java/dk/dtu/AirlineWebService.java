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
    public ArrayList<FlightInfo> getFlights(@WebParam(name = "origin") String origin, @WebParam(name = "destination") String destination, @WebParam(name = "startDate") XMLGregorianCalendar startDate){     
        return null;
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
