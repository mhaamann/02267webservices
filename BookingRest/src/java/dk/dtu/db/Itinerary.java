/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.db;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mhaamann
 */
public class Itinerary {
    static int PlanningState = 0;
    static int BookedCompleteState = 1;

    public ArrayList<Flight> flights = new ArrayList<Flight>();
    public ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    private int state;

    public int itineraryId;

    public Itinerary(int itineraryId) {
        this.state = 0;
        this.itineraryId = itineraryId;
    }

    @Override
    public boolean equals(Object o) {
        return this.itineraryId == ((Itinerary) o).itineraryId;
    }

    protected int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
