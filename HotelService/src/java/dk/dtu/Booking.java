/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.Date;

/**
 *
 * @author mhaamann
 */
class Booking {
    Date arrivalDate;
    Date departureDate;
    String status;
    String bookingNumber;
    Double totalPrice;
    Boolean creditcardGuarantee;
            
    public Booking (Date arrivalDate, Date departureDate, String bookingNumber, Integer price, Boolean creditcardGuarantee) {
        this.status = "unconfirmed";
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.bookingNumber = bookingNumber;
        this.creditcardGuarantee = creditcardGuarantee;
        totalPrice = (double) price * daysBetween(arrivalDate, departureDate);
    }
    
    /**
     * Calculate number of days between two Date objects.
     * @param d1 Date
     * @param d2 Date
     * @ref stackoverflow.com/questions/7103064/java-calculate-the-number-of-days-between-two-dates
     * @return Number of days between the two Date objects.
     */
    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
}
