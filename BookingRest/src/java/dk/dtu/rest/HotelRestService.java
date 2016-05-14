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
 * @author mhaamann
 */
@Path("hotels")
public class HotelRestService {
    
    static BookingContainer bookingContainer = new BookingContainer();

    @POST
    //@Produces("application/xml")
    public String addHotel() {
        return ItineraryDB.getItineraryDB().createItinerary();
    }

    @GET
    @Produces("application/xml")
    public Response getHotels(
            @QueryParam("city") String city,
            @QueryParam("arrivalDate") String arrivalDate,
            @QueryParam("departureDate") String departureDate,
            @QueryParam("itineraryId") String itineraryId) throws ParseException_Exception {
        List<Booking> hotels = HotelService.getHotels(city, arrivalDate, departureDate);

        /*List<BookingContainer> finalList = new ArrayList<>();
        
        for (Booking booking : hotels) {
            finalList.add(new BookingContainer(booking.getCity(), booking.getBookingNumber(), booking.getTotalPrice(), booking.getArrivalDate(), booking.getDepartureDate(), booking.getStatus()));
        }
        return Response.status(Response.Status.OK).entity(finalList).build();*/
        bookingContainer.set(hotels);
	return  Response.status(Response.Status.OK).entity(bookingContainer).build();
    }

}
