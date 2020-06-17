/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.domain;

import eapli.base.machinemanagement.domain.Machine;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 */
public class ProductTest {
    
    public ProductTest() {
    }

    /**
     * Test of sameAs method, of class Product.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        
        //Tests for a null comparison
        Object other = null;
        Product instance = new Product("code","commercial","brief","full","cat","unit");
        boolean expResult = false;
        boolean result = instance.sameAs(other);
        assertEquals(expResult, result);

        //Tests for comparison between different classes
        //Machine machine = new Machine("CODE","SERIAL","DESC","BRAND","MODEL", new Date(), prodLine, position);
        //boolean result2 = instance.sameAs(machine);
        //assertEquals(expResult, result2);
        
        //Tests for different objects
        Product instance2 = new Product("1","B","A","C","cat","unit");
        boolean result3 = instance.sameAs(instance2);
        assertEquals(expResult, result3);
        
        //Tests for equal object
        boolean expResult2 = true;
        Product instance5 = new Product("code","commercial","brief","full","cat","unit");
        boolean result6 = instance.sameAs(instance5);
        assertEquals(expResult2, result6);
    }

    /**
     * Test of identity method, of class Product.
     */
    @Test
    public void testIdentity() {
        System.out.println("identity");
        Product instance = new Product("IM_CODE","","","","cat","unit");
        String expResult = "IM_CODE";
        String result = instance.identity();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of doesProductHaveBOM method, of class Product.
     * Not having BOM
     */
    @Test
    public void testDoesProductHaveBOM_no() {
        System.out.println("doesProductHaveBOM");
        Product instance = new Product("1","2","3","4","cat","unit");
        
        boolean expResult = false;
        boolean result = instance.doesProductHaveBOM();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of doesProductHaveBOM method, of class Product.
     * Having BOM
     */
    @Test
    public void testDoesProductHaveBOM_yes() {
        System.out.println("doesProductHaveBOM");
        BillOfMaterials bom = new BillOfMaterials(new Product(), 9, "UN");
        Product instance = new Product("1","2","3","4", "cat", "unit",bom);
        
        boolean expResult = true;
        boolean result = instance.doesProductHaveBOM();
        assertEquals(expResult, result);
    }

    /**
     * Test of addBOMtoProduct method, of class Product.
     */
    //@Test
    public void testAddBOMtoProduct() {
        System.out.println("addBOMtoProduct");
        BillOfMaterials bom1 = new BillOfMaterials(new Product(), 9, "UN");
        BillOfMaterials bom2 = new BillOfMaterials(new Product(), 2, "KG");
        
        Product instance = new Product("a","b","c","d","cat","unit");
        instance.addBOMtoProduct(bom1);
        instance.addBOMtoProduct(bom2);
                
        assertEquals((BillOfMaterials)instance.consultBOMS().toArray()[1], bom1);
        assertEquals((BillOfMaterials)instance.consultBOMS().toArray()[0], bom2);
    }
    
}
