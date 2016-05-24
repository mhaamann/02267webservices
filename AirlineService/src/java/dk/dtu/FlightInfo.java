/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * @author Djoni Frank Strømsten
 */
class FlightInfo {
    
    public String origin, destination, bookingNumber, reservationService, carrier;
    public Date startDate, endDate;
    public int price;
    
    
    public String getBookingNumber(){
            return this.bookingNumber;
    }
    
    public FlightInfo(String origin, String destination, String bookingNumber, 
            String reservationService, String carrier, Date startDate, 
            Date endDate, int price) throws DatatypeConfigurationException{
        
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
