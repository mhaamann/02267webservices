/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

import ExternalBookingService.FlightList;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;

/**
 *
 * @author mhaamann
 */
public class BookingServiceBPEL {
    
    
    // Itinerary operations - Start
    
    public static HotelList bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.bookItinerary(itineraryId, name, number, month, year);
    }

    public static String createItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary(itineraryId);
    }
    
    public static HotelList listItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.listItinerary(itineraryId);
    }

    public static HotelList cancelItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.cancelItinerary(itineraryId);
    }
    
    // Itinerary operations - End
    
    // Hotel operations - Start

    public static GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure, itineraryId);
    }
    
    public static HotelList addHotel(java.lang.String bookingNumber, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addHotel(bookingNumber, itineraryId);
    }
    
    // Hotel operations - End
    
    // Flight operations - Start

    public static GetFlightsResponse getFlights(java.lang.String origin, java.lang.String destination, java.lang.String startDate, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getFlights(origin, destination, startDate, itineraryId);
    }
    
    public static FlightList addFlight(java.lang.String bookingNumber, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addFlight(bookingNumber, itineraryId);
    }
    
    // Flight operations - End

    

    

   
}
