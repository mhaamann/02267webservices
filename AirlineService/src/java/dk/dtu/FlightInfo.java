/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author User
 */
class FlightInfo {
    
    String origin, destination, bookingNumber, reservationService, carrier;
    XMLGregorianCalendar startDate, endDate;
    Integer price;
    
    public FlightInfo(String origin, String destination, String bookingNumber, 
            String reservationService, String carrier, XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price) throws DatatypeConfigurationException{
        
        this.origin = origin; 
        this.destination = destination;
        this.bookingNumber = bookingNumber; 
        this.reservationService = reservationService; 
        this.carrier = carrier;
        this.startDate = startDate; 
        this.endDate = endDate;
        this.price = price;
    }
 
    public FlightInfo(){}
}
