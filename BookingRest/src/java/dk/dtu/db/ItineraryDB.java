/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.db;

import java.util.ArrayList;

/**
 *
 * @author mhaamann
 */
public class ItineraryDB {
    private static final ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
    private static ItineraryDB singletonItineraryDB = null;
    private static int lastItineraryId = 1000;
    
    private ItineraryDB() {
        
    }
    public static ItineraryDB getItineraryDB() {
        if (singletonItineraryDB == null) {
            singletonItineraryDB = new ItineraryDB();
        }
        return singletonItineraryDB;
    }
    
    public Itinerary getItinerary(String itineraryId) {
        
        for (Itinerary itinerary : itineraries) {
            if (itinerary.itineraryId == Integer.parseInt(itineraryId)) {
                return itinerary;
            }
        }
        return null;
    }
    
    public String createItinerary() {
        lastItineraryId++;
        itineraries.add(new Itinerary(lastItineraryId));
        return Integer.toString(lastItineraryId);
    }
}
