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
import ExternalBookingService.ItineryList;

/**
 *
 * @author mhaamann
 */
public class BookingServiceBPEL {
    
    
    // Itinerary operations - Start
    
    public HotelList bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.bookItinerary(itineraryId, name, number, month, year);
    }

    public String createItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.createItinerary(itineraryId);
    }
    

    public HotelList cancelItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.cancelItinerary(itineraryId);
    }
    
    public ItineryList listItinerary(java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.listItinerary(itineraryId);
    }
    
    
    // Itinerary operations - End
    
    // Hotel operations - Start

    public GetHotelsResponse getHotels(java.lang.String city, java.lang.String arrival, java.lang.String departure, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getHotels(city, arrival, departure, itineraryId);
    }
    
    public HotelList addHotel(java.lang.String bookingNumber, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addHotel(bookingNumber, itineraryId);
    }
    
    // Hotel operations - End
    
    // Flight operations - Start

    public GetFlightsResponse getFlights(java.lang.String origin, java.lang.String destination, java.lang.String startDate, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.getFlights(origin, destination, startDate, itineraryId);
    }
    
    public FlightList addFlight(java.lang.String bookingNumber, java.lang.String itineraryId) {
        ExternalBookingService.Service1 service = new ExternalBookingService.Service1();
        ExternalBookingService.BookingServicePortType port = service.getBookingServicePortTypeBindingPort();
        return port.addFlight(bookingNumber, itineraryId);
    }
    
    // Flight operations - End

    

    

    

   
}
