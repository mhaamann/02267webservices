/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("TG")
public class TravelGoodRestService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TravelGoodRestService
     */
    public TravelGoodRestService() {
    }

    /**
     * Retrieves representation of an instance of dk.dtu.rest.TravelGoodRestService
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        return "DTU";
    }

    /**
     * PUT method for updating or creating an instance of TravelGoodRestService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
//     */
//    @PUT
//    @Consumes("application/xml")
//    public void putXml(String content) {
    //}
}