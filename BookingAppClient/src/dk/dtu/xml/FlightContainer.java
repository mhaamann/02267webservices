/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.xml;

import ExternalBookingService.FlightInfo;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mhaamann
 */
@XmlRootElement
public class FlightContainer {

    public List<FlightInfo> flight;


}
