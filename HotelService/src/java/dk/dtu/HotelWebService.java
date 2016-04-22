/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.datatype.XMLGregorianCalendar;
/**
 *
 * @author User
 */
@WebService(serviceName = "HotelWebService")
public class HotelWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " ! from Hotel";
    }
    
    @WebMethod(operationName = "getHotels")
    public ArrayList<Hotel> getHotels(@WebParam(name = "city") String city, @WebParam(name = "arrivalDate") XMLGregorianCalendar arrivalDate, @WebParam(name = "departureDate") XMLGregorianCalendar departureDate){     
        return null;
    }
    
    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(@WebParam(name = "bookingNumber") String bookingNumber, @WebParam(name = "creditCard") CreditCard creditCard){
        return true;
    }
    
    @WebMethod(operationName = "cancelHotel")
    public boolean cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber){
        return true;
    }
}
