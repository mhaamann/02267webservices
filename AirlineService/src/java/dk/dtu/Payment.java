/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

/**
 *
 * @author mhaamann
 */
class Payment {

    public String bookingNumber;
    public int year;
    public int month;
    public String number;
    public String name;
    
    public Payment(String bookingNumber, int year, int month, String number, String name) {
        
        this.bookingNumber = bookingNumber;
        this.year = year;
        this.month = month;
        this.number = number;
        this.name = name;
    }

    Payment(String bookingNumber) {
       this.bookingNumber = bookingNumber;
    }
    
}
