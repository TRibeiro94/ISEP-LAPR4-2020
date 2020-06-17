/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.billofmaterials;

import eapli.base.productmanagement.application.BOMController;
import eapli.base.productmanagement.domain.BillOfMaterials;
import eapli.base.productmanagement.domain.Product;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Tiago Ribeiro
 */
public class SpecifyBOMUI extends AbstractUI {

    private final BOMController controller = new BOMController();

    @Override
    protected boolean doShow() {

        String updateOrNew = Console.readLine("If you wish to update an existing BOM, type 'update', else type 'new'.").toLowerCase();

        while (!(updateOrNew.equals("update") || updateOrNew.equals("new"))) {
            updateOrNew = Console.readLine("Command not recognized. Try again. 'update' for update, 'new' for new.");
        }

        System.out.println("\nCODE \t| DESCRIPTION");

        if (updateOrNew.equals("update")) {  //prints products with a bill of materials
            for (Product prod : controller.getProducts()) {
                if (prod.doesProductHaveBOM() == true) {
                    System.out.println(prod.identity() + " " + prod.getBriefDescription());
                }
            }
        } else if (updateOrNew.equals("new")) {   //prints products WITHOUT a bill of materials
            for (Product prod : controller.getProducts()) {
                if (prod.doesProductHaveBOM() == false) {
                    System.out.println(prod.identity() + " " + prod.getBriefDescription());
                }
            }
        }
        System.out.println("\n");
        final String code = Console.readLine("Insert the product's code you wish to add a Bill of Materials to.");

        String prodOrRaw = Console.readLine("If you wish to add a raw material type 'raw', else type 'prod'.").toLowerCase();

        while (!(prodOrRaw.equals("raw") || prodOrRaw.equals("prod"))) {
            prodOrRaw = Console.readLine("Command not recognized. Try again. 'raw' to add a raw material, 'prod' to add a product.");
        }

        System.out.println("\nCODE \t| DESCRIPTION");

        if (prodOrRaw.equals("raw")) {  //prints raw materials
            for (RawMaterial rm : controller.getRawMaterials()) {
                System.out.println(rm.identity() + " " + rm.description());
            }
        } else if (prodOrRaw.equals("prod")) {   //prints all products except the one the BOM is being added to
            for (Product prod : controller.getProducts()) {
                if (!prod.identity().equals(code)) {
                    System.out.println(prod.identity() + " " + prod.getBriefDescription());
                }
            }
        }
        System.out.println("\n");
        final String codeToBOM = Console.readLine("Insert the code of the item you wish to add to the Bill of Materials.");

        final String qtd = Console.readLine("Quantity");
        final String measure = Console.readLine("Unit measure(UN/LT/CL/KG/GR)");

        try {
            if (prodOrRaw.equals("prod")) {
                this.controller.specifyBOMItem(controller.productByIdentity(code), new BillOfMaterials(controller.productByIdentity(codeToBOM), Integer.valueOf(qtd), measure));
            } else if (prodOrRaw.equals("raw")) {
                this.controller.specifyBOMItem(controller.productByIdentity(code), new BillOfMaterials(controller.rawMaterialByIdentity(codeToBOM), Integer.valueOf(qtd), measure));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Register Bill Of Materials";
    }

}
