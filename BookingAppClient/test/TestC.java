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
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jens
 */
public class TestC {
    
    String city1 = "Copenhagen";
    String city2 = "Berlin";
    String fromDateHotel1 = "2016-01-01";
    String toDateHotel1 = "2016-01-30";
    String fromDateFlight1 = "2016-01-01";
    String toDateFlight1 = "2016-01-10";
    String itinerary1 = "42";
    String flightBookingNo1 = "B12341";
    String flightBookingNo2 = "B12342";
    String hotelBookingNo1 = "7";
    String statusUnconfirmed = "unconfirmed";
    String CreditCardName1 = "Anne Strandberg";
    String CreditCardNumber1 = "50408816";
    BigInteger CreditCardMonth1 = BigInteger.valueOf(5);
    BigInteger CreditCardYear1 = BigInteger.valueOf(9);
    String statusConfirmed = "confirmed";
    String statusCancelled = "cancelled";
    
    /*C1 (cancel booking) Create an itinerary with three bookings (mixed flights and hotels) and book it.
    Get the itinerary and make sure that the booking status is confirmed for each entry. Cancel the
    trip and check that now the booking status is cancelled for all bookings of the itinerary.*/
    
    @Test
    public void testC1() {
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
        System.out.println(flightBookingList);
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
        
        //Add hotel to itinerary
        //Get bookingnumber from returned hotels
        System.out.println("Adding first hotel..");
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
        
        //Get itinerary
        System.out.println("Getting Itinerary..");
         
        //Assert all flights added
        System.out.println("Asserting all flights added..");
        List<FlightReservation> fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertEquals(2, fReservationList.size());
        
        
        //Assert all hotels added
        System.out.println("Asserting all hotels added..");
        List<HotelReservation> hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertEquals(1, hReservationList.size());
        
        
        //Assert all flights and hotels have status unconfirmed
        System.out.println("Asserting all flights and hotels have status unconfirmed..");
                
        for(HotelReservation reservation : hReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
        }
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
        }
        
        //Book itinerary
        System.out.println("Booking itinerary..");
        List<HotelReservation> bookItineraryReply =  bookingServiceBPEL.bookItinerary(itinerary1, CreditCardName1, CreditCardNumber1, CreditCardMonth1, CreditCardYear1);
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
        
        //Cancel booking
        List<HotelReservation> cancelItineraryReply = bookingServiceBPEL.cancelItinerary(itinerary1);
        assertNotNull(cancelItineraryReply);
        assertFalse(cancelItineraryReply.isEmpty());
        
        //Assert all entries have status cancelled
        System.out.println("Asserting all statuses are cancelled...");
        
        hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertNotNull(hReservationList);
        assertFalse(hReservationList.isEmpty());
        
        for(HotelReservation reservation : hReservationList){
            assertEquals(statusCancelled, reservation.getStatus());
            
        }
        
        fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(fReservationList);
        assertFalse(fReservationList.isEmpty());
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusCancelled, reservation.getStatus());
            
        }
        
    }
    
    /*C2 (cancelling fails) Create an itinerary with three bookings and book it. Make sure that the booking
    status is confirmed for each entry. During cancelling of the trip, the cancellation of the second
    booking should fail. Check that the cancelling resulted in an error condition (e.g. value of status
    variable, exception, HTTP status code). Get the itinerary and check that the returned itinerary
    has cancelled as the first and third booking and confirmed for the second booking.*/
    
    @Test
    public void testC2() {
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
        System.out.println(flightBookingList);
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
        
        //Add hotel to itinerary
        //Get bookingnumber from returned hotels
        System.out.println("Adding first hotel..");
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
        
        //Get itinerary
        System.out.println("Getting Itinerary..");
         
        //Assert all flights added
        System.out.println("Asserting all flights added..");
        List<FlightReservation> fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertEquals(2, fReservationList.size());
        
        
        //Assert all hotels added
        System.out.println("Asserting all hotels added..");
        List<HotelReservation> hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertEquals(1, hReservationList.size());
        
        
        //Assert all flights and hotels have status unconfirmed
        System.out.println("Asserting all flights and hotels have status unconfirmed..");
                
        for(HotelReservation reservation : hReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
        }
        
        for (FlightReservation reservation : fReservationList){
            assertEquals(statusUnconfirmed, reservation.getStatus());
        }
        
        //Book itinerary
        System.out.println("Booking itinerary..");
        List<HotelReservation> bookItineraryReply =  bookingServiceBPEL.bookItinerary(itinerary1, CreditCardName1, CreditCardNumber1, CreditCardMonth1, CreditCardYear1);
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
        
        //Cancel booking
        List<HotelReservation> cancelItineraryReply = bookingServiceBPEL.cancelItinerary(itinerary1);
        assertNotNull(cancelItineraryReply);
        assertFalse(cancelItineraryReply.isEmpty());
        
        
        //TODO: Second trip cancelling fails - hardcode in SOAP-service?

        //TODO: Assert error status from SOAP-service?
        
        
        HashMap hm = new HashMap();
        
        hReservationList = bookingServiceBPEL.getHotelItineraryList(itinerary1);
        assertNotNull(hReservationList);
        assertFalse(hReservationList.isEmpty());
        
        for(HotelReservation reservation : hReservationList){
            hm.put(reservation.getBookingNumber(), reservation);
        }
        
        
        fReservationList = bookingServiceBPEL.getFlightItineraryList(itinerary1);
        assertNotNull(fReservationList);
        assertFalse(fReservationList.isEmpty());
        
        for (FlightReservation reservation : fReservationList){
            hm.put(reservation.getBookingNumber(), reservation);
        }
        
        //Assert all bookingnumbers match the booked flights and hotels
        System.out.println("Asserting all bookingnumbers match the booked flights and hotels...");
        
        assertTrue(hm.containsKey(flightBookingNo1));
        assertTrue(hm.containsKey(flightBookingNo2));
        assertTrue(hm.containsKey(hotelBookingNo1));
        
        //Asserting first and third booking are cancelled, and second has status confirmed
        System.out.println("Asserting first and third booking are cancelled, and second has status confirmed...");
        
        FlightReservation fReservation1 = (FlightReservation) hm.get(flightBookingNo1);
        assertNotNull(fReservation1);
        assertEquals(statusCancelled, fReservation1.getStatus());
        
        FlightReservation fReservation2 = (FlightReservation) hm.get(flightBookingNo2);
        assertNotNull(fReservation2);
        assertEquals(statusConfirmed,fReservation2.getStatus());
        
        HotelReservation hReservation1 = (HotelReservation) hm.get(hotelBookingNo1);
        assertNotNull(hReservation1);
        assertEquals(statusCancelled, hReservation1.getStatus());
    }
}
