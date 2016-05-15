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
import dk.dtu.xml.Flight;
import dk.dtu.xml.FlightContainer;
import dk.dtu.xml.ItineraryContainer;
import dk.dtu.xml.Hotel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
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
        System.out.print(itineraryId);
        
        // Prepare Get hotels query params.
        MultivaluedMap<String, String> hotelQueryParams = new MultivaluedMapImpl();
        hotelQueryParams.add("city", "Copenhagen");
        hotelQueryParams.add("arrivalDate", "2016-01-01");
        hotelQueryParams.add("departureDate", "2016-01-5");
        BookingContainer hotels = hotelsResource.queryParams(hotelQueryParams).get(new GenericType<BookingContainer>() {});
        
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
        queryParamsFlights.add("origin", "Copenhagen");
        queryParamsFlights.add("destination", "Berlin");
        queryParamsFlights.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {});

        for (FlightInfo flight : flights.flight) {
             // Prepare Add flight query params.
            MultivaluedMap queryParamsAddFlight = new MultivaluedMapImpl();
            queryParamsAddFlight.add("itineraryId", itineraryId);
            queryParamsAddFlight.add("bookingNumber", flight.getBookingNumber());
            // Add flight.
            ClientResponse response2 = flightsResource.queryParams(queryParamsAddFlight).post(ClientResponse.class);
        }
        
        
        // Fetch list itinerary.
        ItineraryContainer list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        System.out.print("Done");
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("unconfirmed", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
        
        // Book.
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "Anne Strandberg");
        formData.add("number", "50408816");
        formData.add("year", "9");
        formData.add("month", "5");
        String response = itineraryResource.path(itineraryId).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
        
        // Fetch list itinerary.
        list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("confirmed", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("confirmed", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
        
        // Cancel.
        response = itineraryResource.path(itineraryId).delete(String.class);
        
        // Fetch list itinerary.
        list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("cancelled", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("cancelled", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
    }
    
    
    @Test
    public void testC2() {
        
        // Create itinerary Id.
        String itineraryId = itineraryResource.post(String.class);
        System.out.print(itineraryId);
        
        // Prepare Get hotels query params.
        MultivaluedMap<String, String> hotelQueryParams = new MultivaluedMapImpl();
        hotelQueryParams.add("city", "Copenhagen");
        hotelQueryParams.add("arrivalDate", "2016-01-01");
        hotelQueryParams.add("departureDate", "2016-01-5");
        BookingContainer hotels = hotelsResource.queryParams(hotelQueryParams).get(new GenericType<BookingContainer>() {});
        
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
        queryParamsFlights.add("origin", "Copenhagen");
        queryParamsFlights.add("destination", "Berlin");
        queryParamsFlights.add("departureDate", "2016-01-01");
        FlightContainer flights = flightsResource.queryParams(queryParamsFlights).get(new GenericType<FlightContainer>() {});

        for (FlightInfo flight : flights.flight) {
             // Prepare Add flight query params.
            MultivaluedMap queryParamsAddFlight = new MultivaluedMapImpl();
            queryParamsAddFlight.add("itineraryId", itineraryId);
            queryParamsAddFlight.add("bookingNumber", flight.getBookingNumber());
            // Add flight.
            ClientResponse response2 = flightsResource.queryParams(queryParamsAddFlight).post(ClientResponse.class);
        }
        
        
        // Fetch list itinerary.
        ItineraryContainer list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        System.out.print("Done");
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("unconfirmed", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("unconfirmed", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
        
        // Book.
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("name", "Anne Strandberg");
        formData.add("number", "50408816");
        formData.add("year", "9");
        formData.add("month", "5");
        String response = itineraryResource.path(itineraryId).type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
        
        // Fetch list itinerary.
        list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("confirmed", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("confirmed", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
        
        // Cancel.
        response = itineraryResource.path(itineraryId).delete(String.class);
        
        // Fetch list itinerary.
        list = itineraryResource.path(itineraryId).get(new GenericType<ItineraryContainer>() {});
        
        for (Hotel hotel : list.itinerary.hotels) {
            assertEquals("cancelled", hotel.status);
        }
        for (Flight flight : list.itinerary.flights) {
            assertEquals("cancelled", flight.status);
        }
        assertEquals(1, list.itinerary.flights.size());
        assertEquals(2, list.itinerary.hotels.size());
    }
    
    
}
