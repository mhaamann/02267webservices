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
class Hotel {
    
    public String city;
    public String hotel;
    public int pricePerDay;
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    
    
    public Hotel(String city, String hotel, int pricePerDay) {
        this.city = city;
        this.hotel = hotel;
        this.pricePerDay = pricePerDay;
    }
    
}
