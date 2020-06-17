/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.machines;

import eapli.base.machinemanagement.application.AddConfigFileToAMachineController;
import eapli.base.machinemanagement.domain.ConfigurationFile;
import eapli.base.machinemanagement.domain.Machine;
import eapli.base.messagemanagement.application.TransmitConfigFileByTCPController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * @author Tiago Ribeiro
 */
public class ChooseConfigFileTCPUI extends AbstractUI {

    private final AddConfigFileToAMachineController theController = new AddConfigFileToAMachineController();
    private final TransmitConfigFileByTCPController controller = new TransmitConfigFileByTCPController();

    @Override
    protected boolean doShow() {
        Iterable<Machine> machines = this.theController.listAllMachines();
        SelectWidget<Machine> selector = new SelectWidget<>("Select a machine:", machines, new MachinePrinter());
        selector.show();

        Machine theMachine = selector.selectedElement();
        if (theMachine == null) {
            return false;
        }

        Iterable<ConfigurationFile> configFiles = theMachine.configFiles();
        if (theMachine.configFiles().isEmpty()) {
            System.out.println("The selected machine has no configuration files assigned. Please visit the Machines in order to assign one.");
        } else {
            SelectWidget<ConfigurationFile> selectorConfigFiles = new SelectWidget<>("Choose a configuration file:", configFiles, new ConfigurationFilePrinter());
            selectorConfigFiles.show();

            ConfigurationFile theConfigFile = selectorConfigFiles.selectedElement();
            if (theConfigFile == null) {
                return false;
            }

            String configFile = theConfigFile.configFileDescription();
            int machineCode = Integer.parseInt(theMachine.protocol());

            try {
                this.controller.sendConfigurationFileByTCP(configFile, machineCode);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Send a specific Config File to a machine.";
    }

}
