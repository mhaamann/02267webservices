/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import dk.dtu.db.Itinerary;
import dk.dtu.db.ItineraryDB;
import static dk.dtu.rest.AirlineRestService.flightContainer;
import dk.dtu.xml.FlightContainer;
import dk.dtu.xml.ItineraryContainer;
import static java.util.Collections.list;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



/**
 *
 * @author mhaamann
 */
@Path("itinerary")
public class ItineraryRestService {
    
    static ItineraryContainer itineraryContainer = new ItineraryContainer();

    @GET @Path("/{itineraryId}")
    @Produces("application/xml")
    public Response listItinerary(@PathParam("itineraryId") String itineraryId) {
        if (itineraryId.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Itinerary list = ItineraryDB.getItineraryDB().getItinerary(itineraryId);
        itineraryContainer.set(list);
        return Response.status(Response.Status.OK).entity(itineraryContainer).build();
    }
    
    @POST
    @Produces("application/xml")
    public String createItinerary() {
        return ItineraryDB.getItineraryDB().createItinerary();
    }
}
