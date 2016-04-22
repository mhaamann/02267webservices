/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mhaamann
 */
public class HotelsDB {

    ArrayList<Hotel> rooms = new ArrayList<Hotel>();

    public HotelsDB() {

        // Each hotel represents a Room. The second parameter represents the hotel name.
        rooms.add(new Hotel("Copenhagen", "CabIn", 60000));
        rooms.add(new Hotel("Copenhagen", "CabIn", 60000));
        rooms.add(new Hotel("Copenhagen", "CabIn", 60000));

        rooms.add(new Hotel("Amsterdam", "CabIn", 55000));
        rooms.add(new Hotel("Amsterdam", "CabIn", 55000));
        rooms.add(new Hotel("Amsterdam", "CabIn", 55000));

        rooms.add(new Hotel("Berlin", "CabIn", 40000));
        rooms.add(new Hotel("Berlin", "CabIn", 40000));
        rooms.add(new Hotel("Berlin", "CabIn", 40000));
        rooms.add(new Hotel("Berlin", "CabIn", 40000));
        rooms.add(new Hotel("Berlin", "CabIn", 40000));

    }
    
    /**
     * Returns a list of rooms that does not have a booking that conflicts with the given date.
     * @param city The city where the hotel is located.
     * @param arrivalDate
     * @param departureDate
     * @return 
     */
    public ArrayList<Hotel> getHotels(String city, Date arrivalDate, Date departureDate) {
        ArrayList<Hotel> availableRooms = new ArrayList<Hotel>();

        for (Hotel room : rooms) {
            if (room.city.toLowerCase().equals(city.toLowerCase())) {
                if (room.bookings.isEmpty()) {
                    availableRooms.add(room);
                }
                for (Booking booking : room.bookings) {
                    if (booking.startDate.before(departureDate) && booking.endDate.after(arrivalDate)) {
                        // Conflict
                    } else {
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }

}
