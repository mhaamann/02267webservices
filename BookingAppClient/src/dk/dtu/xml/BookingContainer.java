/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.xml;

import ExternalBookingService.Booking;
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
    public List<Action> actions;

    public void set(List<Booking> hotelList) {
        this.hotel = hotelList;
        
        actions = new ArrayList<Action>();
        this.actions.add(new Action("GET", "/hotels?city={city}&arrivalDate={date}&departureDate={date}"));
        this.actions.add(new Action("POST", "/hotels?bookingNumber={bookingNumber}&itineraryId={itineraryId}"));
    }

}
