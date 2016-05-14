/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import dk.dtu.db.ItineraryDB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



/**
 *
 * @author mhaamann
 */
@Path("itinerary")
public class ItineraryRestService {
    

    @GET @Path("/{itineraryId}")
    //@Produces("application/xml")
    public String listItinerary(@PathParam("itineraryId") String itineraryId) {
        if (itineraryId.isEmpty()) {
            return "No itineraryId was specified";
        }
        return Integer.toString(ItineraryDB.getItineraryDB().getItinerary(itineraryId).itineraryId);
    }
    
    @POST
    //@Produces("application/xml")
    public String createItinerary() {
        return ItineraryDB.getItineraryDB().createItinerary();
    }
}
