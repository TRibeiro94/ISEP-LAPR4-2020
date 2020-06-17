/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionlinemanagement.domain;

import eapli.base.machinemanagement.domain.Machine;
import java.util.Date;

import eapli.base.machinemanagement.domain.MachinePosition;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 */
public class ProductionLineTest {
    
    public ProductionLineTest() {
    }

    /**
     * Test of addMachinePosition method, of class ProductionLine.
     */
    //@Test
    public void testAddMachinePosition() {
        System.out.println("addMachinePosition");
        //Machine machine = new Machine("CODE","SERIAL","DESC","BRAND","MODEL", new Date(), prodLine, position);
        //MachinePosition position = new MachinePosition(machine);
        Integer pos = 5;
        ProductionLine instance = new ProductionLine("TEST");
        boolean expResult = true;
        
        //boolean result = instance.addMachinePosition(position, pos);
        //assertEquals(expResult, result);
    }

    /**
     * Test of sameAs method, of class ProductionLine.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        
        //Tests for a null comparison
        Object other = null;
        ProductionLine instance = new ProductionLine("1");
        boolean expResult = false;
        boolean result = instance.sameAs(other);
        assertEquals(expResult, result);

        //Tests for comparison between different classes
        //Machine machine = new Machine("CODE","SERIAL","DESC","BRAND","MODEL", new Date(), prodLine, position);
        //boolean result2 = instance.sameAs(machine);
        //assertEquals(expResult, result2);
        
        //Tests for different objects
        ProductionLine instance2 = new ProductionLine("2");
        boolean result3 = instance.sameAs(instance2);
        assertEquals(expResult, result3);
        
        //Tests for equal objects
        boolean expResult2 = true;
        ProductionLine instance4 = new ProductionLine("1");
        boolean result4 = instance.sameAs(instance4);
        assertEquals(expResult2, result4);
    }

    /**
     * Test of identity method, of class ProductionLine.
     */
    //@Test
    public void testIdentity() {
        System.out.println("identity");
        ProductionLine instance = new ProductionLine("1794");
        String expResult = "1794";
        String result = instance.identity();
        assertEquals(expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void prohibitProductionLineWithNullCode() {
        ProductionLine pl = new ProductionLine(null);
    }
    
}
