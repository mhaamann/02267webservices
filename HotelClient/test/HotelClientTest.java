/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.dtu.Booking;
import hotelclient.HotelClientWrapper;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class HotelClientTest {
    
    public HotelClientTest() {
    }
    
    @Test
    public void sayHi(){
        try {
            System.out.println("Getting hotels");
            List<Booking> hotels = HotelClientWrapper.getHotels("Copenhagen", "2016-01-01", "2016-01-30");
            for (Booking booking : hotels) {
                System.out.println(booking.getCity());
                System.out.println(booking.getTotalPrice());
            }
        } catch (Exception e) {
            
        }
    }

}
