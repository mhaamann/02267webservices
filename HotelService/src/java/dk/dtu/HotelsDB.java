package dk.dtu;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Matthias Haamann
 */
public class HotelsDB {

    private ArrayList<Hotel> rooms;
    private Integer currentBookingNumber;
    private BankServiceWrapper bank = new BankServiceWrapper();
    private static int hotelBankGroupId = 50308815;
    private boolean reserveHotels = false; // TODO: for debug purposes.
    private static HotelsDB singletonHotelsDB = null;
    
    public HotelsDB() {
        resetData();
    }
    
    public static HotelsDB getHotelsDB() {
        if (singletonHotelsDB == null) {
            singletonHotelsDB = new HotelsDB();
        }
        return singletonHotelsDB;
    }

    /** 
     * Function to reset the hotel data for testing purposes.
     */
    public void resetData() {
        this.rooms = new ArrayList<Hotel>();
        this.currentBookingNumber = 1;

        // Each hotel represents a Room. The second parameter represents the hotel name.
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 1000, true));
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 1000, false));
        this.rooms.add(new Hotel("Copenhagen", "CabIn", 3000, false));

        this.rooms.add(new Hotel("Amsterdam", "CabIn", 500, false));
        this.rooms.add(new Hotel("Amsterdam", "CabIn", 500, true));

        this.rooms.add(new Hotel("Berlin", "CabIn", 1500, true));
        this.rooms.add(new Hotel("Berlin", "CabIn", 1500, false));
        this.rooms.add(new Hotel("Berlin", "CabIn", 1500, false));
        this.rooms.add(new Hotel("Berlin", "CabIn", 1500, false));
        this.rooms.add(new Hotel("Berlin", "CabIn", 1500, false));
    }

    /**
     * Creates a unconfirmed booking reservation and saves it on the hotel
     * object.
     * 
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
     * Returns a list of rooms that does not have a booking that conflicts with
     * the given date.
     *
     * @param city The city where the hotel is located.
     * @param arrivalDate
     * @param departureDate
     * @return
     */
    public ArrayList<Booking> getHotels(String city, Date arrivalDate, Date departureDate) throws ParseException {
        ArrayList<Booking> reservedBookings = new ArrayList<Booking>();

        for (Hotel hotel : rooms) {
            if (hotel.city.toLowerCase().equals(city.toLowerCase())) {
                if (!this.reserveHotels) {
                   reservedBookings.add(addReservation(hotel, arrivalDate, departureDate));
                    continue; 
                }
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
    
    /**
     * Book the hotel identified by the booking number. If credit card guarantee 
     * is required the credit card will be checked, if no guarantee is required
     * the booking is confirmed right away.
     * 
     * @param bookingNumber The number for the booking.
     * @param year The year of the credit card.
     * @param month The month of the credit card.
     * @param number The credit card number.
     * @param name The name on the credit card.
     * @return
     * @throws Exception 
     */
    public boolean bookHotel(String bookingNumber, int year, int month, String number, String name) throws Exception {
        for (Hotel hotel : rooms) {
            for (Booking booking : hotel.bookings) {
                if (booking.bookingNumber.equals(bookingNumber)) {
                    // Booking exists. 
                    // Charge the creditcard

                    if (hotel.creditcardGuarantee) {

                        // Init the credit card.
                        CreditCardInfoType card = new CreditCardInfoType();
                        card.setName(name);
                        card.setNumber(number);
                        CreditCardInfoType.ExpirationDate date = new ExpirationDate();
                        date.setMonth(month);
                        date.setYear(year);
                        card.setExpirationDate(date);

                        if (bank.validateCreditCard(1, card, booking.totalPrice.intValue())) {
                            // Creditcard validated, confirming the hotel.
                            booking.status = "confirmed";
                            return true;
                        } else {
                            throw new Exception("Could not be validated");
                        }
                    } else {
                        // No creditcard guarantee, go ahead and confirm the hotel.
                        booking.status = "confirmed";
                        return true;
                    }
                }
            }
        }
        throw new Exception("Booking did not exists");
    }
    
    /**
     * Cancels the booking on the hotel identified by the booking number.
     * For testing purposes the cancellation fails if the credit card guarantee
     * is required on the booking.
     * 
     * @param bookingNumber The number for the booking.
     * @return true if the cancellation succeeded.
     * @throws Exception In case the cancellation could not be processed. 
     */
    public boolean cancelHotel(String bookingNumber) throws Exception {
        for (Hotel hotel : rooms) {
            for (Booking booking : hotel.bookings) {
                if (booking.bookingNumber.equals(bookingNumber)) {
                    // Booking exists. 
                    if (booking.creditcardGuarantee) {
                        // Decline cancellation
                        throw new Exception("Cancellation not allowed when credit card guarantee is required");
                    } else {
                        // Cancel hotel.
                        booking.status = "cancelled";
                        // Remove booking from system.
                        hotel.bookings.remove(booking);
                        return true;
                    }
                }
            }
        }
        throw new Exception("Booking not found");
    }
}
