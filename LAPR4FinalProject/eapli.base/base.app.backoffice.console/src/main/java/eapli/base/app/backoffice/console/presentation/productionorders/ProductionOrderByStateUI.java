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
import java.util.ArrayList;

/**
 *
 * @author Tiago Ribeiro
 */
public class ProductionOrderByStateUI extends AbstractUI {

    private final ProductionOrderController controller = new ProductionOrderController();

    @Override
    protected boolean doShow() {
        String input = Console.readLine("Production Orders: Executing (Type 1) | Pending (Type 2):\n");
        
        ArrayList<ProductionOrder> prodsExec = new ArrayList<>();
        ArrayList<ProductionOrder> prodsNonExec = new ArrayList<>();
        
        switch (input) {
            case "1":
                boolean executing = true;
                prodsExec = controller.checkProdOrdersByState(executing);
                break;
            case "2":
                boolean nonExecuting = false;
                prodsNonExec = controller.checkProdOrdersByState(nonExecuting);
                break;
            default:
                System.out.println("\nA invalid input was inserted. Try again.\n");
                this.doShow();
                break;
        }

        if (input.equals("1") && prodsExec.isEmpty()) {
            System.out.println("\nThere are no production orders for the given state.\n");
        } else if (input.equals("2") && prodsNonExec.isEmpty()) {
            System.out.println("\nThere are no production orders for the given state.\n");
        } else {
            if (input.equals("1")) {
                for (ProductionOrder prod : prodsExec) {
                    System.out.println(prod.toString());
                }
            }
            if (input.equals("2")) {
                for (ProductionOrder prod : prodsNonExec) {
                    System.out.println(prod.toString());
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Consult Production Order Given an State";
    }

}
