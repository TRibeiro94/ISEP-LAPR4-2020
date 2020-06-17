/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.base.productionordermanagement.application.ProductionOrderController;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.HashSet;

/**
 *
 * @author Tiago Ribeiro
 */
public class ProductionOrderByOrderUI extends AbstractUI {

    private final ProductionOrderController controller = new ProductionOrderController();
    
    @Override
    protected boolean doShow() {
        String input = Console.readLine("Insert an Order ID:\n");
        
        HashSet<ProductionOrder> prods = controller.checkProdOrdersByRequest(input);
        if(prods.isEmpty()){
            System.out.println("\nThere are no production orders for the given request.\n");
        }else{
            for (ProductionOrder prod : prods) {
                System.out.printf("Production Order: %s, Emission Date: %s, Expected Date: %s, Product ID: %s, (Quantity: %d, MeasureUnit: %s)\n",prod.identity(), prod.emissionDate().toString(), prod.expectedDate().toString(), prod.prodCode(), prod.quantity().checkQuantity(), prod.quantity().measureUnit());
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Consult Production Order Given an Order";
    }
    
}
