/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mhaamann
 */
public class HotelsDB {

    private ArrayList<Hotel> rooms;
    private Integer currentBookingNumber;
    private BankServiceWrapper bank = new BankServiceWrapper();
    
    public void resetData() {
        this.rooms = new ArrayList<Hotel>();
        this.currentBookingNumber = 1;
        
        // Each hotel represents a Room. The second parameter represents the hotel name.
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 60000));
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 60000));
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 60000));

        this.rooms.add(new Hotel("Amsterdam", "CabIn", 55000));
        this.rooms.add(new Hotel("Amsterdam", "CabIn", 55000));
        this.rooms.add(new Hotel("Amsterdam", "CabIn", 55000));

        this.rooms.add(new Hotel("Berlin", "CabIn", 40000));
        this.rooms.add(new Hotel("Berlin", "CabIn", 40000));
        this.rooms.add(new Hotel("Berlin", "CabIn", 40000));
        this.rooms.add(new Hotel("Berlin", "CabIn", 40000));
        this.rooms.add(new Hotel("Berlin", "CabIn", 40000));
    }

    public HotelsDB() {

        resetData();

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
        Booking booking = new Booking(hotel.city, hotel.hotel, startDate, endDate, Integer.toString(this.currentBookingNumber), hotel.pricePerDay, hotel.creditcardGuarantee);
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
    public ArrayList<Booking> getHotels(String city, Date arrivalDate, Date departureDate) throws ParseException {
        ArrayList<Booking> reservedBookings = new ArrayList<Booking>();

        for (Hotel hotel : rooms) {
            if (hotel.city.toLowerCase().equals(city.toLowerCase())) {
                if (hotel.bookings.isEmpty()) {
                    reservedBookings.add(addReservation(hotel, arrivalDate, departureDate));
                    continue;
                }
                for (Booking booking : hotel.bookings) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (dateFormat.parse(booking.arrivalDate).before(departureDate) && dateFormat.parse(booking.departureDate).after(arrivalDate)) {
                        // Conflict, do nothing.
                    } else {
                        reservedBookings.add(addReservation(hotel, arrivalDate, departureDate));
                    }
                }
            }
        }
        return reservedBookings;
    }

    public boolean bookHotel(String bookingNumber, int year, int month, String number, String name) throws Exception {
        for (Hotel hotel : rooms) {
            for (Booking booking : hotel.bookings) {
                if (booking.bookingNumber.equals(bookingNumber)) {
                    // Booking exists. 
                    // Charge the creditcard
                    
                    // Init the credit card.
                    CreditCardInfoType card = new dk.dtu.imm.fastmoney.types.CreditCardInfoType();
                    card.setName(name);
                    card.setNumber(number);
                    CreditCardInfoType.ExpirationDate date = new ExpirationDate();
                    date.setMonth(month);
                    date.setYear(year);
                    card.setExpirationDate(date);
                    
                    if (hotel.creditcardGuarantee) {
                        if (bank.validateCreditCard(2, card, 200))
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

    public boolean cancelHotel(String bookingNumber) throws Exception {
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
