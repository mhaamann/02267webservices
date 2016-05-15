/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.db;

import dk.dtu.Exception_Exception;
import dk.dtu.airline.CreditCardFaultMessage;
import dk.dtu.external.AirlineService;
import dk.dtu.external.HotelService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void addFlight(String bookingNumber, String itineraryId) {
        Itinerary itinerary = getItinerary(itineraryId);
        if (itinerary.getState() == Itinerary.PlanningState) {
            getItinerary(itineraryId).flights.add(new Flight(bookingNumber));
        }

    }

    public void addHotel(String bookingNumber, String itineraryId) {
        Itinerary itinerary = getItinerary(itineraryId);
        if (itinerary.getState() == Itinerary.PlanningState) {
            getItinerary(itineraryId).hotels.add(new Hotel(bookingNumber));
        }
    }

    public String createItinerary() {
        lastItineraryId++;
        itineraries.add(new Itinerary(lastItineraryId));
        return Integer.toString(lastItineraryId);
    }

    public boolean bookItinerary(String itineraryId, int year, int month, String number, String name) {

        boolean itinerarySuccess = true;

        Itinerary itinerary = getItinerary(itineraryId);
        if (itinerary.getState() == Itinerary.PlanningState) {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Booking...");
            for (Flight flight : itinerary.flights) {
                boolean responseF = AirlineService.bookFlight(flight.bookingNumber, year, month, number, name);
                Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Attempted booking on " + flight.bookingNumber, responseF);
                if (responseF) {
                    flight.status = "confirmed";
                }
            }
            for (Hotel hotel : itinerary.hotels) {
                boolean responseH;
                try {
                    responseH = HotelService.bookHotel(hotel.bookingNumber, year, month, number, name);
                    Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Attempted booking on " + hotel.bookingNumber, responseH);
                    if (responseH) {
                        hotel.status = "confirmed";
                    }
                } catch (Exception_Exception ex) {
                    Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Booking exception", ex);
                    itinerarySuccess = false;
                }
            }
            itinerary.setState(Itinerary.BookedCompleteState);
        }

        // Itinerary failed, cancel all the confimed bookings.
        if (!itinerarySuccess) {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Booking failed");
            cancelItinerary(itineraryId);
            return itinerarySuccess;
        }
        Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Booking complete");
        return itinerarySuccess;
    }

    public void cancelFlight(Flight flight) {
        if (!flight.status.equals("confirmed")) {
            return;
        }
        boolean responseF;
        try {
            responseF = AirlineService.cancelFlight(flight.bookingNumber);
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Attempted cancel on " + flight.bookingNumber, responseF);
            if (responseF) {
                flight.status = "cancelled";
            }
        } catch (CreditCardFaultMessage ex) {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelHotel(Hotel hotel) {
        if (!hotel.status.equals("confirmed")) {
            return;
        }
        boolean responseH;
        try {
            responseH = HotelService.cancelHotel(hotel.bookingNumber);
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Attempted cancel on " + hotel.bookingNumber, responseH);
            if (responseH) {
                hotel.status = "cancelled";
            }
        } catch (Exception_Exception ex) {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelItinerary(String itineraryId) {
        Itinerary itinerary = getItinerary(itineraryId);
        if (itinerary.getState() == Itinerary.BookedCompleteState) {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "Canceling...");
            for (Flight flight : itinerary.flights) {
                cancelFlight(flight);
            }
            for (Hotel hotel : itinerary.hotels) {
                cancelHotel(hotel);
            }
            itinerary.setState(Itinerary.CanceledState);
        } else {
            Logger.getLogger(ItineraryDB.class.getName()).log(Level.SEVERE, "State was wrong");
        }
    }

}
