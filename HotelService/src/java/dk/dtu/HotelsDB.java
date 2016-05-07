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
    private Integer currentBookingNumber = 1;

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
     * Creates a unconfirmed booking reservation and saves it on the hotel object.
     * @param hotel
     * @param startDate
     * @param endDate
     * @return 
     */
    private Booking addReservation(Hotel hotel, Date startDate, Date endDate) {
        // Adds an unconfirmed booking.
        Booking booking = new Booking(startDate, endDate, Integer.toString(this.currentBookingNumber), hotel.pricePerDay, hotel.creditcardGuarantee);
        hotel.bookings.add(booking);
        this.currentBookingNumber++;
        return booking;
    }
    
    /**
     * Returns a list of rooms that does not have a booking that conflicts with the given date.
     * @param city The city where the hotel is located.
     * @param arrivalDate
     * @param departureDate
     * @return 
     */
    public ArrayList<Booking> getHotels(String city, Date arrivalDate, Date departureDate) {
        ArrayList<Booking> reservedBookings = new ArrayList<Booking>();

        for (Hotel hotel : rooms) {
            if (hotel.city.toLowerCase().equals(city.toLowerCase())) {
                if (hotel.bookings.isEmpty()) {
                    reservedBookings.add(addReservation(hotel, arrivalDate, departureDate));
                }
                for (Booking booking : hotel.bookings) {
                    if (booking.arrivalDate.before(departureDate) && booking.departureDate.after(arrivalDate)) {
                        // Conflict, do nothing.
                    } else {
                        reservedBookings.add(addReservation(hotel, arrivalDate, departureDate));
                    }
                }
            }
        }
        return reservedBookings;
    }

    boolean bookHotel(String bookingNumber, int year, int month, int number, String name) throws Exception {
        for (Hotel hotel : rooms) {
            for (Booking booking : hotel.bookings) {
                if (booking.bookingNumber.equals(bookingNumber)) {
                    // Booking exists. 
                    // Charge the creditcard
                    if (hotel.creditcardGuarantee) {
                        // TODO: With creditcard guarantee.
                        booking.status = "confirmed";
                        return true;
                    } else {
                        // TODO: No creditcard guarantee.
                        throw new Exception("Bank error");
                    }
                }
            }
        }
        throw new Exception("Booking did not exists");
    }

    boolean cancelHotel(String bookingNumber) throws Exception {
       for (Hotel hotel : rooms) {
            for (Booking booking : hotel.bookings) {
                if (booking.bookingNumber.equals(bookingNumber)) {
                    // Booking exists. 
                    // Cancel hotel.
                    booking.status = "cancelled";
                    return true;
                }
            }
        }
       throw new Exception("Cancellation failed");
    }
}
