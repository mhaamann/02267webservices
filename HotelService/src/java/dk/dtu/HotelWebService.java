/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author User
 */
@WebService(serviceName = "HotelWebService")
public class HotelWebService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private HotelsDB hotelsDB = new HotelsDB();

    @WebMethod(operationName = "getHotels")
    public ArrayList<Booking> getHotels(@WebParam(name = "city") String city, @WebParam(name = "arrivalDate") String arrivalDate, @WebParam(name = "departureDate") String departureDate) throws ParseException {
        // Parse the dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return hotelsDB.getHotels(city, dateFormat.parse(arrivalDate), dateFormat.parse(departureDate));
    }

    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(
            @WebParam(name = "bookingNumber") String bookingNumber,
            @WebParam(name = "year") int year,
            @WebParam(name = "month") int month,
            @WebParam(name = "number") String number,
            @WebParam(name = "name") String name) throws Exception {
        return hotelsDB.bookHotel(bookingNumber, year, month, number, name);
    }
    
    @WebMethod(operationName = "resetHotel")
    public boolean resetHotel() {
        hotelsDB.resetData();
        return true;
    }

    @WebMethod(operationName = "cancelHotel")
    public boolean cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber) throws Exception {
        return hotelsDB.cancelHotel(bookingNumber);
    }

}
