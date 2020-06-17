/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.domain;

import eapli.base.machinemanagement.domain.Machine;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 *
 * @author Tiago Ribeiro
 */
public class DepositTest {
    
    public DepositTest() {
    }

    /**
     * Test of identity method, of class Deposit.
     */
    @Test
    public void testIdentity() {
        System.out.println("identity");
        Deposit instance = new Deposit("46","Valentino Rossi");
        String expResult = "46";
        String result = instance.identity();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeDescription method, of class Deposit.
     */
    @Test
    public void testChangeDescription() {
        System.out.println("changeDescription");
        String expected = "NEW_DESC";
        Deposit instance = new Deposit("CODE","DESC_1");
        instance.changeDescription(expected);
        
        assertEquals(expected, instance.consultDepositsDescription());
    }
    
    @Test
    public void testConsultDepositsDescription(){
        System.out.println("consultDepositsDescription");
        Deposit instance = new Deposit("94","TR");
        String expected = "TR";
        String obtained = instance.consultDepositsDescription();
        
        assertEquals(expected, obtained);
    }

    /**
     * Test of sameAs method, of class Deposit.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        
        //Tests for a null comparison
        Object other = null;
        Deposit instance = new Deposit("1","A");
        boolean expResult = false;
        boolean result = instance.sameAs(other);
        assertEquals(expResult, result);

        //Tests for comparison between different classes
        //Machine machine = new Machine("CODE","SERIAL","DESC","BRAND","MODEL", new Date(), prodLine, position);
        //boolean result2 = instance.sameAs(machine);
        //assertEquals(expResult, result2);
        
        //Tests for same code but different description
        Deposit instance2 = new Deposit("1","B");
        boolean result3 = instance.sameAs(instance2);
        assertEquals(expResult, result3);
        
        //Tests for different code but same description
        Deposit instance3 = new Deposit("2","A");
        boolean result4 = instance.sameAs(instance3);
        assertEquals(expResult, result4);
        
        //Tests for both fields different
        Deposit instance4 = new Deposit("9","Z");
        boolean result5 = instance.sameAs(instance4);
        assertEquals(expResult, result5);
        
        //Tests for equal object
        boolean expResult2 = true;
        Deposit instance5 = new Deposit("1","A");
        boolean result6 = instance.sameAs(instance5);
        assertEquals(expResult2, result6);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void prohibitDepositWithNullCode() {
        Deposit deposit = new Deposit(null, "DESCRIPTION_TEST");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void prohibitDepositWithNullDescription() {
        Deposit deposit = new Deposit("CODE_TEST", null);
    }
    
    //verificar mais tarde se existe alguma regra de negócio especifica no deposito ou nos seus atributos
}
