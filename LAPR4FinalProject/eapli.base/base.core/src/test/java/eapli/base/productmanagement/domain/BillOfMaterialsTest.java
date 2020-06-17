/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.domain;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 */
public class BillOfMaterialsTest {
    
    public BillOfMaterialsTest() {
    }

    /**
     * Test of checkBOMItemIdentifier method, of class BillOfMaterials.
     */
    @Test
    public void testCheckBOMItemIdentifier() {
        System.out.println("checkBOMItemIdentifier");
        Product p = new Product("COD","COM","BRI","FUL", "CAT", "KG");
        BillOfMaterials instance = new BillOfMaterials(p, 5, "UN");
        String expResult = "COD";
        String result = instance.checkBOMItemIdentifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkQuantity method, of class BillOfMaterials.
     */
    @Test
    public void testCheckQuantity() {
        System.out.println("checkQuantity");
        Product p = new Product("COD","COM","BRI","FUL", "CAT", "KG");
        BillOfMaterials instance = new BillOfMaterials(p, 5, "UN");
        int expResult = 5;
        int result = instance.checkQuantity();
        assertEquals(expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void prohibitDepositWithNullItemValue() {
        BillOfMaterials instance1 = new BillOfMaterials((Product)null, 4, "UN");
        BillOfMaterials instance2 = new BillOfMaterials((RawMaterial)null, 6, "UN");
    }
    
}
