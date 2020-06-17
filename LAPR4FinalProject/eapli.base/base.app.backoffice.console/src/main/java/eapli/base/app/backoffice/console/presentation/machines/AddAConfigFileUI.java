package eapli.base.app.backoffice.console.presentation.machines;

import eapli.base.machinemanagement.application.AddConfigFileToAMachineController;
import eapli.base.machinemanagement.domain.Machine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;


public class AddAConfigFileUI extends AbstractUI {

    private final AddConfigFileToAMachineController theController = new AddConfigFileToAMachineController();

    @Override
    protected boolean doShow() {
        final Iterable<Machine> machines = this.theController.listAllMachines();

        final SelectWidget<Machine> selector = new SelectWidget<>("Select a machine:", machines, new MachinePrinter());
        selector.show();
        final Machine theMachine = selector.selectedElement();

        if (theMachine == null) {
            return false;
        }
        final String filePath = Console.readLine("Insert the path of the file");
        try {
            return this.theController.addConfigFile(theMachine, filePath);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String headline() {
        return "Add Configuration File To A Machine";
    }
}
