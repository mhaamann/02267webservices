/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import dk.dtu.Booking;
import dk.dtu.ParseException_Exception;
import dk.dtu.airline.FlightInfo;
import dk.dtu.db.ItineraryDB;
import dk.dtu.external.AirlineService;
import dk.dtu.external.HotelService;
import dk.dtu.xml.BookingContainer;
import dk.dtu.xml.FlightContainer;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author mhaamann
 */
@Path("flights")
public class AirlineRestService {
    
    static FlightContainer flightContainer = new FlightContainer();

    @POST
    @Produces("application/xml")
    public Response addFlight(
        @QueryParam("bookingNumber") String bookingNumber,
        @QueryParam("itineraryId") String itineraryId) {
        ItineraryDB.getItineraryDB().addFlight(bookingNumber, itineraryId);
        return Response.ok().build();
    }

    @GET
    @Produces("application/xml")
    public Response getFlights(
            @QueryParam("city") String origin,
            @QueryParam("destination") String destination,
            @QueryParam("departureDate") String departureDate) throws ParseException_Exception, dk.dtu.airline.ParseException_Exception {
        List<FlightInfo> flights = AirlineService.getFlights(origin, destination, departureDate);
        
        // Assign the flights to a container object that can be serialized to xml.
        flightContainer.set(flights);
	return Response.status(Response.Status.OK).entity(flightContainer).build();
    }

}
