/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.db;

/**
 *
 * @author Matthias Haamann
 */
class Flight {
    public String bookingNumber;
    public String status;
    
    public Flight (String bookingNumber) {
        this.bookingNumber = bookingNumber;
        this.status = "unconfirmed";
    }
}
