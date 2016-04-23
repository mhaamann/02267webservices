/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author User
 */
public class FlightInfoDataBase {
    
    GregorianCalendar sd1 = new GregorianCalendar(2014, 10, 8);
    XMLGregorianCalendar startDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd1);    
    GregorianCalendar ed1 = new GregorianCalendar(2015, 9, 7);
    XMLGregorianCalendar endDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ed1);
    
    GregorianCalendar sd2 = new GregorianCalendar(2014, 10, 8);
    XMLGregorianCalendar startDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd2);    
    GregorianCalendar ed2 = new GregorianCalendar(2015, 9, 7);
    XMLGregorianCalendar endDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ed2);
    
    GregorianCalendar sd3 = new GregorianCalendar(2014, 10, 8);
    XMLGregorianCalendar startDate3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd3);    
    GregorianCalendar ed3 = new GregorianCalendar(2015, 9, 7);
    XMLGregorianCalendar endDate3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ed3);
    
    GregorianCalendar sd4 = new GregorianCalendar(2014, 10, 8);
    XMLGregorianCalendar startDate4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd4);    
    GregorianCalendar ed4 = new GregorianCalendar(2015, 9, 7);
    XMLGregorianCalendar endDate4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ed4);
    
    GregorianCalendar sd5 = new GregorianCalendar(2014, 10, 8);
    XMLGregorianCalendar startDate5 = DatatypeFactory.newInstance().newXMLGregorianCalendar(sd5);    
    GregorianCalendar ed5 = new GregorianCalendar(2015, 9, 7);
    XMLGregorianCalendar endDate5 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ed5);
    
    FlightInfo flight_1;
    FlightInfo flight_2;
    FlightInfo flight_3;
    FlightInfo flight_4;
    FlightInfo flight_5;

    public FlightInfoDataBase() throws DatatypeConfigurationException {
        this.flight_1 = new FlightInfo("Copenhagen", "Berlin", "B12341", 
                "LameDuck", "SAS", startDate1, endDate1, 50);
        this.flight_2 = new FlightInfo("Berlin", "Amsterdam", "B12342", 
                "LameDuck", "Ryanair", startDate2, endDate2, 100);
        this.flight_3 = new FlightInfo("Amsterdam", "Paris", "B12343", 
                "LameDuck", "SAS", startDate3, endDate3, 88);
        this.flight_4 = new FlightInfo("Paris", "Madrid", "B12344", 
                "LameDuck", "Ryanair", startDate4, endDate4, 66);        
        this.flight_5 = new FlightInfo("Madrid", "Copenhagen", "B12345", 
                "LameDuck", "SAS", startDate5, endDate5, 70);
    }
}
