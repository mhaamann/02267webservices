/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.FlightReservation;
import ExternalBookingService.HotelReservation;
import bookingappclient.BookingServiceBPELWrapper;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/**
 *
 * @author Jens Paulsen
 */
public class TestP {
    
    
    String city1 = "Copenhagen";
    String city2 = "Berlin";
    String fromDateHotel1 = "2016-01-01";
    String toDateHotel1 = "2016-01-30";
    String fromDateFlight1 = "2016-01-01";
    String toDateFlight1 = "2016-01-10";
    String itinerary1;
    String flightBookingNo1 = "B12341";
    String flightBookingNo2 = "B12342";
    String flightBookingNo3 = "B12343";
    String hotelBookingNo1;
    String hotelBookingNo2;
    String CreditCardName1 = "Anne Strandberg";
    String CreditCardNumber1 = "50408816";
    BigInteger CreditCardMonth1 = BigInteger.valueOf(5);
    BigInteger CreditCardYear1 = BigInteger.valueOf(9);
    String statusConfirmed = "confirmed";
    String statusCancelled = "cancelled";
    String statusUnconfirmed = "unconfirmed";
    
    
    /*P1 (planning and booking) Plan a trip by first planning a flight (i.e. getting a list of flights and then
    adding a flight to the itinerary), then by planning a hotel, another flight, a third flight, and finally
    a hotel. Ask for the itinerary and check that it is correct using JUnit’s assert statements – i.e.
    assertEquals, assertTrue, . . . – in particular, that the booking status for each item is unconfirmed.
    Book the itinerary and ask again for the itinerary. Check that each booking status is now confirmed.*/

    @Test
    public void testP1_BPEL() {
        
        BookingServiceBPELWrapper bookingServiceBPEL = new BookingServiceBPELWrapper();
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        itinerary1 = Integer.toString(random);
        
        //Create itinerary
        System.out.println("Creating Itinerary number " + itinerary1);
        String itineraryString = bookingServiceBPEL.createItinerary(itinerary1);
        assertNotNull(itineraryString);
        assertNotSame("", itineraryString);
        
        //Reset hotels and flights for jUnit test.
        //bookingServiceBPEL.reset(itinerary1);
        
        //Get list of flights
        System.out.println("Finding flights..");
        List<FlightInfo> flightBookingList = bookingServiceBPEL.getFlights(city1, city2, fromDateFlight1, itinerary1);
        assertFalse(flightBookingList.isEmpty() );
        
        //Add flight to itinerary
        System.out.println("Adding first flight..");
        List<FlightReservation> flightReservationList = bookingServiceBPEL.addFlight(flightBookingNo1, itinerary1);
        assertNotNull(flightReservationList);
        assertEquals(1, flightReservationList.size());
        
        //Get list of hotels
        System.out.println("Finding hotels..");
        List<Booking> hotelBookingList = bookingServiceBPEL.getHotels(city1, fromDateHotel1 , toDateHotel1, itinerary1);
        assertNotNull(hotelBookingList);
        assertFalse(hotelBookingList.isEmpty() );
        assertTrue(hotelBookingList.size() > 1 );
        
        //Add hotel to itinerary
        //Get bookingnumber from returned hotels
        System.out.println("Adding first hotel..");
        
        hotelBookingNo1 = hotelBookingList.get(0).getBookingNumber();
        hotelBookingNo2 = hotelBookingList.get(1).getBookingNumber();
        
        List<HotelReservation> hotelReservationList = bookingServiceBPEL.addHotel(hotelBookingNo1, itinerary1);
        assertNotNull(hotelReservationList);
        assertEquals(1, hotelReservationList.size());
        assertEquals(hotelBookingNo1, hotelReservationList.get(0).getBookingNumber());
        assertEquals(statusUnconfirmed, hotelReservationList.get(0).getStatus());
        
        
        //Add a second flight to itinerary
        System.out.println("Adding second flight..");
        flightReservationList = bookingServiceBPEL.addFlight(flightBookingNo2, itinerary1);
        assertNotNull(flightReservationList);
        assertEquals(2, flightReservationList.size());
        
        //Add a third flight to itinerary
        System.out.println("Adding third flight..");
        flightReservationList = bookingServiceBPEL.addFlight(flightBookingNo3, itinerary1);
        assertNotNull(flightReservationList);
        assertEquals(3, flightReservationList.size());
        
        //Add a second hotel to itinerary
        System.out.println("Adding second hotel..");
        hotelReservationList = bookingServiceBPEL.addHotel(hotelBookingNo2, itinerary1);
        assertNotNull(hotelReservationList);
        assertEquals(2, hotelReservationList.size());
        
        //Get itinerary
        System.out.println("Getting Itinerary..");
        
        
        //Assert all flights added
        System.out.println("Asserting all flights added..");
        List<FlightReservation> fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertEquals(3, fReservationList.size());
        
        
        //Assert all hotels added
        System.out.println("Asserting all hotels added..");
        List<HotelReservation> hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertEquals(2, hReservationList.size());
        
        
        //Assert all flights and hotels have status unconfirmed
        System.out.println("Asserting all flights and hotels have status unconfirmed..");
        HashMap hm = new HashMap();
                
        for(HotelReservation reservation : hReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
            hm.put(reservation.getBookingNumber(), reservation);
        }
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
            hm.put(reservation.getBookingNumber(), reservation);
        }
        
        //Assert all bookingnumbers match the booked flights and hotels
        System.out.println("Asserting all bookingnumbers match the booked flights and hotels...");
        
        assertTrue(hm.containsKey(flightBookingNo1));
        assertTrue(hm.containsKey(flightBookingNo2));
        assertTrue(hm.containsKey(flightBookingNo3));
        assertTrue(hm.containsKey(hotelBookingNo1));
        assertTrue(hm.containsKey(hotelBookingNo2));
        
        //Book itinerary
        System.out.println("Booking itinerary..");
        bookingServiceBPEL.bookItinerary(itinerary1, CreditCardName1, CreditCardNumber1, CreditCardMonth1, CreditCardYear1);
        List<HotelReservation> bookItineraryReply =  bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertNotNull(bookItineraryReply);
        assertFalse(bookItineraryReply.isEmpty());
        
        //Assert all statuses are confirmed
        System.out.println("Asserting all statuses are confirmed...");
        
        
        hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertNotNull(hReservationList);
        assertFalse(hReservationList.isEmpty());
        
        for(HotelReservation reservation : hReservationList){
            assertEquals(statusConfirmed, reservation.getStatus());
            
        }
        
        fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(fReservationList);
        assertFalse(fReservationList.isEmpty());
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusConfirmed, reservation.getStatus());
            
        }
        
    }
    
    
    /*P2 (cancel planning) Plan a trip by first getting a list of flights and then adding a flight to the itinerary.
    Then cancel planning.*/
    
    @Test
    public void testP2_BPEL() {
        BookingServiceBPELWrapper bookingServiceBPEL = new BookingServiceBPELWrapper();

        //Create itinerary
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        itinerary1 = Integer.toString(random);
        
        System.out.println("Creating Itinerary number " + itinerary1);
        String itineraryString = bookingServiceBPEL.createItinerary(itinerary1);
        assertNotNull(itineraryString);
        assertNotSame("", itineraryString);

        //Get list of flights
        System.out.println("Finding flights..");
        List<FlightInfo> flightBookingList = bookingServiceBPEL.getFlights(city1, city2, fromDateFlight1, itinerary1);
        assertFalse(flightBookingList.isEmpty() );

        //Add flight to itinerary
        System.out.println("Adding first flight..");
        List<FlightReservation> flightReservationList = bookingServiceBPEL.addFlight(flightBookingNo1, itinerary1);
        assertNotNull(flightReservationList);
        assertEquals(1, flightReservationList.size());

        //Assert all flights added
        System.out.println("Asserting all flights added..");
        List<FlightReservation> fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertEquals(1, fReservationList.size());
        
        //Book itinerary
        System.out.println("Booking itinerary..");
        bookingServiceBPEL.bookItinerary(itinerary1, CreditCardName1, CreditCardNumber1, CreditCardMonth1, CreditCardYear1);
        List<FlightReservation> bookItineraryReply =  bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(bookItineraryReply);
        assertFalse(bookItineraryReply.isEmpty());
        
        //Assert all statuses are confirmed
        System.out.println("Asserting all statuses are confirmed...");
        
        fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(fReservationList);
        assertFalse(fReservationList.isEmpty());
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusConfirmed, reservation.getStatus());
            
        }
        
        //Cancel Planning
        System.out.println("Cancelling planning..");
        bookingServiceBPEL.cancelItinerary(itinerary1);
        bookItineraryReply =  bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(bookItineraryReply);
        assertFalse(bookItineraryReply.isEmpty());

        //Assert status of flight is cancelled...
        System.out.println("Asserting status of flight is cancelled..");
        System.out.println("Asserting booking number of flight is correct..");
        fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(fReservationList);
        assertFalse(fReservationList.isEmpty());
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusCancelled, reservation.getStatus());
            assertEquals(flightBookingNo1, reservation.getBookingNumber());
        }
        
    }

}
