package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.base.productionordermanagement.application.ProductionOrderImport;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Pedro Borda de Água
 */
public class ImporterPOUI extends AbstractUI {
    private final ProductionOrderImport controller = new ProductionOrderImport();

    @Override
    protected boolean doShow() {
        final String filename = Console.readLine("Insert the filename: ");

        try {
            this.controller.importer(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String headline() {
        return "Import Production Order information from CSV";
    }
}
