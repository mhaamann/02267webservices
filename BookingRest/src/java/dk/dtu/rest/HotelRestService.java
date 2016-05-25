/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import dk.dtu.Booking;
import dk.dtu.ParseException_Exception;
import dk.dtu.db.ItineraryDB;
import dk.dtu.external.HotelService;
import dk.dtu.xml.BookingContainer;
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
 * @author Matthias Haamann
 */
@Path("hotels")
public class HotelRestService {
    
    static BookingContainer bookingContainer = new BookingContainer();

    @POST
    @Produces("application/xml")
    public Response addHotel(
        @QueryParam("bookingNumber") String bookingNumber,
        @QueryParam("itineraryId") String itineraryId) {
        ItineraryDB.getItineraryDB().addHotel(bookingNumber, itineraryId);
        return Response.ok().build();
    }

    @GET
    @Produces("application/xml")
    public Response getHotels(
            @QueryParam("city") String city,
            @QueryParam("arrivalDate") String arrivalDate,
            @QueryParam("departureDate") String departureDate) throws ParseException_Exception {
        List<Booking> hotels = HotelService.getHotels(city, arrivalDate, departureDate);
        
        // Assign the hotels to a container object that can be serialized to xml.
        bookingContainer.set(hotels);
	return  Response.status(Response.Status.OK).entity(bookingContainer).build();
    }

}
