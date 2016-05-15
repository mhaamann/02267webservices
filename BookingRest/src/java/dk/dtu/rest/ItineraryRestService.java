/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import dk.dtu.Exception_Exception;
import dk.dtu.db.Itinerary;
import dk.dtu.db.ItineraryDB;
import static dk.dtu.rest.AirlineRestService.flightContainer;
import dk.dtu.xml.FlightContainer;
import dk.dtu.xml.ItineraryContainer;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 *
 * @author mhaamann
 */
@Path("itinerary")
public class ItineraryRestService {
    
    static ItineraryContainer itineraryContainer = new ItineraryContainer();

    @GET @Path("/{itineraryId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response listItinerary(@PathParam("itineraryId") String itineraryId) {
        if (itineraryId.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Itinerary list = ItineraryDB.getItineraryDB().getItinerary(itineraryId);
        itineraryContainer.set(list);
        return Response.status(Response.Status.OK).entity(itineraryContainer).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String createItinerary() {
        return ItineraryDB.getItineraryDB().createItinerary();
    }
    
    @PUT @Path("/{itineraryId}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response bookItinerary(
            @PathParam("itineraryId") String itineraryId,
            @FormParam("year") int year,
            @FormParam("month") int month,
            @FormParam("number") String number,
            @FormParam("name") String name) {
        try {
            ItineraryDB.getItineraryDB().bookItinerary(itineraryId, year, month, number, name);
        } catch (Exception_Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
