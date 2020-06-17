/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.productmanagement.application.BOMController;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.productmanagement.domain.BillOfMaterials;
import eapli.base.productmanagement.domain.Product;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Bernardo Carvalho
 */
public class RegisterProductUI extends AbstractUI {

    private final RegisterProductController theController = new RegisterProductController();
    private final BOMController theBOMController = new BOMController();
    private final BOMController controller = new BOMController();


    @Override
    protected boolean doShow() {

        String code = Console.readLine("Product code");
        while (this.theController.checkAvailableCode(code) == false) {
            code = Console.readLine("Code already exists. Please Insert a valid Product code");
        }
        String commercialCode = Console.readLine("Commercial Code");
        String briefDescription = Console.readLine("Brief Description");
        String fullDescription = Console.readLine("Full Description");
        String category = Console.readLine("Category");
        String measureUnit = Console.readLine("Measure Unit");

        try {
            Product product = this.theController.registerProductWithoutBOM(code, commercialCode, briefDescription, fullDescription, category, measureUnit);

            String bom = Console.readLine("Do you want to insert a bill of materials to this product? (Y/N)");

            while (bom.equalsIgnoreCase("y")) {
                String typeOfMaterial = Console.readLine("Do you want a raw material or a product?(raw material/product)");
                BillOfMaterials billOM = addBOM(product, typeOfMaterial);
                if (billOM != null) {
                    product.addBOMtoProduct(billOM);
                }
                bom = Console.readLine("Do you want to insert another bill of materials to this product? (Y/N)");
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }

        return true;
    }

    protected BillOfMaterials addBOM(Product product, String typeOfMaterial) {

        BillOfMaterials bom = null;

        if (typeOfMaterial.equalsIgnoreCase("product")) {
            for (Product prod : controller.getProducts()) {
                System.out.println(prod.identity() + " " + prod.getBriefDescription());
            }
            String code = Console.readLine("Please insert the desired product's code");
            while (controller.productByIdentity(code) == null) {
                code = Console.readLine("Invalid code! Please insert the desired product's code");
            }
            String qtd = Console.readLine("Quantity");
            String unitMeasure = Console.readLine("Unit Measure");

            try {
                bom = new BillOfMaterials(controller.productByIdentity(code), Integer.valueOf(qtd), unitMeasure);
                this.controller.specifyBOMItem(product, bom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (typeOfMaterial.equalsIgnoreCase("raw material")) {
            for (RawMaterial rm : controller.getRawMaterials()) {
                System.out.println(rm.identity() + " " + rm.description());
            }
            String code = Console.readLine("Please insert the desired raw material's code");
            while (controller.rawMaterialByIdentity(code) == null) {
                code = Console.readLine("Invalid code! Please insert the desired raw material's code");
            }
            String qtd = Console.readLine("Quantity");
            String unitMeasure = Console.readLine("Unit Measure");

            try {
                bom = new BillOfMaterials(controller.rawMaterialByIdentity(code), Integer.valueOf(qtd), unitMeasure);
                this.controller.specifyBOMItem(product, bom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bom;
    }

    @Override
    public String headline() {
        return "Add Product to Product Catalog";
    }

}
