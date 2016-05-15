/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DFS
 */
public class BookingServiceRESTtest {
    
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
        
    
    
    public BookingServiceRESTtest() {
    }

    @Test
    public void testP1() {
        itinerary1 = itineraryResource.post(String.class);
        int itineraryId = Integer.parseInt(itinerary1);
        assertTrue(itineraryId > 0);
        
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("origin", city1);
        params.add("destination", city2);
        params.add("departureDate", fromDate1);
        String flightContainer = flightsResource.queryParams(params).get(String.class);
        //flightsResource.post(flightContainer.bookingNumber, itinerary1);
        
        System.err.println(flightContainer);
        
        MultivaluedMap<String, String> params5 = new MultivaluedMapImpl();
        params.add("city", city1);
        params.add("arrivalDate", fromDate1);
        params.add("departureDate", toDate1);
        String bookingContainer = hotelsResource.queryParams(params).get(String.class);
        //hotelsResource.post(bookingContainer.bookingNumber, itinerary1);
        
        System.err.println(bookingContainer);
        
        MultivaluedMap<String, String> params4 = new MultivaluedMapImpl();
        params.add("origin", city1);
        params.add("destination", city2);
        params.add("departureDate", fromDate1);
        String flightContainer2 = flightsResource.queryParams(params).get(String.class);
        //flightsResource.post(flightContainer2.bookingNumber, itinerary1);
        
        System.err.println(flightContainer2);
        
        MultivaluedMap<String, String> params3 = new MultivaluedMapImpl();
        params.add("origin", city1);
        params.add("destination", city2);
        params.add("departureDate", fromDate1);
        String flightContainer3 = flightsResource.queryParams(params).get(String.class);
        //flightsResource.post(flightContainer3.bookingNumber, itinerary1);
        
        System.err.println(flightContainer3);
        
        MultivaluedMap<String, String> params6 = new MultivaluedMapImpl();
        params.add("city", city1);
        params.add("arrivalDate", fromDate1);
        params.add("departureDate", toDate1);
        String bookingContainer2 = hotelsResource.queryParams(params).get(String.class);
        //hotelsResource.post(bookingContainer2.bookingNumber, itinerary1);
        
        System.err.println(bookingContainer2);
    }
    /*
    @Test
    public void testP2() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }
    
    @Test
    public void testB() {
        String result = res.get(String.class);
        assertEquals(result, "DTU");
    }*/
    
}
