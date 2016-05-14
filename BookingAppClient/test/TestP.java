/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.FlightList;
import ExternalBookingService.FlightReservation;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import bookingappclient.BookingServiceBPELWrapper;
import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/**
 *
 * @author jens
 */
public class TestP {
    
    
    String city1 = "Copenhagen";
    String city2 = "Berlin";
    String fromDate1 = "2016-01-01";
    String toDate1 = "2016-01-30";
    String toDateFlight1 = "2016-01-10";
    String itinerary1 = "42";
    String bookingNumber1 = "1";
    String bookingNumber2 = "2";
    String flightBooking1 = "B12341";
    String hotelBookingNo1 = "7";
    String hotelBookingNo2 = "12";
    
    @Test
    public void testP1() {
        
        BookingServiceBPELWrapper bookingServiceBPEL = new BookingServiceBPELWrapper();
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        itinerary1 = Integer.toString(random);
        
        //Create itinerary
        System.out.println("Create Itinerary number " + itinerary1);
        bookingServiceBPEL.createItinerary(itinerary1);
        
        //Reset hotels and flights for jUnit test.
        //bookingServiceBPEL.reset(itinerary1);
        
        //Get list of flights
        System.out.println("Finding flights..");
        List<FlightInfo> flightList = bookingServiceBPEL.getFlights(city1, city2, toDateFlight1, itinerary1);
        
        //Add flight to itinerary
        System.out.println("Adding first flight..");
        bookingServiceBPEL.addFlight(flightBooking1, itinerary1);
        
        //Get list of hotels
        System.out.println("Finding hotels..");
        List<Booking> hotelBookingList = bookingServiceBPEL.getHotels(city1, fromDate1 , toDate1, itinerary1);
        
        //Add hotel to itinerary
        //Get bookingnumber from returned hotels
        System.out.println("Adding first hotel..");
        List<HotelReservation> hotelReservationList = bookingServiceBPEL.addHotel(hotelBookingNo1, itinerary1);
        //assertEquals(1, hotelReservation.getBookingNumber());
        //assertEquals("unconfirmed", hotelReservation.getStatus());
        
        
        //TODO:Add a second flight to itinerary
        bookingServiceBPEL.addFlight("B12342", itinerary1);
        
        //TODO:Add a third flight to itinerary
        bookingServiceBPEL.addFlight("B12343", itinerary1);
        
        //Add a second hotel to itinerary
        System.out.println("Add second hotel..");
        hotelReservationList = bookingServiceBPEL.addHotel(hotelBookingNo2, itinerary1);
        
        
        
        //Get itinerary
        System.out.println("Getting Itinerary..");
        
        List<HotelReservation> hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);        
        for(HotelReservation reservation : hReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        List<FlightReservation> fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        for (FlightReservation reservation : fReservationList){
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        //TODO:Assert all flights added
        
        //Assert all hotels added
        System.out.println("Asserting hotels..");
        
        //Assert all flights and hotels are unconfirmed
        
        //Book itinerary
        bookingServiceBPEL.bookItinerary(itinerary1, city1, city1, BigInteger.ZERO, BigInteger.ZERO);
        
        //Assert all statues are confirmed
        
    }
    
    @Test
    public void testP2() {
        BookingServiceBPELWrapper bookingServiceBPEL = new BookingServiceBPELWrapper();

        //Create itinerary
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        itinerary1 = Integer.toString(random);
        
        System.out.println("Create Itinerary number " + itinerary1);
        //bookingServiceBPEL.createItinerary(itinerary1);

        //Get list of flights
        //System.out.println("Finding flights..");
        //List<FlightInfo> flightList = bookingServiceBPEL.getFlights(city1, city2, toDateFlight1, itinerary1);

        //Add flight to itinerary
        //System.out.println("Add flight..");
        //bookingServiceBPEL.addFlight(flightBooking1, itinerary1);

        //Cancel Planning
        //bookingServiceBPEL.cancelItinerary(itinerary1);

        //Assert that stuff is correct...
        
    }

}
