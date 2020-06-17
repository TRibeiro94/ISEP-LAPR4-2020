/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.productmanagement.application.ImportController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Tiago
 */
public class ImporterUI extends AbstractUI {

    private final ImportController controller = new ImportController();

    @Override
    protected boolean doShow() {
        final String filename = Console.readLine("Insert the filename");

        try {
            this.controller.importer(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Import Product Catalog from CSV";
    }

}
