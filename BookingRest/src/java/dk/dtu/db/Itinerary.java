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
public class Itinerary {
    
    ArrayList<Flight> flights = new ArrayList<Flight>();
    ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    
    public int itineraryId;
            
    public Itinerary(int itineraryId) {
       this.itineraryId = itineraryId;
    }
    
    @Override
    public boolean equals(Object o) { 
        return this.itineraryId == ((Itinerary) o).itineraryId;
    }
    
}
