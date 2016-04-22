/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author mhaamann
 */
public class HotelsDB {

    ArrayList<Hotel> availableRooms = new ArrayList<Hotel>();
    ArrayList<Booking> bookings = new ArrayList<Booking>();

    public HotelsDB() {
        
        // Add rooms to the database.
        availableRooms.add(new Hotel("Copenhagen", "CabIn", 40000));
        availableRooms.add(new Hotel("Copenhagen", "CabIn", 40000));
        availableRooms.add(new Hotel("Copenhagen", "CabIn", 40000));
        availableRooms.add(new Hotel("Amsterdam", "CabIn", 40000));
        availableRooms.add(new Hotel("Amsterdam", "CabIn", 40000));
        availableRooms.add(new Hotel("Amsterdam", "CabIn", 40000));
        availableRooms.add(new Hotel("Berlin", "CabIn", 40000));
        availableRooms.add(new Hotel("Berlin", "CabIn", 40000));
        availableRooms.add(new Hotel("Berlin", "CabIn", 40000));
        availableRooms.add(new Hotel("Berlin", "CabIn", 40000));
        availableRooms.add(new Hotel("Berlin", "CabIn", 40000));
        
    }

    public ArrayList<Hotel> getHotels(String city, XMLGregorianCalendar arrivalDate, XMLGregorianCalendar departureDate) {
        return null;
    }
    
    
}
