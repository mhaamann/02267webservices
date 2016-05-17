/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingService.Rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.FlightContainer;
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
        
        MultivaluedMap<String, String> queryParametersForFlight_1 = new MultivaluedMapImpl();
        queryParametersForFlight_1.add("origin", city1);
        queryParametersForFlight_1.add("destination", city2);
        queryParametersForFlight_1.add("departureDate", fromDate1);
        FlightContainer flightContainer = flightsResource.queryParams(queryParametersForFlight_1).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> queryParametersForFlight_1_1 = new MultivaluedMapImpl();
        queryParametersForFlight_1_1.add("bookintNumber", flightContainer.flight.get(0).getBookingNumber());
        queryParametersForFlight_1_1.add("itineraryId", itinerary1);
        flightsResource.queryParams(queryParametersForFlight_1_1).post();
        /*
        MultivaluedMap<String, String> queryParametersForHotel_1 = new MultivaluedMapImpl();
        queryParametersForHotel_1.add("city", city1);
        queryParametersForHotel_1.add("arrivalDate", fromDate1);
        queryParametersForHotel_1.add("departureDate", toDate1);
        BookingContainer bookingContainer = hotelsResource.queryParams(queryParametersForHotel_1).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> queryParametersForHotel_1_1 = new MultivaluedMapImpl();
        queryParametersForHotel_1_1.add("bookintNumber", bookingContainer.hotel.get(0).getBookingNumber());
        queryParametersForHotel_1_1.add("itineraryId", itinerary1);
        hotelsResource.queryParams(queryParametersForHotel_1_1).post();
        */
        MultivaluedMap<String, String> queryParametersForFlight_2 = new MultivaluedMapImpl();
        queryParametersForFlight_2.add("origin", city1);
        queryParametersForFlight_2.add("destination", city2);
        queryParametersForFlight_2.add("departureDate", fromDate1);
        FlightContainer flightContainer2 = flightsResource.queryParams(queryParametersForFlight_2).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> queryParametersForFlight_2_1 = new MultivaluedMapImpl();
        queryParametersForFlight_2_1.add("bookintNumber", flightContainer2.flight.get(0).getBookingNumber());
        queryParametersForFlight_2_1.add("itineraryId", itinerary1);
        flightsResource.queryParams(queryParametersForFlight_2_1).post();
       
        MultivaluedMap<String, String> queryParametersForFlight_3 = new MultivaluedMapImpl();
        queryParametersForFlight_3.add("origin", city1);
        queryParametersForFlight_3.add("destination", city2);
        queryParametersForFlight_3.add("departureDate", fromDate1);
        FlightContainer flightContainer3 = flightsResource.queryParams(queryParametersForFlight_3).get(new GenericType<FlightContainer>() {});
        
        MultivaluedMap<String,String> queryParametersForFlight_3_1 = new MultivaluedMapImpl();
        queryParametersForFlight_3_1.add("bookintNumber", flightContainer3.flight.get(0).getBookingNumber());
        queryParametersForFlight_3_1.add("itineraryId", itinerary1);
        flightsResource.queryParams(queryParametersForFlight_3_1).post();
        /*
        MultivaluedMap<String, String> queryParametersForHotel_2 = new MultivaluedMapImpl();
        queryParametersForHotel_2.add("city", city1);
        queryParametersForHotel_2.add("arrivalDate", fromDate1);
        queryParametersForHotel_2.add("departureDate", toDate1);
        BookingContainer bookingContainer2 = hotelsResource.queryParams(queryParametersForHotel_2).get(new GenericType<BookingContainer>() {});
        
        MultivaluedMap<String,String> queryParametersForHotel_2_1 = new MultivaluedMapImpl();
        queryParametersForHotel_2_1.add("bookintNumber", bookingContainer2.hotel.get(0).getBookingNumber());
        queryParametersForHotel_2_1.add("itineraryId", itinerary1);
        hotelsResource.queryParams(queryParametersForHotel_2_1).post();
        */
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
