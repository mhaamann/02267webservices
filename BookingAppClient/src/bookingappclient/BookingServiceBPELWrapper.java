/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingappclient;

import ExternalBookingService.Booking;
import ExternalBookingService.FlightInfo;
import ExternalBookingService.GetFlightsResponse;
import ExternalBookingService.GetHotelsResponse;
import ExternalBookingService.HotelList;
import ExternalBookingService.HotelReservation;
import bookingappclient.bookingserviceinterface.BookingServiceInterface;
import java.util.List;

/**
 *
 * @author jens
 */
public class BookingServiceBPELWrapper implements BookingServiceInterface{
    BookingServiceBPEL bookingServiceBPEL = new BookingServiceBPEL();
    
    @Override
    public List<HotelReservation> bookItinerary(java.lang.String itineraryId, java.lang.String name, java.math.BigInteger number, java.math.BigInteger month, java.math.BigInteger year) {
        HotelList hotelList = bookingServiceBPEL.bookItinerary(itineraryId, name, number, month, year);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public String createItinerary(String itineraryId) {
        return bookingServiceBPEL.createItinerary(itineraryId);   
    }

    @Override
    public List<HotelReservation> listItinerary(String itineraryId) {
        HotelList hotelList = bookingServiceBPEL.listItinerary(itineraryId);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public List<HotelReservation> cancelItinerary(String itineraryId) {
        HotelList hotelList = bookingServiceBPEL.cancelItinerary(itineraryId);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public List<Booking> getHotels(String city, String arrival, String departure, String itineraryId) {
        GetHotelsResponse getHotelsResponse = bookingServiceBPEL.getHotels(city, arrival, departure, itineraryId);
        List<Booking> bookingList = getHotelsResponse.getReturn();
        return bookingList;
    }

    @Override
    public List<HotelReservation> addHotel(String bookingNumber, String itineraryId) {
        bookingServiceBPEL.addHotel(bookingNumber, itineraryId);
        HotelList hotelList = bookingServiceBPEL.cancelItinerary(itineraryId);
        List<HotelReservation> hotelReservationList = hotelList.getReservation();
        return hotelReservationList;
    }

    @Override
    public List<FlightInfo> getFlights(String origin, String destination, String startDate, String itineraryId) {
        GetFlightsResponse getFlightsResponse = bookingServiceBPEL.getFlights(origin, destination, startDate, itineraryId);
        List<FlightInfo> flightInfoList = getFlightsResponse.getReturn();
        return flightInfoList;
    }
    
}
