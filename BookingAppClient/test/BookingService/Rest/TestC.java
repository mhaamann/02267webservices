/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.FlightContainer;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author DFS
 */
public class TestC {
    
    String city1 = "Copenhagen";
    String city2 = "Berlin";
    String fromDate1 = "2016-01-01";
    String toDate1 = "2016-01-30";
    String toDateFlight1 = "2016-01-10";
    String itinerary1 = "";
    String bookingNumber1 = "1";
    String bookingNumber2 = "2";
    String flightBooking1 = "B12341";
    String hotelBookingNo1 = "7";
    String hotelBookingNo2 = "12";
    
    Client client = Client.create();
    WebResource itineraryResource = client.resource("http://localhost:8080/BookingRestService/webresources/itinerary");
    WebResource flightsResource = client.resource("http://localhost:8080/BookingRestService/webresources/flights");
    WebResource hotelsResource = client.resource("http://localhost:8080/BookingRestService/webresources/hotels");
    
    public TestC() {
    }

    
    @Test
    public void testC1() {
        
        // Create itinerary Id.
        String itineraryId = itineraryResource.post(String.class);
        
        // Prepare Get hotels query params.
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("city", "Copenhagen");
        params.add("arrivalDate", "2016-01-01");
        params.add("departureDate", "2016-01-5");
        BookingContainer hotels = hotelsResource.queryParams(params).get(new GenericType<BookingContainer>() {});
        
        for (Booking booking : hotels.hotel) {
            if (!booking.isCreditcardGuarantee()) {
               // Prepare Add hotel query params.
                MultivaluedMap queryParamsAddHotel = new MultivaluedMapImpl();
                queryParamsAddHotel.add("itineraryId", itineraryId);
                queryParamsAddHotel.add("bookingNumber", booking.getBookingNumber());
                // Add hotel.
                ClientResponse response = hotelsResource.queryParams(queryParamsAddHotel).post(ClientResponse.class);
            }
        }
  
        // Prepare Get flights query params.
        MultivaluedMap<String, String> queryParamsFlights = new MultivaluedMapImpl();
        params.add("origin", "Copenhagen");
        params.add("destination", "Berlin");
        params.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {});

        for (FlightInfo flight : flights.flight) {
             // Prepare Add flight query params.
            MultivaluedMap queryParamsAddFlight = new MultivaluedMapImpl();
            queryParamsAddFlight.add("itineraryId", itineraryId);
            queryParamsAddFlight.add("bookingNumber", flight.getBookingNumber());
            // Add flight.
            ClientResponse response2 = hotelsResource.queryParams(queryParamsAddFlight).post(ClientResponse.class);
        }
        
        // List itinerary
        //itineraryResource.get()
       

    }
    
    
}
