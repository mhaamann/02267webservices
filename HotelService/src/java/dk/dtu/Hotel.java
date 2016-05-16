/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mhaamann
 */
public class Hotel {
    
    public String city;
    public String hotel;
    public int pricePerDay;
    ArrayList<Booking> bookings;
    public Boolean creditcardGuarantee;
    
    
    public Hotel(String city, String hotel, int pricePerDay, Boolean creditcardGuarantee) {
        this.bookings = new ArrayList<>();
        this.city = city;
        this.hotel = hotel;
        this.pricePerDay = pricePerDay;
        this.creditcardGuarantee = creditcardGuarantee;
    }
    
}
