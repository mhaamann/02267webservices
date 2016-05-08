/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banktest;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class SecureBankTest {
    
    public SecureBankTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private static boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankSecureService service = new dk.dtu.imm.fastmoney.BankSecureService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankSecurePort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
