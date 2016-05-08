/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.Hotel;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import bookingappclient.BookingService;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jens
 */
public class testP1 {
    
    String city1 = "Copenhagen";
    String fromDate1 = "2016-01-01";
    String toDate1 = "2016-01-30";
    String itinerary1 = "1";
    String bookingNumber1 = "1";
    
    @Test
    public void testP1() {
        //Create itinerary
        BookingService.createItinerary(null);
        //TODO:Get list of flights
        //TODO:Add flight to itinerary
        
        //Get list of hotels
        GetHotelsResponse hotels = BookingService.getHotels(city1, fromDate1 , toDate1, itinerary1);
        List<Hotel> hotelList = hotels.getReturn();
        
        //Add hotel to itinerary
        HotelList hotelListReceipt = BookingService.addHotel(bookingNumber1, itinerary1);
        
        //TODO:Add a second flight to itinerary
        
        //TODO:Add a third flight to itinerary
        
        //Add a second hotel to itinerary
        hotelListReceipt = BookingService.addHotel(bookingNumber1, itinerary1);
        List<HotelReservation> hotelReservationReceipt = hotelListReceipt.getReservation();
        
        //Get itinerary
        hotelListReceipt = BookingService.listItinerary(null);
        
        //TODO:Assert all flights added
        
        //Assert all hotels added
        hotelListReceipt.getReservation();
        //hotelListReceipt.
        
        //Assert all flights and hotels are unconfirmed
        
        //Book itinerary
        
        //Assert all statues are confirmed
        
    }

}
