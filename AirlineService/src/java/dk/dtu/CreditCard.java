/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author User
 */
@XmlType(name = "CreditCard")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {

    @XmlElement(name = "cvc")
    String cvc;

    public CreditCard() {
    }

    public CreditCard(String cvc) {
        this.cvc = cvc;
    }

}
