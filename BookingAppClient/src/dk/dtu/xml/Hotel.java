/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.xml;

/**
 *
 * @author mhaamann
 */
public class Hotel {
    public String bookingNumber;
    public String status;
    
    public Hotel () {
        
    }
    public Hotel (String bookingNumber) {
        this.bookingNumber = bookingNumber;
        this.status = "unconfirmed";
    }
}
