/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExternalBookingService.Booking;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import bookingappclient.BookingServiceBPELWrapper;
import java.util.List;
import java.util.ListIterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jens
 */
public class testP1 {
    
    BookingServiceBPELWrapper bookingServiceBPEL = new BookingServiceBPELWrapper();
    String city1 = "Copenhagen";
    String fromDate1 = "2016-01-01";
    String toDate1 = "2016-01-30";
    String itinerary1 = "1";
    String bookingNumber1 = "1";
    String bookingNumber2 = "2";
    
    @Test
    public void testP1() {
        //Create itinerary
        bookingServiceBPEL.createItinerary(itinerary1);
        //TODO:Get list of flights
        //TODO:Add flight to itinerary
        
        //Get list of hotels
        List<Booking> hotelBookingList= bookingServiceBPEL.getHotels(city1, fromDate1 , toDate1, itinerary1);
        
        //Add hotel to itinerary
        //Get bookingnumber from returned hotels
        List<HotelReservation> hotelReservationList = bookingServiceBPEL.addHotel(bookingNumber1, itinerary1);
        HotelReservation hotelReservation = hotelReservationList.get(0);
        assertEquals(1, hotelReservation.getBookingNumber());
        assertEquals("unconfirmed", hotelReservation.getStatus());
        
        
        //TODO:Add a second flight to itinerary
        
        //TODO:Add a third flight to itinerary
        
        //Add a second hotel to itinerary
        hotelReservationList = bookingServiceBPEL.addHotel(bookingNumber2, itinerary1);
        
        //Get itinerary
        hotelReservationList = bookingServiceBPEL.listItinerary(itinerary1);
        
        //TODO:Assert all flights added
        
        //Assert all hotels added
        ListIterator<HotelReservation> hotelReservationIterator = hotelReservationList.listIterator();
        
        
        
        //Assert all flights and hotels are unconfirmed
        
        //Book itinerary
        
        //Assert all statues are confirmed
        
    }

}
