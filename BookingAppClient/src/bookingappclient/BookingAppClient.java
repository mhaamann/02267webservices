/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import java.util.Arrays;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.math.BigInteger;

/**
 *
 * @author jens
 */
public class BookingAppClient {
    
    // TODO: BPEL proccess
    // Add airline service, get, add, cancel and book
    // merge lists when calling get itinerary
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting");
        String itineraryId = "16";
        String createItinerary = BookingService.createItinerary(itineraryId);

        System.out.println(createItinerary);
        
        System.out.println("Finding hotels..");
        GetHotelsResponse hotels = BookingService.getHotels("Copenhagen", "2016-01-01", "2016-01-30", itineraryId);
        for (Booking booking : hotels.getReturn()) {
            System.out.println(booking.getCity());
            System.out.println(booking.getTotalPrice());
        }
        
        System.out.println("Finding Flights..");
        GetFlightsResponse flights = BookingService.getFlights("Copenhagen", "Berlin", "2016-01-01", itineraryId);
        for (FlightInfo flight : flights.getReturn()) {
            System.out.println(flight.getOrigin() + " - " + flight.getPrice());
        }
        
        System.out.println("Adding..");
        BookingService.addHotel("7", itineraryId);
        HotelList hotelList = BookingService.addHotel("12", itineraryId);
        for (HotelReservation reservation : hotelList.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        HotelList list = BookingService.listItinerary(itineraryId);
        for (HotelReservation reservation : list.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Booking..");
        HotelList hotelListBooked = BookingService.bookItinerary(itineraryId, "Anne Strandberg", BigInteger.valueOf(50408816), BigInteger.valueOf(5), BigInteger.valueOf(9));
        for (HotelReservation reservation : hotelListBooked.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        HotelList list2 = BookingService.listItinerary(itineraryId);
        for (HotelReservation reservation : list2.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Cancelling..");
        HotelList list3 = BookingService.cancelItinerary(itineraryId);
        for (HotelReservation reservation : list3.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        System.out.println("Listing..");
        HotelList list4 = BookingService.listItinerary(itineraryId);
        for (HotelReservation reservation : list4.getReservation()) {
            System.out.print(reservation.getBookingNumber() + " - Status:");
            System.out.println(reservation.getStatus());
        }
        
        
        
    }

   
    

    
}
