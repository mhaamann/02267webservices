/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author User
 */
public class FlightInfoDataBase {
    
    FlightInfo flight_1 = new FlightInfo("Copenhagen", "Berlin", "B12341", 
            "LameDuck", "SAS", XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price);
    FlightInfo flight_2 = new FlightInfo("Berlin", "Amsterdam", "B12342", 
            "LameDuck", "Ryanair", XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price);
    FlightInfo flight_3 = new FlightInfo("Amsterdam", "Paris", "B12343", 
            "LameDuck", "SAS", XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price);
    FlightInfo flight_4 = new FlightInfo("Paris", "Madrid", "B12344", 
            "LameDuck", "Ryanair", XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price);
    FlightInfo flight_5 = new FlightInfo("Madrid", "Copenhagen", "B12345", 
            "LameDuck", "SAS", XMLGregorianCalendar startDate, 
            XMLGregorianCalendar endDate, Integer price);
}
