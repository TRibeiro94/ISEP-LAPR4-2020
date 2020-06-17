package eapli.base.app.backoffice.console.presentation.machines;

import eapli.base.machinemanagement.application.RegisterMachineController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.Date;

public class RegisterMachineUI extends AbstractUI {

    private final RegisterMachineController theController = new RegisterMachineController();

    @Override
    protected boolean doShow() {
        final Iterable<ProductionLine> productionLines = this.theController.getProductionLines();

        final SelectWidget<ProductionLine> selector = new SelectWidget<>("Select the production line you want to add the new machine:", productionLines, new ProductionLinePrinter());
        selector.show();
        final ProductionLine theProductionLine = selector.selectedElement();
        if (theProductionLine == null) {
            return false;
        }
        final Integer position = Console.readInteger("In which position do you want to add the machine?");

        final String machineCode = Console.readLine("Machine code");
        final String description = Console.readLine("Description");
        final String serialNumber = Console.readLine("Serial Number");
        final String protocol = Console.readLine("Protocol Id");
        final String brand = Console.readLine("Brand");
        final String model = Console.readLine("Model");
        final Date installDate = Console.readDate("Installation Date");

        try {
            this.theController.registerMachine(theProductionLine, machineCode, serialNumber, protocol, description, brand, model, installDate, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Register Machine";
    }
}
