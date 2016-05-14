/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.xml;

import dk.dtu.Booking;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mhaamann
 */
@XmlRootElement
public class BookingContainer {

    public List<Booking> hotel;
    public List<String> actions;
    public String lol = "lol";

    public void set(List<Booking> hotelList) {
        this.hotel = hotelList;
        
        this.actions.add("/itinerary/1005");
        this.actions.add("/hotels");
    }

}
