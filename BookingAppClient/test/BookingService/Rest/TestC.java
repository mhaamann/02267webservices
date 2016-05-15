/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.FlightContainer;
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
        params.add("origin", "Copenhagen");
        params.add("destination", "Berlin");
        params.add("departureDate", "2016-01-01");
        
        FlightContainer list = flightsResource.queryParams(params).get(new GenericType<FlightContainer>() {});

        // Prepare Add hotel query params.
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("itineraryId", "val1");
        queryParams.add("bookingNumber", "val2");

        // Add hotel.
        ClientResponse response = hotelsResource.queryParams(queryParams).post(ClientResponse.class);

    }
    
    
}
